package net.thumbtack.school.hiring.service;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.daoimpl.PersonDaoImpl;
import net.thumbtack.school.hiring.dto.*;
import net.thumbtack.school.hiring.users.*;
import org.apache.commons.collections4.ListUtils;

import java.util.*;

public class EmployeeService extends JsonConverter {
    private PersonDaoImpl employeeDao;

    public EmployeeService(PersonDaoImpl employeeDao) {
        this.employeeDao = employeeDao;
    }



    public String registerEmployee(String requestJsonString) {
        Gson gson = new Gson();
        try {
            RegisterEmployeeDtoRequest registerEmployeeDtoRequest = JsonConverter.fromJson(requestJsonString, RegisterEmployeeDtoRequest.class);
            registerEmployeeDtoRequest.validate(registerEmployeeDtoRequest);
            Person person = new Employee(registerEmployeeDtoRequest.getFirstName(), registerEmployeeDtoRequest.getMiddleName(), registerEmployeeDtoRequest.getLastName(), registerEmployeeDtoRequest.getEmail(), registerEmployeeDtoRequest.getLogin(), registerEmployeeDtoRequest.getPassword());
            String token = UUID.randomUUID().toString();
            employeeDao.insert(person, token);
            return gson.toJson(new TokenDtoResponse(token));
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getErrorCode().getMessage());
        }
    }

    public String addSummary(String requestJsonString) {
        Employee employee = null;
        try {
            SkillDtoRequest skillDtoRequest = JsonConverter.fromJson(requestJsonString, SkillDtoRequest.class);
            skillDtoRequest.validate(skillDtoRequest);
            if (employeeDao.getPersonByToken(skillDtoRequest.getToken()).getClass() == Employee.class) {
                employee = (Employee) employeeDao.getPersonByToken(skillDtoRequest.getToken());
            }
            Skill skill = new Skill(skillDtoRequest.getDescriptionSkill(), skillDtoRequest.getProficiencyLevel());
            employeeDao.insertSkill(skillDtoRequest.getDescriptionSkill());
            employeeDao.insertSkills(skill, employee);
            employee.addSummary(skill);
            return EmptyDtoReponse.getJsonStringResponse();
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getMessage());
        }
    }

    public String getVacancy(String requestJsonString) {
        Gson gson = new Gson();
        int i = 0;
        Employee employee = null;
        Collection<Vacancy> receivedVacancies = null;
        Collection<Vacancy> secondList = null;
        try {
            TokenDtoResponse token = gson.fromJson(requestJsonString, TokenDtoResponse.class);
            if (employeeDao.getPersonByToken(token.getToken()).getClass() == Employee.class) {
                employee = (Employee) employeeDao.getPersonByToken(token.getToken());
            }
            for (Skill skill: employee.getSummaryList()) {
                if (i == 0) {
                    i++;
                    receivedVacancies = employeeDao.getVacanciesByRequirement(new Requirement(skill.getDescriptionSkill(), skill.getProficiencyLevel(), Require.YES));
                } else {
                    secondList = ListUtils.retainAll(receivedVacancies, employeeDao.getVacanciesByRequirement(new Requirement(skill.getDescriptionSkill(), skill.getProficiencyLevel(), Require.YES)));
                }
            }
            return gson.toJson(secondList);
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getMessage());
        }
    }

    public String getVacancyWithRequire(String requestJsonString) {
        Gson gson = new Gson();
        int i = 0;
        Employee employee = null;
        Collection<Vacancy> receivedVacancies = null;
        Collection<Vacancy> secondList = null;
        try {
            TokenDtoResponse token = gson.fromJson(requestJsonString, TokenDtoResponse.class);
            if (employeeDao.getPersonByToken(token.getToken()).getClass() == Employee.class) {
                employee = (Employee) employeeDao.getPersonByToken(token.getToken());
            }
            for (Skill skill: employee.getSummaryList()) {
                if (i == 0) {
                    i++;
                    receivedVacancies = employeeDao.getVacanciesByRequirementWithRequire(new Requirement(skill.getDescriptionSkill(), skill.getProficiencyLevel(), Require.YES));
                } else {
                    secondList = ListUtils.retainAll(receivedVacancies, employeeDao.getVacanciesByRequirement(new Requirement(skill.getDescriptionSkill(), skill.getProficiencyLevel(), Require.YES)));
                }
            }
            return gson.toJson(secondList);
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getMessage());
        }
    }

    public String getVacancyAllLevel(String requestJsonString) {
        Gson gson = new Gson();
        int i = 0;
        Employee employee = null;
        Collection<Vacancy> receivedVacancies = null;
        try {
            TokenDtoResponse token = gson.fromJson(requestJsonString, TokenDtoResponse.class);
            if (employeeDao.getPersonByToken(token.getToken()).getClass() == Employee.class) {
                employee = (Employee) employeeDao.getPersonByToken(token.getToken());
            }
            for (Skill skill: employee.getSummaryList()) {
                if (i == 0) {
                    i++;
                    receivedVacancies = employeeDao.getVacanciesByRequirementAllLevel(new Requirement(skill.getDescriptionSkill(), skill.getProficiencyLevel(), Require.YES));
                } else {
                    Collection<Vacancy> receivedVacancies2 = employeeDao.getVacanciesByRequirementAllLevel(new Requirement(skill.getDescriptionSkill(), skill.getProficiencyLevel(), Require.YES));
                    receivedVacancies.retainAll(receivedVacancies2);
                }
            }
            return gson.toJson(receivedVacancies);
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getMessage());
        }
    }

    public String getVacancyOnlyOneRequirement(String requestJsonString) {
        Gson gson = new Gson();
        Employee employee = null;
        List<Vacancy> vacancies = new ArrayList<>();
        int i = 0;
        try {
            TokenDtoResponse token = gson.fromJson(requestJsonString, TokenDtoResponse.class);
            if (employeeDao.getPersonByToken(token.getToken()).getClass() == Employee.class) {
                employee = (Employee) employeeDao.getPersonByToken(token.getToken());
            }
            for (Skill skill: employee.getSummaryList()) {
                vacancies.addAll(employeeDao.getVacanciesByRequirement(new Requirement(skill.getDescriptionSkill(), skill.getProficiencyLevel(), Require.YES)));
            }
            Map<Vacancy, Integer> result = new HashMap<>();
            vacancies.forEach(v -> result.merge(v, 1, Integer::sum));
            SortedSet<Map.Entry<Vacancy, Integer>> sortedset = new TreeSet<Map.Entry<Vacancy, Integer>>(
                    new Comparator<Map.Entry<Vacancy, Integer>>() {
                        @Override public int compare(Map.Entry<Vacancy,Integer> e1, Map.Entry<Vacancy,Integer> e2) {
                            int res = e2.getValue().compareTo(e1.getValue());
                            return res != 0 ? res : 1;
                        }
                    });
            sortedset.addAll(result.entrySet());
            ImmutableSet<Vacancy> finalSet = ImmutableMap.copyOf(sortedset).keySet();
            VacanciesListResponse vil = new VacanciesListResponse(finalSet.toArray());
            return gson.toJson(vil);
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getMessage());
        }
    }

    public String editDataEmployee(String requestJsonString) {
        Gson gson = new Gson();
        Employee employee = null;
        try {
            TokenDtoResponse token = gson.fromJson(requestJsonString, TokenDtoResponse.class);
            EditDataEmployeeDtoRequest editDataEmployeeDtoRequest = gson.fromJson(requestJsonString, EditDataEmployeeDtoRequest.class);
            editDataEmployeeDtoRequest.validate(editDataEmployeeDtoRequest);
            if (employeeDao.getPersonByToken(token.getToken()).getClass() == Employee.class) {
                employee = (Employee) employeeDao.getPersonByToken(token.getToken());
            }
            employee.editDataEmployee(editDataEmployeeDtoRequest.getFirstName(), editDataEmployeeDtoRequest.getMiddleName(), editDataEmployeeDtoRequest.getLastName(), editDataEmployeeDtoRequest.getEmail(), editDataEmployeeDtoRequest.getPassword());
            return EmptyDtoReponse.getJsonStringResponse();
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getErrorCode().getMessage());
        }
    }

    public String editSummary(String requestJsonString) throws ServerException {
        Gson gson = new Gson();
        Employee employee = null;
        try {
            String keySummary = null;
            EditSummaryDtoRequest editSummaryDtoRequest = gson.fromJson(requestJsonString, EditSummaryDtoRequest.class);
            TokenDtoRequest token = gson.fromJson(requestJsonString, TokenDtoRequest.class);
            if (employeeDao.getPersonByToken(token.getToken()).getClass() == Employee.class) {
                employee = (Employee) employeeDao.getPersonByToken(token.getToken());
            }
            for (String key : editSummaryDtoRequest.getEditSummaryDtoRequestMap().keySet()) {
                keySummary = key;
            }
            SkillDtoRequest editSummary = editSummaryDtoRequest.getEditSummaryDtoRequestMap().get(keySummary);
            Skill skill = employee.getSummaryByDescriptionSkill(keySummary);
            if (editSummary.isEmpty()) {
                employee.getSummaryList().remove(skill);
            } else if (skill.isEmpty() && !editSummary.isEmpty()) {
                employee.getSummaryList().add(new Skill(keySummary, editSummary.getProficiencyLevel()));
                employeeDao.insertSkill(keySummary);
            } else  {
                skill.editSkill(editSummary.getProficiencyLevel());
            }
            return EmptyDtoReponse.getJsonStringResponse();
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getErrorCode().getMessage());
        }
    }

    public String makeActive(String requestJsonString) {
        Gson gson = new Gson();
        Employee employee = null;
        try {
            TokenDtoRequest token = gson.fromJson(requestJsonString, TokenDtoRequest.class);
            if (employeeDao.getPersonByToken(token.getToken()).getClass() == Employee.class) {
                employee = (Employee) employeeDao.getPersonByToken(token.getToken());
            }
            if (!employee.isStatus()) {
                employee.setStatus(true);
            }
            return EmptyDtoReponse.getJsonStringResponse();
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getErrorCode().getMessage());
        }
    }

    public String makeInactive(String requestJsonString) {
        Gson gson = new Gson();
        Employee employee = null;
        try {
            TokenDtoRequest token = gson.fromJson(requestJsonString, TokenDtoRequest.class);
            if (employeeDao.getPersonByToken(token.getToken()).getClass() == Employee.class) {
                employee = (Employee) employeeDao.getPersonByToken(token.getToken());
            }
            if (employee.isStatus()) {
                employee.setStatus(false);
            }
            return EmptyDtoReponse.getJsonStringResponse();
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getErrorCode().getMessage());
        }
    }
}
