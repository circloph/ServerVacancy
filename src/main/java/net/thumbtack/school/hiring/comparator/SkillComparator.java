package net.thumbtack.school.hiring.comparator;

import net.thumbtack.school.hiring.users.Skill;

import java.io.Serializable;
import java.util.Comparator;

public class SkillComparator implements Comparator<Skill>, Serializable {
    @Override
    public int compare(Skill o1, Skill o2) {
        int compInt = o1.getDescriptionSkill().compareTo(o2.getDescriptionSkill());
        if (compInt != 0) {
            return compInt;
        } else {
            return Integer.compare(o1.getProficiencyLevel(), o2.getProficiencyLevel());
        }
    }
}
