package net.thumbtack.school.hiring.comparator;

import net.thumbtack.school.hiring.users.Requirement;

import java.io.Serializable;
import java.util.Comparator;

public class RequirementComparator implements Comparator<Requirement>, Serializable {

    @Override
    public int compare(Requirement o1, Requirement o2) {
        int compInt = o1.getDescriptionSkill().compareTo(o2.getDescriptionSkill());
        if (compInt != 0) {
            return compInt;
        } else {
            return Integer.compare(o1.getProficiencyLevel(), o2.getProficiencyLevel());
        }
    }
}
