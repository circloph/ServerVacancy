package net.thumbtack.school.hiring.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employee extends Person implements Serializable {
    private List<Skill> skillList;
    private boolean status = true;

    public Employee(String firstName, String middleName, String lastName, String email, String login, String password) {
        super(firstName, middleName, lastName, email, login, password);
        skillList = new ArrayList<>();
    }

    public Employee(String firstName, String lastName, String email, String login, String password) {
        super(firstName, lastName, email, login, password);
        skillList = new ArrayList<>();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Skill> getSummaryList() {
        return skillList;
    }

    public void setSummaryList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public void addSummary(Skill skill) {
        skillList.add(skill);
    }

    public void editDataEmployee(String firstName, String middleName, String lastName, String email, String password) {
        if (firstName != null) {
            setFirstName(firstName);
        }

        if (middleName != null) {
            setMiddleName(middleName);
        }

        if (lastName != null) {
            setLastName(lastName);
        }

        if (email != null) {
            setEmail(email);
        }

        if (password != null) {
            setPassword(password);
        }

    }

    public Skill getSummaryByDescriptionSkill(String descriptionSkill) {
        Skill foundSkill = new Skill();
        for (Skill skill : getSummaryList()) {
            if (skill.getDescriptionSkill().equals(descriptionSkill)) {
                foundSkill = new Skill(skill.getDescriptionSkill(), skill.getProficiencyLevel());
            }
        }
        return foundSkill;
    }

    public void removeSummary(Skill skill) {
        getSummaryList().remove(skill);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(skillList, employee.skillList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), skillList);
    }

}
