package net.thumbtack.school.hiring.dto;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;

public class SkillDtoRequest {
    private String descriptionSkill;
    private int proficiencyLevel;
    private String token;

    public SkillDtoRequest() {
    }

    public SkillDtoRequest(String descriptionSkill, int proficiencyLevel, String token) {
        this.descriptionSkill = descriptionSkill;
        this.proficiencyLevel = proficiencyLevel;
        this.token = token;
    }

    public SkillDtoRequest(int proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    public String getDescriptionSkill() {
        return descriptionSkill;
    }

    public int getProficiencyLevel() {
        return proficiencyLevel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void validate(SkillDtoRequest skillDtoRequest) throws ServerException {
        if (skillDtoRequest.getDescriptionSkill() == null || skillDtoRequest.getDescriptionSkill().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_DESCRIPTION_SKILL);
        }
        if (skillDtoRequest.getProficiencyLevel() == 0) {
            throw new ServerException(ServerErrorCode.WRONG_PROFICIENCY_LEVEL);
        }
    }

    public boolean isEmpty() {
        return (getProficiencyLevel() == 0);
    }
}
