package net.thumbtack.school.hiring.daoimpl;

import net.thumbtack.school.hiring.dao.PersonDao;
import net.thumbtack.school.hiring.database.DataBase;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.users.*;

import java.util.Collection;
import java.util.List;

public class PersonDaoImpl implements PersonDao {
    private DataBase dataBase;

    public PersonDaoImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void insert(Person person, String token) throws ServerException {
        dataBase.insert(person, token);
    }

    @Override
    public void insertAuthorized(Person person, String token) throws ServerException {
        dataBase.insertAuthorized(person, token);
    }

    @Override
    public Person getPersonByToken(String token) throws ServerException {
        return dataBase.getPersonByToken(token);
    }

    @Override
    public Person getPersonByLogin(String login) throws ServerException {
        return dataBase.getPersonByLogin(login);
    }

    @Override
    public void removeKey(String token) throws ServerException {
        dataBase.removeKey(token);
    }


    @Override
    public List<Vacancy> getVacancies() throws ServerException {
        return dataBase.getVacancies();
    }

    @Override
    public List<Employee> getEmployees() throws ServerException {
        return dataBase.getEmployees();
    }

    @Override
    public void insertSkill(String skill) throws ServerException {
        dataBase.insertSkill(skill);
    }

    @Override
    public void insertSkills(Skill skill, Person person) throws ServerException {
        dataBase.insertSkill(skill, person);
    }


    @Override
    public Collection<Person> getPersonsBySkill(Skill skill) throws ServerException {
        return dataBase.getPersonsBySkill(skill);
    }

    @Override
    public Collection<Person> getPersonsBySkillAllLevel(Skill skill) throws ServerException {
        return dataBase.getPersonsBySkillAllLevel(skill);
    }

    @Override
    public Collection<Vacancy> getVacanciesByRequirement(Requirement requirement) throws ServerException {
        return dataBase.getVacanciesByRequirement(requirement);
    }

    @Override
    public Collection<Vacancy> getVacanciesByRequirementAllLevel(Requirement requirement) throws ServerException {
        return dataBase.getVacanciesByRequirementAllLevel(requirement);
    }

    @Override
    public Collection<Vacancy> getVacanciesByRequirementWithRequire(Requirement requirement) throws ServerException {
        return dataBase.getVacanciesByRequirementWithRequire(requirement);
    }

    @Override
    public void insertRequirement(Requirement requirement, Vacancy vacancy) throws ServerException {
        dataBase.insertRequirement(requirement, vacancy);
    }

    @Override
    public void insertRequirementWithRequire(Requirement requirement, Vacancy vacancy) throws ServerException {
        dataBase.insertRequirementWithRequire(requirement, vacancy);
    }
}
