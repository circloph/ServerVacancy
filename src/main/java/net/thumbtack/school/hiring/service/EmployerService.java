package net.thumbtack.school.hiring.service;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.daoimpl.PersonDaoImpl;
import net.thumbtack.school.hiring.dto.*;
import net.thumbtack.school.hiring.users.*;
import org.apache.commons.collections4.ListUtils;

import java.util.*;

public class EmployerService extends JsonConverter {
    private PersonDaoImpl personDao;

    public EmployerService(PersonDaoImpl personDao) {
        this.personDao = personDao;
    }

    public String registerEmployer(String requestJsonString) throws ServerException {
        Gson gson = new Gson();
        try {
            RegisterEmployerDtoRequest registerEmployerDtoRequest = JsonConverter.fromJson(requestJsonString, RegisterEmployerDtoRequest.class);
            registerEmployerDtoRequest.validate(registerEmployerDtoRequest);
            Person person = new Employer(registerEmployerDtoRequest.getNameCompany(), registerEmployerDtoRequest.getAddressCompany(), registerEmployerDtoRequest.getEmail(), registerEmployerDtoRequest.getFirstName(), registerEmployerDtoRequest.getMiddleName(), registerEmployerDtoRequest.getLastName(), registerEmployerDtoRequest.getLogin(), registerEmployerDtoRequest.getPassword());
            String token = UUID.randomUUID().toString();
            personDao.insert(person, token);
            return gson.toJson(new TokenDtoResponse(token));
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getMessage());
        }
    }

    public String addVacancy(String requestJsonString) {
        Employer employer = null;
        Gson gson = new Gson();
        List<Requirement> requirements = new ArrayList<>();
        try {
            VacancyDtoRequest vacancyDtoRequest = JsonConverter.fromJson(requestJsonString, VacancyDtoRequest.class);
            vacancyDtoRequest.validate(vacancyDtoRequest);
            if (personDao.getPersonByToken(vacancyDtoRequest.getToken()).getClass() == Employer.class) {
                employer = (Employer) personDao.getPersonByToken(vacancyDtoRequest.getToken());
            }
            for (RequirementDtoRequest requirement : vacancyDtoRequest.getRequirements()) {
                requirements.add(new Requirement(requirement.getDescriptionSkill(), requirement.getProficiencyLevel(), requirement.getRequire()));
            }
            Vacancy vacancy = new Vacancy(vacancyDtoRequest.getNamePosition(), vacancyDtoRequest.getSalary(), requirements);
            for (Requirement requirement : requirements) {
                personDao.insertSkill(requirement.getDescriptionSkill());
                personDao.insertRequirement(requirement, vacancy);
                if (requirement.getRequire().equals(Require.YES)) {
                    personDao.insertRequirementWithRequire(requirement, vacancy);
                }
            }
            employer.addVacancy(vacancy);
            return EmptyDtoReponse.getJsonStringResponse();
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getMessage());
        }
    }


    public String getSkill(String requestJsonString) throws ServerException {
        Gson gson = new Gson();
        Employer employer  = null;
        Collection<Person> receivedSet = null;
        Collection<Person> secondSet = new ArrayList<>();
        int k = 0;
        try {
            VacancyDtoRequest vacancyDtoRequest = gson.fromJson(requestJsonString, VacancyDtoRequest.class);
            TokenDtoRequest token = gson.fromJson(requestJsonString, TokenDtoRequest.class);
            if (personDao.getPersonByToken(token.getToken()).getClass() == Employer.class) {
                employer = (Employer) personDao.getPersonByToken(token.getToken());
            }
            for (RequirementDtoRequest requirement : vacancyDtoRequest.getRequirements()) {
                if (k == 0) {
                    k++;
                    receivedSet = personDao.getPersonsBySkill(new Skill(requirement.getDescriptionSkill(), requirement.getProficiencyLevel()));
                } else {
                    secondSet = ListUtils.retainAll(receivedSet, personDao.getPersonsBySkill(new Skill(requirement.getDescriptionSkill(), requirement.getProficiencyLevel())));
                }
            }
            return gson.toJson(secondSet);
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getMessage());
        }
    }

    public String getSkillWithRequire(String requestJsonString) throws ServerException {
        Gson gson = new Gson();
        Employer employer  = null;
        Collection<Person> receivedSet = null;
        int k = 0;
        try {
            VacancyDtoRequest vacancyDtoRequest = gson.fromJson(requestJsonString, VacancyDtoRequest.class);
            TokenDtoRequest token = gson.fromJson(requestJsonString, TokenDtoRequest.class);
            if (personDao.getPersonByToken(token.getToken()).getClass() == Employer.class) {
                employer = (Employer) personDao.getPersonByToken(token.getToken());
            }
            for (RequirementDtoRequest requirement : vacancyDtoRequest.getRequirements()) {
                if (requirement.getRequire().equals(Require.YES)) {
                    if (k == 0) {
                        k++;
                        receivedSet = personDao.getPersonsBySkill(new Skill(requirement.getDescriptionSkill(), requirement.getProficiencyLevel()));
                    } else {
                        receivedSet.retainAll(personDao.getPersonsBySkill(new Skill(requirement.getDescriptionSkill(), requirement.getProficiencyLevel())));
                    }
                }
            }
            return gson.toJson(receivedSet);
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getMessage());
        }
    }

    public String getSkillAllLevel(String requestJsonString) throws ServerException {
        Gson gson = new Gson();
        Employer employer  = null;
        Collection<Person> receivedSet = null;
        Collection<Person> secondSet = new ArrayList<>();
        int k = 0;
        try {
            VacancyDtoRequest vacancyDtoRequest = gson.fromJson(requestJsonString, VacancyDtoRequest.class);
            TokenDtoRequest token = gson.fromJson(requestJsonString, TokenDtoRequest.class);
            if (personDao.getPersonByToken(token.getToken()).getClass() == Employer.class) {
                employer = (Employer) personDao.getPersonByToken(token.getToken());
            }
            for (RequirementDtoRequest requirement : vacancyDtoRequest.getRequirements()) {
                if (k == 0) {
                    k++;
                    receivedSet = personDao.getPersonsBySkillAllLevel(new Skill(requirement.getDescriptionSkill(), requirement.getProficiencyLevel()));
                } else {
                    secondSet = ListUtils.retainAll(receivedSet, personDao.getPersonsBySkillAllLevel(new Skill(requirement.getDescriptionSkill(), requirement.getProficiencyLevel())));
                }
            }
            return gson.toJson(secondSet);
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getMessage());
        }
    }


    public String getSummaryOnlyOneRequirement(String requestJsonString) throws ServerException {
        Gson gson = new Gson();
        Employer employer  = null;
        Collection<Person> receivedSet = null;
        Collection<Person> secondSet = new ArrayList<>();
        int k = 0;
        try {
            VacancyDtoRequest vacancyDtoRequest = gson.fromJson(requestJsonString, VacancyDtoRequest.class);
            TokenDtoRequest token = gson.fromJson(requestJsonString, TokenDtoRequest.class);
            if (personDao.getPersonByToken(token.getToken()).getClass() == Employer.class) {
                employer = (Employer) personDao.getPersonByToken(token.getToken());
            }
            for (RequirementDtoRequest requirement : vacancyDtoRequest.getRequirements()) {
                if (k == 0) {
                    k++;
                    receivedSet = personDao.getPersonsBySkill(new Skill(requirement.getDescriptionSkill(), requirement.getProficiencyLevel()));
                } else {
                    secondSet = personDao.getPersonsBySkill(new Skill(requirement.getDescriptionSkill(), requirement.getProficiencyLevel()));
                    receivedSet.addAll(secondSet);
                }
            }
            return gson.toJson(receivedSet);
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getMessage());
        }
    }

    public String editDataEmployer(String requestJsonString) {
        Gson gson = new Gson();
        Employer employer = null;
        try {
            TokenDtoResponse token = gson.fromJson(requestJsonString, TokenDtoResponse.class);
            EditDataEmployerDtoRequest editDataEmployerDtoRequest = JsonConverter.fromJson(requestJsonString, EditDataEmployerDtoRequest.class);
            editDataEmployerDtoRequest.validate(editDataEmployerDtoRequest);
            if (personDao.getPersonByToken(token.getToken()).getClass() == Employer.class) {
                employer = (Employer) personDao.getPersonByToken(token.getToken());
            }
            employer.editDataEmployer(editDataEmployerDtoRequest.getNameCompany(), editDataEmployerDtoRequest.getAddressCompany(), editDataEmployerDtoRequest.getFirstName(), editDataEmployerDtoRequest.getMiddleName(), editDataEmployerDtoRequest.getLastName(), editDataEmployerDtoRequest.getEmail(), editDataEmployerDtoRequest.getPassword());
            return EmptyDtoReponse.getJsonStringResponse();
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getErrorCode().getMessage());
        }

    }

        public String editVacancy(String requestJsonString) {
        Gson gson = new Gson();
        Employer employer = null;
        try {
            String vacancyKey = null;
            String requirementKey = null;
            EditVacancyDtoRequest editVacancyDtoRequest = gson.fromJson(requestJsonString, EditVacancyDtoRequest.class);
            String token = editVacancyDtoRequest.getToken();
            if (personDao.getPersonByToken(token).getClass() == Employer.class) {
                employer = (Employer) personDao.getPersonByToken(token);
            }
            Set<String> keysVacancy = editVacancyDtoRequest.getEditVacancy().keySet();
            for (String s : keysVacancy) {
                vacancyKey = s;
            }
            Set<String> keyRequirement = editVacancyDtoRequest.getEditRequirement().keySet();
            for (String s : keyRequirement) {
                requirementKey = s;
            }
            Vacancy vacancy = employer.getVacancyByNamePosition(vacancyKey);
            VacancyDtoRequest vacancyDtoRequest = editVacancyDtoRequest.getEditVacancy().get(vacancyKey);
            Requirement requirement = vacancy.getRequirementByNamePosition(requirementKey);
            RequirementDtoRequest requirementDtoRequest = editVacancyDtoRequest.getEditRequirement().get(requirementKey);
            if (vacancyDtoRequest.isEmpty() && requirementDtoRequest.isEmpty() && requirement.isEmpty()) {
                employer.removeVacancy(vacancy);
            } else if (requirement.isEmpty() && !requirementDtoRequest.isEmpty()) {
                vacancy.getRequirements().add(new Requirement(requirementDtoRequest.getDescriptionSkill(), requirementDtoRequest.getProficiencyLevel(), requirementDtoRequest.getRequire()));
                personDao.insertSkill(requirementDtoRequest.getDescriptionSkill());
            } else if (vacancyDtoRequest.isEmpty() && requirementDtoRequest.isEmpty()) {
                vacancy.getRequirements().remove(requirement);
            } else {
                vacancy.editVacancy(vacancyDtoRequest.getNamePosition(), vacancyDtoRequest.getSalary());
                requirement.editRequirement(requirementDtoRequest.getDescriptionSkill(), requirementDtoRequest.getProficiencyLevel(), requirementDtoRequest.getRequire());
            }
            return EmptyDtoReponse.getJsonStringResponse();
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getErrorCode().getMessage());
        }
    }

    public String makeVacancyActive(String requestJsonString) {
        Gson gson = new Gson();
        Employer employer = null;
        try {
            EditVacancyStatus editVacancyStatus = gson.fromJson(requestJsonString, EditVacancyStatus.class);
            if (personDao.getPersonByToken(editVacancyStatus.getToken()).getClass() == Employer.class) {
                employer = (Employer) personDao.getPersonByToken(editVacancyStatus.getToken());
            }
            Vacancy vacancy = employer.getVacancyByNamePosition(editVacancyStatus.getNamePosition());
            if (!vacancy.isStatus()) {
                vacancy.setStatus(true);
            }
            return EmptyDtoReponse.getJsonStringResponse();
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getErrorCode().getMessage());
        }
    }

    public String makeVacancyInactive(String requestJsonString) {
        Gson gson = new Gson();
        Employer employer = null;
        try {
            EditVacancyStatus editVacancyStatus = gson.fromJson(requestJsonString, EditVacancyStatus.class);
            if (personDao.getPersonByToken(editVacancyStatus.getToken()).getClass() == Employer.class) {
                employer = (Employer) personDao.getPersonByToken(editVacancyStatus.getToken());
            }
            Vacancy vacancy = employer.getVacancyByNamePosition(editVacancyStatus.getNamePosition());
            if (vacancy.isStatus()) {
                vacancy.setStatus(false);
            }
            return EmptyDtoReponse.getJsonStringResponse();
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getErrorCode().getMessage());
        }
    }

    public String getAllVacancy(String requestJsonString) throws ServerException {
        Gson gson = new Gson();
        Employer employer = null;
        try {
            TokenDtoRequest tokenDtoRequest = gson.fromJson(requestJsonString, TokenDtoRequest.class);
            if (personDao.getPersonByToken(tokenDtoRequest.getToken()).getClass() == Employer.class) {
                employer = (Employer) personDao.getPersonByToken(tokenDtoRequest.getToken());
            }
            VacancyListDtoRequest vacancyListDtoRequest = new VacancyListDtoRequest(employer.getVacancies());
            return VacancyListDtoRequest.getListVacancyJson(vacancyListDtoRequest);
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getErrorCode().getMessage());
        }
    }

    public String getVacancyOnlyActive(String requestJsonString){
        Gson gson = new Gson();
        Employer employer = null;
        try {
            TokenDtoRequest tokenDtoRequest = gson.fromJson(requestJsonString, TokenDtoRequest.class);
            if (personDao.getPersonByToken(tokenDtoRequest.getToken()).getClass() == Employer.class) {
                employer = (Employer) personDao.getPersonByToken(tokenDtoRequest.getToken());
            }
            List<Vacancy> vacancyList = new ArrayList<>();
            for (Vacancy vacancy : employer.getVacancies()) {
                if (vacancy.isStatus()) {
                    vacancyList.add(vacancy);
                }
            }
            VacancyListDtoRequest vacancyListDtoRequest = new VacancyListDtoRequest(vacancyList);
            return VacancyListDtoRequest.getListVacancyJson(vacancyListDtoRequest);
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getErrorCode().getMessage());
        }
    }

    public String getVacancyOnlyInactive(String requestJsonString){
        Gson gson = new Gson();
        Employer employer = null;
        try {
            TokenDtoRequest tokenDtoRequest = gson.fromJson(requestJsonString, TokenDtoRequest.class);
            if (personDao.getPersonByToken(tokenDtoRequest.getToken()).getClass() == Employer.class) {
                employer = (Employer) personDao.getPersonByToken(tokenDtoRequest.getToken());
            }
            List<Vacancy> vacancyList = new ArrayList<>();
            for (Vacancy vacancy : employer.getVacancies()) {
                if (!vacancy.isStatus()) {
                    vacancyList.add(vacancy);
                }
            }
            VacancyListDtoRequest vacancyListDtoRequest = new VacancyListDtoRequest(vacancyList);
            return VacancyListDtoRequest.getListVacancyJson(vacancyListDtoRequest);
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getErrorCode().getMessage());
        }
    }
}
