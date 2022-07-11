package net.thumbtack.school.hiring.users;

import java.io.Serializable;
import java.util.Objects;

public class Skill implements  Serializable {
    private String descriptionSkill;
    private int proficiencyLevel;

    public Skill(String descriptionSkill, int proficiencyLevel) {
        this.descriptionSkill = descriptionSkill;
        this.proficiencyLevel = proficiencyLevel;
    }

    public Skill() {
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

    public void editSkill(int proficiencyLevel) {
        if (proficiencyLevel != 0) {
            setProficiencyLevel(proficiencyLevel);
        }
    }

    public boolean isEmpty() {
        return (getProficiencyLevel() == 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(descriptionSkill, skill.descriptionSkill) &&
                Objects.equals(proficiencyLevel, skill.proficiencyLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptionSkill, proficiencyLevel);
    }




    @Override
    public String toString() {
        return "Skill{" +
                "descriptionSkill='" + descriptionSkill + '\'' +
                ", proficiencyLevel=" + proficiencyLevel +
                '}';
    }
}
