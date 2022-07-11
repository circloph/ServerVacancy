// REVU переименуйте пакет в net.thumbtack.school.hiring.model
// с какой стати Vacancy находится в users ?
package net.thumbtack.school.hiring.users;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Vacancy implements  Serializable{
    private String positionName;
    private int salary;
    private List<Requirement> requirements;
    private boolean status = true;


    public Vacancy(String namePosition, int salary, List<Requirement> requirements) {
        this.positionName = namePosition;
        this.salary = salary;
        this.requirements = requirements;
    }

    public Vacancy() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNamePosition() {
        return positionName;
    }

    public void setNamePosition(String namePosition) {
        this.positionName = namePosition;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    public void editVacancy(String namePosition, int salary) {
        if (namePosition != null) {
            setNamePosition(namePosition);
        }
        if (salary != 0) {
            setSalary(salary);
        }

    }

    public Requirement getRequirementByNamePosition(String descriptionSkill) {
        Requirement foundRequirement = new Requirement();
        if (getRequirements() != null) {
            for (Requirement requirement : getRequirements()) {
                if (requirement.getDescriptionSkill().equals(descriptionSkill)) {
                    foundRequirement = new Requirement(requirement.getDescriptionSkill(), requirement.getProficiencyLevel(), requirement.getRequire());
                }
            }
        }
        return foundRequirement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(positionName, vacancy.positionName) &&
                Objects.equals(salary, vacancy.salary) &&
                Objects.equals(requirements, vacancy.requirements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionName, salary, requirements);
    }
}
