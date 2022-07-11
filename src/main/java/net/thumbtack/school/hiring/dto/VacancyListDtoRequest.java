package net.thumbtack.school.hiring.dto;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.users.Vacancy;

import java.util.List;

public class VacancyListDtoRequest {
    private List<Vacancy> vacancies;

    public VacancyListDtoRequest(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public VacancyListDtoRequest() {
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public static String getListVacancyJson(VacancyListDtoRequest vacancyListDtoRequest) {
        Gson gson = new Gson();
        return gson.toJson(vacancyListDtoRequest);
    }
}
