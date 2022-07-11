package net.thumbtack.school.hiring.dto;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;

import java.util.List;

public class VacancyDtoRequest {
    private String positionName;
    private int salary;
    private List<RequirementDtoRequest> requirements;
    private String token;

    public VacancyDtoRequest() {
    }

    public VacancyDtoRequest(String namePosition, int salary, List<RequirementDtoRequest> requirements, String token) {
        this.positionName = namePosition;
        this.salary = salary;
        this.requirements = requirements;
        this.token = token;
    }

    public VacancyDtoRequest(String namePosition, int salary) {
        this.positionName = namePosition;
        this.salary = salary;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNamePosition() {
        return positionName;
    }

    public int getSalary() {
        return salary;
    }

    public List<RequirementDtoRequest> getRequirements() {
        return requirements;
    }

    public void validate(VacancyDtoRequest vacancyDtoRequest) throws ServerException {
        if (vacancyDtoRequest.getNamePosition() == null || vacancyDtoRequest.getNamePosition().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_NAME_POSITION);
        }
        if (vacancyDtoRequest.getSalary() == 0) {
            throw new ServerException(ServerErrorCode.WRONG_SALARY);
        }
        for (RequirementDtoRequest requirement : vacancyDtoRequest.getRequirements()) {
            if (requirement.getDescriptionSkill() == null || requirement.getDescriptionSkill().equals("")) {
                throw new ServerException(ServerErrorCode.WRONG_DESCRIPTION_SKILL);
            }
            if (requirement.getProficiencyLevel() == 0 ) {
                throw new ServerException(ServerErrorCode.WRONG_PROFICIENCY_LEVEL);
            }
            if (requirement.getRequire() == null) {
                throw new ServerException(ServerErrorCode.WRONG_RESPONSE);
            }
        }
    }

    public boolean isEmpty() {
        return (getNamePosition() == null && getSalary() == 0);
    }
}
