// REVU переименуйте пакет
// например net.thumbtack.school.hiring.model
// тут не только юзеры
package net.thumbtack.school.hiring.users;

import java.io.Serializable;
import java.util.Objects;

public class Requirement implements Serializable {
    private String descriptionSkill;
    private int proficiencyLevel;
    private Require require;

    public Requirement(String descriptionSkill, int proficiencyLevel, Require require) {
        this.descriptionSkill = descriptionSkill;
        this.proficiencyLevel = proficiencyLevel;
        this.require = require;
    }

    public Requirement() {
    }

    public String getDescriptionSkill() {
        return descriptionSkill;
    }

    public void setDescriptionSkill(String descriptionSkill) {
        this.descriptionSkill = descriptionSkill;
    }

    public int getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(int proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    public Require getRequire() {
        return require;
    }

    public void setRequire(Require require) {
        this.require = require;
    }

    public void editRequirement(String descriptionSkill, int proficiencyLevel, Require require) {
        if (descriptionSkill != null) {
            setDescriptionSkill(descriptionSkill);
        }
        if (proficiencyLevel != 0) {
            setProficiencyLevel(proficiencyLevel);
        }
        if (require != null) {
            setRequire(require);
        }
    }

    public boolean isEmpty() {
        return (getDescriptionSkill() == null && getProficiencyLevel() == 0 && getRequire() == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Requirement that = (Requirement) o;
        return Objects.equals(descriptionSkill, that.descriptionSkill) &&
                Objects.equals(proficiencyLevel, that.proficiencyLevel) &&
                Objects.equals(require, that.require);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptionSkill, proficiencyLevel, require);
    }

}
