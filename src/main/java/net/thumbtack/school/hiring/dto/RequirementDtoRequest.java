package net.thumbtack.school.hiring.dto;

import net.thumbtack.school.hiring.users.Require;

public class RequirementDtoRequest {
    private String descriptionSkill;
    private int proficiencyLevel;
    private Require require;

    public RequirementDtoRequest(String descriptionSkill, int proficiencyLevel, Require require) {
        this.descriptionSkill = descriptionSkill;
        this.proficiencyLevel = proficiencyLevel;
        this.require = require;
    }

    public String getDescriptionSkill() {
        return descriptionSkill;
    }

    public int getProficiencyLevel() {
        return proficiencyLevel;
    }

    public Require getRequire() {
        return require;
    }

    public boolean isEmpty() {
        return (getDescriptionSkill() == null && getProficiencyLevel() == 0 && getRequire() == null);
    }
}
