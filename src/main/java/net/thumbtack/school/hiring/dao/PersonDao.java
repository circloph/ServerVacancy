package net.thumbtack.school.hiring.dao;

import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.users.*;

import java.util.Collection;
import java.util.List;

public interface PersonDao {
    void insert(Person person, String token) throws ServerException;
    void insertAuthorized(Person person, String token) throws ServerException;
    Person getPersonByToken(String token) throws ServerException;
    Person getPersonByLogin(String login) throws ServerException;
    void removeKey(String token) throws ServerException;
    List<Vacancy> getVacancies() throws ServerException;
    List<Employee> getEmployees() throws ServerException;
    void insertSkill(String skill) throws ServerException;
    void insertSkills(Skill skill, Person person) throws ServerException;
    Collection<Person> getPersonsBySkill(Skill skill) throws ServerException;
    Collection<Person> getPersonsBySkillAllLevel(Skill skill) throws ServerException;
    Collection<Vacancy> getVacanciesByRequirement(Requirement requirement) throws ServerException;
    Collection<Vacancy> getVacanciesByRequirementWithRequire(Requirement requirement) throws ServerException;
    Collection<Vacancy> getVacanciesByRequirementAllLevel(Requirement requirement) throws ServerException;
    void insertRequirement(Requirement requirement, Vacancy vacancy) throws ServerException;
    void insertRequirementWithRequire(Requirement requirement, Vacancy vacancy) throws ServerException;

}
