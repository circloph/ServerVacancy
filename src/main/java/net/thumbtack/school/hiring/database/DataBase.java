package net.thumbtack.school.hiring.database;

import net.thumbtack.school.hiring.comparator.PersonComparator;
import net.thumbtack.school.hiring.comparator.RequirementComparator;
import net.thumbtack.school.hiring.comparator.SkillComparator;
import net.thumbtack.school.hiring.comparator.VacancyComparator;
import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.users.Require;
import net.thumbtack.school.hiring.users.*;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import com.google.common.collect.SortedSetMultimap;
import com.google.common.collect.TreeMultimap;


public class DataBase implements  Serializable {
    private Map<String, Person> registeredPersons;
    private Map<String, Person> authorizedPersons;
    private List<String> skillsList;
    private SortedSetMultimap<Skill, Person> personsOnSkill;
    private SortedSetMultimap<Requirement, Vacancy> vacanciesOnRequirement;
    private SortedSetMultimap<Requirement, Vacancy> vacanciesOnRequirementWithRequire;

    public DataBase() {
        registeredPersons = new HashMap<>();
        authorizedPersons = new HashMap<>();
        skillsList = new ArrayList<>();
        personsOnSkill = TreeMultimap.create(new SkillComparator(), new PersonComparator());

        vacanciesOnRequirement = TreeMultimap.create(new RequirementComparator(), new VacancyComparator());
        vacanciesOnRequirementWithRequire = TreeMultimap.create(new RequirementComparator(), new VacancyComparator());
    }


    public List<String> getSkillsList() {
        return skillsList;
    }

    public Map<String, Person> getRegisteredPersons() {
        return registeredPersons;
    }

    public Map<String, Person> getAuthorizedPersons() {
        return authorizedPersons;
    }

    public void insertPersonsWithSkill(Skill skill, Person person) {
        personsOnSkill.put(skill, person);
    }

    public Collection<Person> getPersonsBySkill(Skill skill) {
        NavigableMap<Skill, Collection<Person>> persons = null;
        SortedMap<Skill, Collection<Person>> personSortedMap = null;
        persons = (NavigableMap<Skill, Collection<Person>>) personsOnSkill.asMap();
        personSortedMap = persons.subMap(new Skill(skill.getDescriptionSkill(), skill.getProficiencyLevel()), new Skill(skill.getDescriptionSkill(), 6));
        Collection<Person> personList = personSortedMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList());

        return personList;
    }

    public Collection<Person> getPersonsBySkillAllLevel(Skill skill) {
        NavigableMap<Skill, Collection<Person>> persons = null;
        SortedMap<Skill, Collection<Person>> personSortedMap = null;
        persons = (NavigableMap<Skill, Collection<Person>>) personsOnSkill.asMap();
        personSortedMap = persons.subMap(new Skill(skill.getDescriptionSkill(), 0), new Skill(skill.getDescriptionSkill(), 6));
        Collection<Person> personList = personSortedMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList());

        return personList;
    }

    public Collection<Vacancy> getVacanciesByRequirement(Requirement requirement) {
        NavigableMap<Requirement,Collection<Vacancy>> vacancies = null;
        SortedMap<Requirement, Collection<Vacancy>> vacancySortedMap = null;
        vacancies = (NavigableMap<Requirement, Collection<Vacancy>>) vacanciesOnRequirement.asMap();
        vacancySortedMap = vacancies.subMap(new Requirement(requirement.getDescriptionSkill(), 1, Require.YES), new Requirement(requirement.getDescriptionSkill(), requirement.getProficiencyLevel()+1, Require.YES));
        Collection<Vacancy> vacancies1 = vacancySortedMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList());

        return vacancies1;
    }

    public Collection<Vacancy> getVacanciesByRequirementWithRequire(Requirement requirement) {
        NavigableMap<Requirement,Collection<Vacancy>> vacancies = null;
        SortedMap<Requirement, Collection<Vacancy>> vacancySortedMap = null;
        vacancies = (NavigableMap<Requirement, Collection<Vacancy>>) vacanciesOnRequirementWithRequire.asMap();

        vacancySortedMap = vacancies.subMap(new Requirement(requirement.getDescriptionSkill(), 1, Require.YES), new Requirement(requirement.getDescriptionSkill(), requirement.getProficiencyLevel()+1, Require.YES));
        return vacancySortedMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList());

    }



    public Collection<Vacancy> getVacanciesByRequirementAllLevel(Requirement requirement) {
        NavigableMap<Requirement,Collection<Vacancy>> vacancies = null;
        SortedMap<Requirement, Collection<Vacancy>> vacancySortedMap = null;
        vacancies = (NavigableMap<Requirement, Collection<Vacancy>>) vacanciesOnRequirement.asMap();

        vacancySortedMap = vacancies.subMap(new Requirement(requirement.getDescriptionSkill(), 0, Require.YES), new Requirement(requirement.getDescriptionSkill(), 6, Require.YES));
        return vacancySortedMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public void insertAuthorized(Person person, String token) throws ServerException {
        if (authorizedPersons.putIfAbsent(token, person) != null) {
            throw new ServerException(ServerErrorCode.PERSON_EXIST);
        }
    }


    public void insert(Person person, String token) throws ServerException {
        insertAuthorized(person, token);
        if (registeredPersons.putIfAbsent(person.getLogin(), person) != null) {
            throw new ServerException(ServerErrorCode.PERSON_EXIST);
        }
    }

    public Person getPersonByLogin(String login) throws ServerException {
        if (registeredPersons.containsKey(login)) {
            return registeredPersons.get(login);
        } else {
            throw new ServerException(ServerErrorCode.PERSON_NOT_EXIST);
        }
    }

    public Person getPersonByToken(String token) throws ServerException {
        Person person = authorizedPersons.get(token);
        if (person == null) {
            throw new ServerException(ServerErrorCode.PERSON_NOT_AUTHORIZED);
        }
        return authorizedPersons.get(token);
    }

    public void removeKey(String token) throws ServerException {
        authorizedPersons.remove(token, getPersonByToken(token));
    }


    public List<Vacancy> getVacancies() {
        List<Vacancy> vacancies = new ArrayList<>();
        for (Person person : registeredPersons.values()) {
            if (person.getClass().equals(Employer.class)) {
                vacancies.addAll(((Employer) person).getVacancies());
            }
        }
        return vacancies;
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        registeredPersons.forEach((k, v) -> {
            if (v.getClass().equals(Employee.class)) {
                employees.add((Employee) v);
            }
        });
        return employees;
    }

    public void insertSkill(String skill) {
        if (!getSkillsList().contains(skill)) {
            getSkillsList().add(skill);
        }
    }

    public void insertSkill(Skill skill, Person person) {
        personsOnSkill.put(skill, person);
    }

    public void insertRequirement(Requirement requirement, Vacancy vacancy) {
        vacanciesOnRequirement.put(requirement, vacancy);
    }

    public void insertRequirementWithRequire(Requirement requirement, Vacancy vacancy) {
        vacanciesOnRequirementWithRequire.put(requirement, vacancy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataBase dataBase = (DataBase) o;
        return Objects.equals(registeredPersons, dataBase.registeredPersons) && Objects.equals(authorizedPersons, dataBase.authorizedPersons) && Objects.equals(skillsList, dataBase.skillsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registeredPersons, authorizedPersons, skillsList);
    }
}
