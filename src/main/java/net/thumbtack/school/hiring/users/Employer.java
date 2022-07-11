package net.thumbtack.school.hiring.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employer extends Person implements Serializable {
    private String companyName;
    private String companyAddress;
    private List<Vacancy> vacancies;

    public Employer(String companyName, String companyAddress, String email, String firstName, String middleName, String lastName, String login, String password) {
        super(firstName, middleName, lastName, email, login, password);
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        vacancies = new ArrayList<>();
    }

    public Employer(String companyName, String companyAddress, String email, String firstName, String lastName, String login, String password) {
        super(firstName, lastName, email, login, password);
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        vacancies = new ArrayList<>();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public void addVacancy(Vacancy vacancy) {
        vacancies.add(vacancy);
    }


    public void editDataEmployer(String nameCompany, String addressCompany, String firstName, String middleName, String lastName, String email, String password) {
        if (nameCompany != null) {
            setCompanyName(nameCompany);
        }

        if (addressCompany != null) {
            setCompanyAddress(addressCompany);
        }

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

    public Vacancy getVacancyByNamePosition(String namePosition) {
        Vacancy foundVacancy = new Vacancy();
            for (Vacancy vacancy : getVacancies()) {
                if (vacancy.getNamePosition().equals(namePosition)) {
                    foundVacancy = vacancy;
                }
            }
        return foundVacancy;
    }

    public void removeVacancy(Vacancy vacancy) {
        getVacancies().remove(vacancy);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employer employer = (Employer) o;
        return Objects.equals(companyName, employer.companyName) &&
                Objects.equals(companyAddress, employer.companyAddress) &&
                Objects.equals(vacancies, employer.vacancies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), companyName, companyAddress, vacancies);
    }

}
