package net.thumbtack.school.hiring;

import com.google.common.collect.ImmutableSet;
import com.google.gson.Gson;
import net.thumbtack.school.hiring.dto.*;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.users.Require;
import net.thumbtack.school.hiring.users.Employer;
import net.thumbtack.school.hiring.users.Requirement;
import net.thumbtack.school.hiring.users.Vacancy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVacancy {
    Server server = new Server();
    Gson gson = new Gson();

    @Test
    public void testAddVacancy() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employer = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "Jek1", "1qaz2wsx&3edc");
        String jsonEmployer = gson.toJson(employer);
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(jsonEmployer), TokenDtoResponse.class);
        RequirementDtoRequest requirement = new RequirementDtoRequest("Java", 4, Require.YES);
        List<RequirementDtoRequest> requirements = new ArrayList<>();
        requirements.add(requirement);
        VacancyDtoRequest vacancy = new VacancyDtoRequest("Стажер", 15000, requirements, token.getToken());
        String jsonVacancy = gson.toJson(vacancy);
        String jsonString = "";
        assertEquals(server.addVacancy(jsonVacancy), gson.toJson(jsonString));
    }

    @Test
    public void testGetVacancy() throws ServerException, IOException { //ok
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "Jek1", "1qaz2wsx&3edc");
        RegisterEmployerDtoRequest employer2 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Артем", "Игоревич", "Безухов", "jack", "1qaz2wsx&3edc");
        RegisterEmployerDtoRequest employer3 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Антон", "Александрович", "Крутов", "Horhe", "1qaz2wsx&3edc");
        RegisterEmployerDtoRequest employer4 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Семен", "Васильевич", "Болотов", "molod", "1qaz2wsx&3edc");
        String jsonEmployer1 = gson.toJson(employer1);
        String jsonEmployer2 = gson.toJson(employer2);
        String jsonEmployer3 = gson.toJson(employer3);
        String jsonEmployer4 = gson.toJson(employer4);
        TokenDtoResponse token1 = gson.fromJson(server.registerEmployer(jsonEmployer1), TokenDtoResponse.class);
        TokenDtoResponse token2 = gson.fromJson(server.registerEmployer(jsonEmployer2), TokenDtoResponse.class);
        TokenDtoResponse token3 = gson.fromJson(server.registerEmployer(jsonEmployer3), TokenDtoResponse.class);
        TokenDtoResponse token4 = gson.fromJson(server.registerEmployer(jsonEmployer4), TokenDtoResponse.class);
        RequirementDtoRequest requirement1 = new RequirementDtoRequest("Java", 3, Require.YES);
        RequirementDtoRequest requirement2 = new RequirementDtoRequest("Pascal", 2, Require.NO);
        RequirementDtoRequest requirement3 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement4 = new RequirementDtoRequest("JavaScript", 3, Require.NO);
        RequirementDtoRequest requirement5 = new RequirementDtoRequest("Java", 2, Require.YES);
        RequirementDtoRequest requirement6 = new RequirementDtoRequest("Pascal", 1, Require.NO);
        RequirementDtoRequest requirement7 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement8 = new RequirementDtoRequest("JavaScript", 3, Require.YES);
        List<RequirementDtoRequest> list1 = new ArrayList<>();
        List<RequirementDtoRequest> list2 = new ArrayList<>();
        List<RequirementDtoRequest> list3 = new ArrayList<>();
        List<RequirementDtoRequest> list4 = new ArrayList<>();
        list1.add(requirement1);
        list1.add(requirement2);
        list2.add(requirement3);
        list2.add(requirement4);
        list3.add(requirement5);
        list3.add(requirement6);
        list4.add(requirement7);
        list4.add(requirement8);
        VacancyDtoRequest vacancy1 = new VacancyDtoRequest("Стажер", 16000, list1, token1.getToken());
        VacancyDtoRequest vacancy2 = new VacancyDtoRequest("Стажер", 10000, list2, token2.getToken());
        VacancyDtoRequest vacancy3 = new VacancyDtoRequest("Стажер", 1000, list3, token3.getToken());
        VacancyDtoRequest vacancy4 = new VacancyDtoRequest("Стажер", 1500, list4, token4.getToken());
        List<Vacancy> vacancyList = new ArrayList<>();


        Requirement requirement11 = new Requirement("Java", 3, Require.YES);
        Requirement requirement22 = new Requirement("Pascal", 2, Require.NO);
        List<Requirement> list11 = new ArrayList<>();
        list11.add(requirement11);
        list11.add(requirement22);

        Requirement requirement55 = new Requirement("Java", 2, Require.YES);
        Requirement requirement66 = new Requirement("Pascal", 1, Require.NO);
        List<Requirement> list33 = new ArrayList<>();
        list33.add(requirement55);
        list33.add(requirement66);

        Vacancy vacancy7 = new Vacancy("Стажер", 16000, list11);
        Vacancy vacancy8 = new Vacancy("Стажер", 1000, list33);


        vacancyList.add(vacancy7);
        vacancyList.add(vacancy8);
        server.addVacancy(gson.toJson(vacancy1));
        server.addVacancy(gson.toJson(vacancy2));
        server.addVacancy(gson.toJson(vacancy3));
        server.addVacancy(gson.toJson(vacancy4));
        RegisterEmployeeDtoRequest registerEmployeeDtoRequest1 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        String jsonEmployer = gson.toJson(registerEmployeeDtoRequest1);
        TokenDtoResponse token11 = gson.fromJson(server.registerEmployee(jsonEmployer), TokenDtoResponse.class);
        SkillDtoRequest skillDtoRequest1 = new SkillDtoRequest("Java", 3, token11.getToken());
        SkillDtoRequest skillDtoRequest2 = new SkillDtoRequest("Pascal", 3, token11.getToken());
        server.addSummary(gson.toJson(skillDtoRequest1));
        server.addSummary(gson.toJson(skillDtoRequest2));

        Collection<Vacancy> vacancies = new ArrayList<>();
        vacancies.add(vacancy8);
        vacancies.add(vacancy7);
        assertEquals(server.getVacancy(gson.toJson(token11)), gson.toJson(vacancies));

    }

    @Test
    public void testGetVacancyWithRequire() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "Jek1", "1qaz2wsx&3edc");
        RegisterEmployerDtoRequest employer2 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Артем", "Игоревич", "Безухов", "jack", "1qaz2wsx&3edc");
        RegisterEmployerDtoRequest employer3 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Антон", "Александрович", "Крутов", "Horhe", "1qaz2wsx&3edc");
        RegisterEmployerDtoRequest employer4 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Семен", "Васильевич", "Болотов", "molod", "1qaz2wsx&3edc");
        String jsonEmployer1 = gson.toJson(employer1);
        String jsonEmployer2 = gson.toJson(employer2);
        String jsonEmployer3 = gson.toJson(employer3);
        String jsonEmployer4 = gson.toJson(employer4);
        TokenDtoResponse token1 = gson.fromJson(server.registerEmployer(jsonEmployer1), TokenDtoResponse.class);
        TokenDtoResponse token2 = gson.fromJson(server.registerEmployer(jsonEmployer2), TokenDtoResponse.class);
        TokenDtoResponse token3 = gson.fromJson(server.registerEmployer(jsonEmployer3), TokenDtoResponse.class);
        TokenDtoResponse token4 = gson.fromJson(server.registerEmployer(jsonEmployer4), TokenDtoResponse.class);
        RequirementDtoRequest requirement1 = new RequirementDtoRequest("Java", 3, Require.YES);
        RequirementDtoRequest requirement2 = new RequirementDtoRequest("Pascal", 4, Require.NO);
        RequirementDtoRequest requirement3 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement4 = new RequirementDtoRequest("JavaScript", 3, Require.NO);
        RequirementDtoRequest requirement5 = new RequirementDtoRequest("Java", 2, Require.YES);
        RequirementDtoRequest requirement6 = new RequirementDtoRequest("Pascal", 3, Require.YES);
        RequirementDtoRequest requirement7 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement8 = new RequirementDtoRequest("JavaScript", 3, Require.YES);
        List<RequirementDtoRequest> list1 = new ArrayList<>();
        List<RequirementDtoRequest> list2 = new ArrayList<>();
        List<RequirementDtoRequest> list3 = new ArrayList<>();
        List<RequirementDtoRequest> list4 = new ArrayList<>();
        list1.add(requirement1);
        list1.add(requirement2);
        list2.add(requirement3);
        list2.add(requirement4);
        list3.add(requirement5);
        list3.add(requirement6);
        list4.add(requirement7);
        list4.add(requirement8);
        VacancyDtoRequest vacancy1 = new VacancyDtoRequest("Стажер", 16000, list1, token1.getToken());
        VacancyDtoRequest vacancy2 = new VacancyDtoRequest("Стажер", 10000, list2, token2.getToken());
        VacancyDtoRequest vacancy3 = new VacancyDtoRequest("Стажер", 1000, list3, token3.getToken());
        VacancyDtoRequest vacancy4 = new VacancyDtoRequest("Стажер", 1500, list4, token4.getToken());
        List<Vacancy> vacancyList = new ArrayList<>();

        Requirement requirement55 = new Requirement("Java", 2, Require.YES);
        Requirement requirement66 = new Requirement("Pascal", 3, Require.YES);
        List<Requirement> list33 = new ArrayList<>();
        list33.add(requirement55);
        list33.add(requirement66);

        Vacancy vacancy8 = new Vacancy("Стажер", 1000, list33);
        vacancyList.add(vacancy8);
        server.addVacancy(gson.toJson(vacancy1));
        server.addVacancy(gson.toJson(vacancy2));
        server.addVacancy(gson.toJson(vacancy3));
        server.addVacancy(gson.toJson(vacancy4));
        RegisterEmployeeDtoRequest registerEmployeeDtoRequest1 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        String jsonEmployer = gson.toJson(registerEmployeeDtoRequest1);
        TokenDtoResponse token11 = gson.fromJson(server.registerEmployee(jsonEmployer), TokenDtoResponse.class);
        SkillDtoRequest skillDtoRequest1 = new SkillDtoRequest("Java", 3, token11.getToken());
        SkillDtoRequest skillDtoRequest2 = new SkillDtoRequest("Pascal", 3, token11.getToken());
        server.addSummary(gson.toJson(skillDtoRequest1));
        server.addSummary(gson.toJson(skillDtoRequest2));

        assertEquals(server.getVacancyWithRequire(gson.toJson(token11)), gson.toJson(vacancyList));
    }

    @Test
    public void testGetVacancyAllLevel() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "Jek1", "1qaz2wsx&3edc");
        RegisterEmployerDtoRequest employer2 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Артем", "Игоревич", "Безухов", "jack", "1qaz2wsx&3edc");
        RegisterEmployerDtoRequest employer3 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Антон", "Александрович", "Крутов", "Horhe", "1qaz2wsx&3edc");
        RegisterEmployerDtoRequest employer4 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Семен", "Васильевич", "Болотов", "molod", "1qaz2wsx&3edc");
        String jsonEmployer1 = gson.toJson(employer1);
        String jsonEmployer2 = gson.toJson(employer2);
        String jsonEmployer3 = gson.toJson(employer3);
        String jsonEmployer4 = gson.toJson(employer4);
        TokenDtoResponse token1 = gson.fromJson(server.registerEmployer(jsonEmployer1), TokenDtoResponse.class);
        TokenDtoResponse token2 = gson.fromJson(server.registerEmployer(jsonEmployer2), TokenDtoResponse.class);
        TokenDtoResponse token3 = gson.fromJson(server.registerEmployer(jsonEmployer3), TokenDtoResponse.class);
        TokenDtoResponse token4 = gson.fromJson(server.registerEmployer(jsonEmployer4), TokenDtoResponse.class);
        RequirementDtoRequest requirement1 = new RequirementDtoRequest("Java", 3, Require.YES);
        RequirementDtoRequest requirement2 = new RequirementDtoRequest("Pascal", 2, Require.NO);
        RequirementDtoRequest requirement3 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement4 = new RequirementDtoRequest("JavaScript", 3, Require.NO);
        RequirementDtoRequest requirement5 = new RequirementDtoRequest("Java", 2, Require.YES);
        RequirementDtoRequest requirement6 = new RequirementDtoRequest("Pascal", 1, Require.NO);
        RequirementDtoRequest requirement7 = new RequirementDtoRequest("C++", 5, Require.YES);
        RequirementDtoRequest requirement8 = new RequirementDtoRequest("JavaScript", 2, Require.YES);
        List<RequirementDtoRequest> list1 = new ArrayList<>();
        List<RequirementDtoRequest> list2 = new ArrayList<>();
        List<RequirementDtoRequest> list3 = new ArrayList<>();
        List<RequirementDtoRequest> list4 = new ArrayList<>();
        list1.add(requirement1);
        list1.add(requirement2);
        list2.add(requirement3);
        list2.add(requirement4);
        list3.add(requirement5);
        list3.add(requirement6);
        list4.add(requirement7);
        list4.add(requirement8);
        VacancyDtoRequest vacancy1 = new VacancyDtoRequest("Стажер", 16000, list1, token1.getToken());
        VacancyDtoRequest vacancy2 = new VacancyDtoRequest("Стажер", 10000, list2, token2.getToken());
        VacancyDtoRequest vacancy3 = new VacancyDtoRequest("Стажер", 1000, list3, token3.getToken());
        VacancyDtoRequest vacancy4 = new VacancyDtoRequest("Стажер", 1500, list4, token4.getToken());
        List<Vacancy> vacancyList = new ArrayList<>();

        Requirement requirement33 = new Requirement("C++", 4, Require.YES);
        Requirement requirement44 = new Requirement("JavaScript", 3, Require.NO);
        Requirement requirement77 = new Requirement("C++", 5, Require.YES);
        Requirement requirement88 = new Requirement("JavaScript", 2, Require.YES);
        List<Requirement> list22 = new ArrayList<>();
        List<Requirement> list44 = new ArrayList<>();
        list22.add(requirement33);
        list22.add(requirement44);
        list44.add(requirement77);
        list44.add(requirement88);



        Vacancy vacancy7 = new Vacancy("Стажер", 10000, list22);
        Vacancy vacancy8 = new Vacancy("Стажер", 1500, list44);
        vacancyList.add(vacancy8);
        vacancyList.add(vacancy7);
        server.addVacancy(gson.toJson(vacancy1));
        server.addVacancy(gson.toJson(vacancy2));
        server.addVacancy(gson.toJson(vacancy3));
        server.addVacancy(gson.toJson(vacancy4));
        RegisterEmployeeDtoRequest registerEmployeeDtoRequest1 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        String jsonEmployer = gson.toJson(registerEmployeeDtoRequest1);
        TokenDtoResponse token11 = gson.fromJson(server.registerEmployee(jsonEmployer), TokenDtoResponse.class);
        SkillDtoRequest skillDtoRequest1 = new SkillDtoRequest("C++", 1, token11.getToken());
        SkillDtoRequest skillDtoRequest2 = new SkillDtoRequest("JavaScript", 1, token11.getToken());
        server.addSummary(gson.toJson(skillDtoRequest1));
        server.addSummary(gson.toJson(skillDtoRequest2));
        List<Vacancy> vacancies = new ArrayList<>();
        vacancies.add(vacancy7);
        vacancies.add(vacancy8);
        assertEquals(server.getVacancyAllLevel(gson.toJson(token11)), gson.toJson(vacancies));
    }

    @Test
    public void testGetVacancyOnlyOneRequirement() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "Jek1", "1qaz2wsx&3edc");
        RegisterEmployerDtoRequest employer2 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Артем", "Игоревич", "Безухов", "jack", "1qaz2wsx&3edc");
        RegisterEmployerDtoRequest employer3 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Антон", "Александрович", "Крутов", "Horhe", "1qaz2wsx&3edc");
        RegisterEmployerDtoRequest employer4 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Семен", "Васильевич", "Болотов", "molod", "1qaz2wsx&3edc");
        String jsonEmployer1 = gson.toJson(employer1);
        String jsonEmployer2 = gson.toJson(employer2);
        String jsonEmployer3 = gson.toJson(employer3);
        String jsonEmployer4 = gson.toJson(employer4);
        TokenDtoResponse token1 = gson.fromJson(server.registerEmployer(jsonEmployer1), TokenDtoResponse.class);
        TokenDtoResponse token2 = gson.fromJson(server.registerEmployer(jsonEmployer2), TokenDtoResponse.class);
        TokenDtoResponse token3 = gson.fromJson(server.registerEmployer(jsonEmployer3), TokenDtoResponse.class);
        TokenDtoResponse token4 = gson.fromJson(server.registerEmployer(jsonEmployer4), TokenDtoResponse.class);
        RequirementDtoRequest requirement1 = new RequirementDtoRequest("Java", 3, Require.YES);
        RequirementDtoRequest requirement2 = new RequirementDtoRequest("Pascal", 2, Require.NO);
        RequirementDtoRequest requirement3 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement4 = new RequirementDtoRequest("JavaScript", 3, Require.NO);
        RequirementDtoRequest requirement5 = new RequirementDtoRequest("Java", 2, Require.YES);
        RequirementDtoRequest requirement6 = new RequirementDtoRequest("C#", 1, Require.NO);
        RequirementDtoRequest requirement7 = new RequirementDtoRequest("C++", 3, Require.YES);
        RequirementDtoRequest requirement8 = new RequirementDtoRequest("Pascal", 3, Require.YES);
        List<RequirementDtoRequest> list1 = new ArrayList<>();
        List<RequirementDtoRequest> list2 = new ArrayList<>();
        List<RequirementDtoRequest> list3 = new ArrayList<>();
        List<RequirementDtoRequest> list4 = new ArrayList<>();
        list1.add(requirement2);
        list1.add(requirement1);
        list2.add(requirement4);
        list2.add(requirement3);
        list3.add(requirement6);
        list3.add(requirement5);
        list4.add(requirement7);
        list4.add(requirement8);
        VacancyDtoRequest vacancy1 = new VacancyDtoRequest("Стажер", 16000, list1, token1.getToken());
        VacancyDtoRequest vacancy2 = new VacancyDtoRequest("Стажер", 10000, list2, token2.getToken());
        VacancyDtoRequest vacancy3 = new VacancyDtoRequest("Стажер", 1000, list3, token3.getToken());
        VacancyDtoRequest vacancy4 = new VacancyDtoRequest("Стажер", 1500, list4, token4.getToken());
        List<Vacancy> vacancyList = new ArrayList<>();

        Requirement requirement11 = new Requirement("Java", 3, Require.YES);
        Requirement requirement22 = new Requirement("Pascal", 2, Require.NO);
        Requirement requirement33 = new Requirement("C++", 4, Require.YES);
        Requirement requirement44 = new Requirement("JavaScript", 3, Require.NO);
        Requirement requirement55 = new Requirement("Java", 2, Require.YES);
        Requirement requirement66 = new Requirement("C#", 1, Require.NO);
        Requirement requirement77 = new Requirement("C++", 3, Require.YES);
        Requirement requirement88 = new Requirement("Pascal", 3, Require.YES);
        List<Requirement> list11 = new ArrayList<>();
        List<Requirement> list22 = new ArrayList<>();
        List<Requirement> list33 = new ArrayList<>();
        List<Requirement> list44 = new ArrayList<>();
        list11.add(requirement22);
        list11.add(requirement11);
        list22.add(requirement44);
        list22.add(requirement33);
        list33.add(requirement66);
        list33.add(requirement55);
        list44.add(requirement77);
        list44.add(requirement88);

        Vacancy vacancy7 = new Vacancy("Стажер", 16000, list11);
        Vacancy vacancy8 = new Vacancy("Стажер", 1500, list44);
        Vacancy vacancy9 = new Vacancy("Стажер", 10000, list22);
        Vacancy vacancy10 = new Vacancy("Стажер", 1000, list33);
        vacancyList.add(vacancy7);
        vacancyList.add(vacancy8);
        vacancyList.add(vacancy9);
        vacancyList.add(vacancy10);
        ImmutableSet<Vacancy> vacancyList1 = ImmutableSet.copyOf(vacancyList);

        server.addVacancy(gson.toJson(vacancy1));
        server.addVacancy(gson.toJson(vacancy2));
        server.addVacancy(gson.toJson(vacancy3));
        server.addVacancy(gson.toJson(vacancy4));
        RegisterEmployeeDtoRequest registerEmployeeDtoRequest1 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Круглов", "jek1@mail.ru", "kruglov", "199&8567");
        String jsonEmployer = gson.toJson(registerEmployeeDtoRequest1);
        TokenDtoResponse token11 = gson.fromJson(server.registerEmployee(jsonEmployer), TokenDtoResponse.class);
        SkillDtoRequest skillDtoRequest4 = new SkillDtoRequest("JavaScript", 5, token11.getToken());
        SkillDtoRequest skillDtoRequest2 = new SkillDtoRequest("Pascal", 5, token11.getToken());
        SkillDtoRequest skillDtoRequest3 = new SkillDtoRequest("Java", 5, token11.getToken());
        SkillDtoRequest skillDtoRequest1 = new SkillDtoRequest("C++", 5, token11.getToken());
        server.addSummary(gson.toJson(skillDtoRequest2));
        server.addSummary(gson.toJson(skillDtoRequest4));
        server.addSummary(gson.toJson(skillDtoRequest3));
        server.addSummary(gson.toJson(skillDtoRequest1));
        String x = server.getVacancyOnlyOneRequirement(gson.toJson(token11));
        VacanciesListResponse vacancies = gson.fromJson(x, VacanciesListResponse.class);
        assertEquals(vacancies.getVacancies().length, 4);
    }

    @Test
    public void testGetAllVacancy() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "Jek1", "1qaz2wsx&3edc");
        String jsonEmployer1 = gson.toJson(employer1);
        String responseRegister = server.registerEmployer(jsonEmployer1);
        TokenDtoRequest tokenDtoRequest1 = gson.fromJson(responseRegister, TokenDtoRequest.class);
        RequirementDtoRequest requirement1 = new RequirementDtoRequest("Java", 3, Require.YES);
        RequirementDtoRequest requirement2 = new RequirementDtoRequest("Pascal", 2, Require.NO);
        RequirementDtoRequest requirement3 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement4 = new RequirementDtoRequest("JavaScript", 3, Require.NO);
        RequirementDtoRequest requirement5 = new RequirementDtoRequest("Java", 2, Require.YES);
        RequirementDtoRequest requirement6 = new RequirementDtoRequest("C#", 1, Require.NO);
        RequirementDtoRequest requirement7 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement8 = new RequirementDtoRequest("Pascal", 3, Require.YES);
        List<RequirementDtoRequest> list1 = new ArrayList<>();
        List<RequirementDtoRequest> list2 = new ArrayList<>();
        List<RequirementDtoRequest> list3 = new ArrayList<>();
        List<RequirementDtoRequest> list4 = new ArrayList<>();
        list1.add(requirement1);
        list1.add(requirement2);
        list2.add(requirement3);
        list2.add(requirement4);
        list3.add(requirement5);
        list3.add(requirement6);
        list4.add(requirement7);
        list4.add(requirement8);
        VacancyDtoRequest vacancy1 = new VacancyDtoRequest("Стажер", 16000, list1, tokenDtoRequest1.getToken());
        VacancyDtoRequest vacancy2 = new VacancyDtoRequest("Директор", 10000, list2, tokenDtoRequest1.getToken());
        VacancyDtoRequest vacancy3 = new VacancyDtoRequest("Менеджер", 1000, list3, tokenDtoRequest1.getToken());
        VacancyDtoRequest vacancy4 = new VacancyDtoRequest("Разработчик", 1500, list4, tokenDtoRequest1.getToken());
        EditVacancyStatus editVacancyStatus = new EditVacancyStatus("Разработчик", tokenDtoRequest1.getToken());
        List<Vacancy> vacancyList = new ArrayList<>();
        server.addVacancy(gson.toJson(vacancy1));
        server.addVacancy(gson.toJson(vacancy2));
        server.addVacancy(gson.toJson(vacancy3));
        server.addVacancy(gson.toJson(vacancy4));
        VacancyListDtoRequest vacancyListDtoRequest = new VacancyListDtoRequest(vacancyList);
        server.makeVacancyInactive(gson.toJson(editVacancyStatus));
        Employer employer2 = (Employer) server.getDataBase().getPersonByToken(tokenDtoRequest1.getToken());
        Vacancy vacancy7 = employer2.getVacancyByNamePosition(vacancy1.getNamePosition());
        Vacancy vacancy8 = employer2.getVacancyByNamePosition(vacancy2.getNamePosition());
        Vacancy vacancy9 = employer2.getVacancyByNamePosition(vacancy3.getNamePosition());
        Vacancy vacancy10 = employer2.getVacancyByNamePosition(vacancy4.getNamePosition());
        vacancyList.add(vacancy7);
        vacancyList.add(vacancy8);
        vacancyList.add(vacancy9);
        vacancyList.add(vacancy10);
        assertEquals(server.getAllVacancy(gson.toJson(tokenDtoRequest1)), gson.toJson(vacancyListDtoRequest));
    }

    @Test
    public void testGetVacancyOnlyActive() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "Jek1", "1qaz2wsx&3edc");
        String jsonEmployer1 = gson.toJson(employer1);
        TokenDtoRequest tokenDtoRequest1 = gson.fromJson(server.registerEmployer(jsonEmployer1), TokenDtoRequest.class);
        RequirementDtoRequest requirement1 = new RequirementDtoRequest("Java", 3, Require.YES);
        RequirementDtoRequest requirement2 = new RequirementDtoRequest("Pascal", 2, Require.NO);
        RequirementDtoRequest requirement3 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement4 = new RequirementDtoRequest("JavaScript", 3, Require.NO);
        RequirementDtoRequest requirement5 = new RequirementDtoRequest("Java", 2, Require.YES);
        RequirementDtoRequest requirement6 = new RequirementDtoRequest("C#", 1, Require.NO);
        RequirementDtoRequest requirement7 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement8 = new RequirementDtoRequest("Pascal", 3, Require.YES);
        List<RequirementDtoRequest> list1 = new ArrayList<>();
        List<RequirementDtoRequest> list2 = new ArrayList<>();
        List<RequirementDtoRequest> list3 = new ArrayList<>();
        List<RequirementDtoRequest> list4 = new ArrayList<>();
        list1.add(requirement1);
        list1.add(requirement2);
        list2.add(requirement3);
        list2.add(requirement4);
        list3.add(requirement5);
        list3.add(requirement6);
        list4.add(requirement7);
        list4.add(requirement8);
        VacancyDtoRequest vacancy1 = new VacancyDtoRequest("Стажер", 16000, list1, tokenDtoRequest1.getToken());
        VacancyDtoRequest vacancy2 = new VacancyDtoRequest("Директор", 10000, list2, tokenDtoRequest1.getToken());
        VacancyDtoRequest vacancy3 = new VacancyDtoRequest("Менеджер", 1000, list3, tokenDtoRequest1.getToken());
        VacancyDtoRequest vacancy4 = new VacancyDtoRequest("Разработчик", 1500, list4, tokenDtoRequest1.getToken());
        EditVacancyStatus editVacancyStatus = new EditVacancyStatus(vacancy4.getNamePosition(), tokenDtoRequest1.getToken());
        List<Vacancy> vacancyList = new ArrayList<>();

        Requirement requirement11 = new Requirement("Java", 3, Require.YES);
        Requirement requirement22 = new Requirement("Pascal", 2, Require.NO);
        Requirement requirement33 = new Requirement("C++", 4, Require.YES);
        Requirement requirement44 = new Requirement("JavaScript", 3, Require.NO);
        Requirement requirement55 = new Requirement("Java", 2, Require.YES);
        Requirement requirement66 = new Requirement("C#", 1, Require.NO);
        List<Requirement> list11 = new ArrayList<>();
        List<Requirement> list22 = new ArrayList<>();
        List<Requirement> list33 = new ArrayList<>();
        list11.add(requirement11);
        list11.add(requirement22);
        list22.add(requirement33);
        list22.add(requirement44);
        list33.add(requirement55);
        list33.add(requirement66);


        Vacancy vacancy7 = new Vacancy("Стажер", 16000, list11);
        Vacancy vacancy8 = new Vacancy("Директор", 10000, list22);
        Vacancy vacancy9 = new Vacancy("Менеджер", 1000, list33);
        vacancyList.add(vacancy7);
        vacancyList.add(vacancy8);
        vacancyList.add(vacancy9);
        server.addVacancy(gson.toJson(vacancy1));
        server.addVacancy(gson.toJson(vacancy2));
        server.addVacancy(gson.toJson(vacancy3));
        server.addVacancy(gson.toJson(vacancy4));
        server.makeVacancyInactive(gson.toJson(editVacancyStatus));
        VacancyListDtoRequest vacancyListDtoRequest = new VacancyListDtoRequest(vacancyList);

        assertEquals(server.getVacancyOnlyActive(gson.toJson(tokenDtoRequest1)), gson.toJson(vacancyListDtoRequest));


    }

    @Test
    public void testGetVacancyOnlyInactive() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "Jek1", "1qaz2wsx&3edc");
        String jsonEmployer1 = gson.toJson(employer1);
        TokenDtoRequest tokenDtoRequest1 = gson.fromJson(server.registerEmployer(jsonEmployer1), TokenDtoRequest.class);
        RequirementDtoRequest requirement1 = new RequirementDtoRequest("Java", 3, Require.YES);
        RequirementDtoRequest requirement2 = new RequirementDtoRequest("Pascal", 2, Require.NO);
        RequirementDtoRequest requirement3 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement4 = new RequirementDtoRequest("JavaScript", 3, Require.NO);
        RequirementDtoRequest requirement5 = new RequirementDtoRequest("Java", 2, Require.YES);
        RequirementDtoRequest requirement6 = new RequirementDtoRequest("C#", 1, Require.NO);
        RequirementDtoRequest requirement7 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement8 = new RequirementDtoRequest("Pascal", 3, Require.YES);
        List<RequirementDtoRequest> list1 = new ArrayList<>();
        List<RequirementDtoRequest> list2 = new ArrayList<>();
        List<RequirementDtoRequest> list3 = new ArrayList<>();
        List<RequirementDtoRequest> list4 = new ArrayList<>();
        list1.add(requirement1);
        list1.add(requirement2);
        list2.add(requirement3);
        list2.add(requirement4);
        list3.add(requirement5);
        list3.add(requirement6);
        list4.add(requirement7);
        list4.add(requirement8);
        VacancyDtoRequest vacancy1 = new VacancyDtoRequest("Стажер", 16000, list1, tokenDtoRequest1.getToken());
        VacancyDtoRequest vacancy2 = new VacancyDtoRequest("Директор", 10000, list2, tokenDtoRequest1.getToken());
        VacancyDtoRequest vacancy3 = new VacancyDtoRequest("Менеджер", 1000, list3, tokenDtoRequest1.getToken());
        VacancyDtoRequest vacancy4 = new VacancyDtoRequest("Разработчик", 1500, list4, tokenDtoRequest1.getToken());
        EditVacancyStatus editVacancyStatus = new EditVacancyStatus(vacancy4.getNamePosition(), tokenDtoRequest1.getToken());
        List<Vacancy> vacancyList = new ArrayList<>();
        Employer employer = (Employer) server.getDataBase().getPersonByToken(tokenDtoRequest1.getToken());
        server.addVacancy(gson.toJson(vacancy1));
        server.addVacancy(gson.toJson(vacancy2));
        server.addVacancy(gson.toJson(vacancy3));
        server.addVacancy(gson.toJson(vacancy4));
        Vacancy vacancy = employer.getVacancyByNamePosition(vacancy4.getNamePosition());
        vacancyList.add(vacancy);
        server.makeVacancyInactive(gson.toJson(editVacancyStatus));
        VacancyListDtoRequest vacancyListDtoRequest = new VacancyListDtoRequest(vacancyList);

        assertEquals(server.getVacancyOnlyInactive(gson.toJson(tokenDtoRequest1)), gson.toJson(vacancyListDtoRequest));

    }

    @Test
    public void testRemoveVacancy() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("test", "test16", "jek1@mail.ru", "Стас", "Алексеевич", "Круглов", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employer1)), TokenDtoResponse.class);
        Employer employer = (Employer) server.getDataBase().getPersonByToken(token.getToken());
        RequirementDtoRequest requirement1 = new RequirementDtoRequest("Java", 2, Require.YES);
        RequirementDtoRequest requirement2 = new RequirementDtoRequest("Pascal", 1, Require.NO);
        RequirementDtoRequest requirement3 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement4 = new RequirementDtoRequest("JavaScript", 3, Require.YES);
        List<RequirementDtoRequest> list1 = new ArrayList<>();
        List<RequirementDtoRequest> list2 = new ArrayList<>();
        list1.add(requirement1);
        list1.add(requirement2);
        list2.add(requirement3);
        list2.add(requirement4);
        VacancyDtoRequest vacancy1 = new VacancyDtoRequest("Разработчик", 16000, list1, token.getToken());
        VacancyDtoRequest vacancy2 = new VacancyDtoRequest("Стажер", 10000, list2, token.getToken());
        server.addVacancy(gson.toJson(vacancy1));
        server.addVacancy(gson.toJson(vacancy2));
        Assertions.assertEquals(employer.getVacancies().size(), 2);
        Vacancy vacancy = employer.getVacancyByNamePosition("Разработчик");
        employer.removeVacancy(vacancy);
        Assertions.assertEquals(employer.getVacancies().size(), 1);

    }

    @Test
    public void testSetVacancies() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("test", "test16", "jek1@mail.ru", "Стас", "Алексеевич", "Круглов", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employer1)), TokenDtoResponse.class);
        Employer employer = (Employer) server.getDataBase().getPersonByToken(token.getToken());
        RequirementDtoRequest requirement1 = new RequirementDtoRequest("Java", 2, Require.YES);
        RequirementDtoRequest requirement2 = new RequirementDtoRequest("Pascal", 1, Require.NO);
        RequirementDtoRequest requirement3 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement4 = new RequirementDtoRequest("JavaScript", 3, Require.YES);
        List<RequirementDtoRequest> list1 = new ArrayList<>();
        List<RequirementDtoRequest> list2 = new ArrayList<>();
        list1.add(requirement1);
        list1.add(requirement2);
        list2.add(requirement3);
        list2.add(requirement4);
        VacancyDtoRequest vacancy1 = new VacancyDtoRequest("Разработчик", 16000, list1, token.getToken());
        VacancyDtoRequest vacancy2 = new VacancyDtoRequest("Стажер", 10000, list2, token.getToken());
        server.addVacancy(gson.toJson(vacancy1));
        server.addVacancy(gson.toJson(vacancy2));

        List<Requirement> baseList = new ArrayList<>();

        Vacancy vacancy3 = new Vacancy("Стажер", 16000, baseList);
        Vacancy vacancy4 = new Vacancy("Помощник разработчика", 10000, baseList);
        List<Vacancy> vacancies = new ArrayList<>();
        vacancies.add(vacancy3);
        vacancies.add(vacancy4);

        Assertions.assertEquals(employer.getVacancies().get(0).getNamePosition(), "Разработчик");
        Assertions.assertEquals(employer.getVacancies().get(1).getNamePosition(), "Стажер");
        employer.setVacancies(vacancies);
        Assertions.assertEquals(employer.getVacancies().get(0).getNamePosition(), "Стажер");
        Assertions.assertEquals(employer.getVacancies().get(1).getNamePosition(), "Помощник разработчика");

    }

    @Test
    public void testGetAddressCompany() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("test", "test16", "Стас" , "Алексеевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employer1)), TokenDtoResponse.class);
        Employer employer = (Employer) server.getDataBase().getPersonByToken(token.getToken());
        Assertions.assertEquals(employer.getMiddleName(), "Алексеевич");
        Assertions.assertEquals(employer.getLastName(), "Круглов");
        Assertions.assertEquals(employer.getEmail(), "jek1@mail.ru");

    }

    @Test
    public void testEditRequirements() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("test", "test16", "jek1@mail.ru", "Стас", "Алексеевич", "Круглов", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employer1)), TokenDtoResponse.class);
        Employer employer = (Employer) server.getDataBase().getPersonByToken(token.getToken());
        RequirementDtoRequest requirement1 = new RequirementDtoRequest("Java", 2, Require.YES);
        RequirementDtoRequest requirement2 = new RequirementDtoRequest("Pascal", 1, Require.NO);
        RequirementDtoRequest requirement3 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement4 = new RequirementDtoRequest("JavaScript", 3, Require.YES);
        List<RequirementDtoRequest> list1 = new ArrayList<>();
        List<RequirementDtoRequest> list2 = new ArrayList<>();
        list1.add(requirement1);
        list1.add(requirement2);
        list2.add(requirement3);
        list2.add(requirement4);
        VacancyDtoRequest vacancy1 = new VacancyDtoRequest("Разработчик", 16000, list1, token.getToken());
        VacancyDtoRequest vacancy2 = new VacancyDtoRequest("Стажер", 10000, list2, token.getToken());
        server.addVacancy(gson.toJson(vacancy1));
        server.addVacancy(gson.toJson(vacancy2));
        Assertions.assertEquals(employer.getVacancies().get(0).getRequirements().get(0).getDescriptionSkill(), "Java");
        employer.getVacancies().get(0).getRequirements().get(0).editRequirement("Python", 5, Require.YES);
        Assertions.assertEquals(employer.getVacancies().get(0).getRequirements().get(0).getDescriptionSkill(), "Python");
    }

    @Test
    public void testEditVacancy1() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("test", "test16", "jek1@mail.ru", "Стас", "Алексеевич", "Круглов", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employer1)), TokenDtoResponse.class);
        Employer employer = (Employer) server.getDataBase().getPersonByToken(token.getToken());
        RequirementDtoRequest requirement1 = new RequirementDtoRequest("Java", 2, Require.YES);
        RequirementDtoRequest requirement2 = new RequirementDtoRequest("Pascal", 1, Require.NO);
        RequirementDtoRequest requirement3 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement4 = new RequirementDtoRequest("JavaScript", 3, Require.YES);
        List<RequirementDtoRequest> list1 = new ArrayList<>();
        List<RequirementDtoRequest> list2 = new ArrayList<>();
        list1.add(requirement1);
        list1.add(requirement2);
        list2.add(requirement3);
        list2.add(requirement4);
        VacancyDtoRequest vacancy1 = new VacancyDtoRequest("Разработчик", 16000, list1, token.getToken());
        VacancyDtoRequest vacancy2 = new VacancyDtoRequest("Стажер", 10000, list2, token.getToken());
        server.addVacancy(gson.toJson(vacancy1));
        server.addVacancy(gson.toJson(vacancy2));
        Assertions.assertEquals(employer.getVacancies().get(0).getNamePosition(), "Разработчик");
        employer.getVacancies().get(0).setNamePosition("Директор");
        Assertions.assertEquals(employer.getVacancies().get(0).getNamePosition(), "Директор");
        Assertions.assertEquals(employer.getVacancies().get(0).getSalary(), 16000);
        employer.getVacancies().get(0).editVacancy("Дизайнер", 7000);
        Assertions.assertEquals(employer.getVacancies().get(0).getSalary(), 7000);
    }

    @Test
    public void testStatus() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("test", "test16", "jek1@mail.ru", "Стас", "Алексеевич", "Круглов", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employer1)), TokenDtoResponse.class);
        Employer employer = (Employer) server.getDataBase().getPersonByToken(token.getToken());
        RequirementDtoRequest requirement1 = new RequirementDtoRequest("Java", 2, Require.YES);
        RequirementDtoRequest requirement2 = new RequirementDtoRequest("Pascal", 1, Require.NO);
        RequirementDtoRequest requirement3 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement4 = new RequirementDtoRequest("JavaScript", 3, Require.YES);
        List<RequirementDtoRequest> list1 = new ArrayList<>();
        List<RequirementDtoRequest> list2 = new ArrayList<>();
        list1.add(requirement1);
        list1.add(requirement2);
        list2.add(requirement3);
        list2.add(requirement4);
        VacancyDtoRequest vacancy1 = new VacancyDtoRequest("Разработчик", 16000, list1, token.getToken());
        VacancyDtoRequest vacancy2 = new VacancyDtoRequest("Стажер", 10000, list2, token.getToken());
        server.addVacancy(gson.toJson(vacancy1));
        server.addVacancy(gson.toJson(vacancy2));
        Assertions.assertTrue(employer.getVacancies().get(0).isStatus());
        employer.getVacancies().get(0).setStatus(false);
        Assertions.assertFalse(employer.getVacancies().get(0).isStatus());

    }
}