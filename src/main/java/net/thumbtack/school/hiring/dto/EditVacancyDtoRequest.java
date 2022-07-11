package net.thumbtack.school.hiring.dto;

import java.util.Map;

public class EditVacancyDtoRequest {
    private Map<String, VacancyDtoRequest> editVacancy;
    private Map<String, RequirementDtoRequest> editRequirement;
    private String token;

    public EditVacancyDtoRequest(Map<String, VacancyDtoRequest> editVacancy, Map<String, RequirementDtoRequest> editRequirement, String token) {
        this.editVacancy = editVacancy;
        this.editRequirement = editRequirement;
        this.token = token;
    }

    public Map<String, VacancyDtoRequest> getEditVacancy() {
        return editVacancy;
    }

    public Map<String, RequirementDtoRequest> getEditRequirement() {
        return editRequirement;
    }

    public String getToken() {
        return token;
    }

}
