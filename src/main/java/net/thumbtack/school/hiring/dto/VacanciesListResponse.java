package net.thumbtack.school.hiring.dto;


public class VacanciesListResponse {
    private Object[] vacancies;

    public VacanciesListResponse(Object[] vacancies) {
        this.vacancies = vacancies;
    }

    public Object[] getVacancies() {
        return vacancies;
    }

    public void setVacancies(Object[] vacancies) {
        this.vacancies = vacancies;
    }
}
