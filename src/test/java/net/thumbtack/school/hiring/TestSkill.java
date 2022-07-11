package net.thumbtack.school.hiring;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.dto.*;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.users.Require;
import net.thumbtack.school.hiring.users.Employee;
import net.thumbtack.school.hiring.users.Requirement;
import net.thumbtack.school.hiring.users.Skill;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSkill {
    Server server = new Server();
    Gson gson = new Gson();

    @Test
    public void testGetSummary() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@ma2il.ru", "kruglov", "1998&567");
        RegisterEmployeeDtoRequest employee2 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Физицкий", "jek1@m3ail.ru", "fixkiy", "199&8567");
        RegisterEmployeeDtoRequest employee3 = new RegisterEmployeeDtoRequest("Константин", "Александрович", "Серов", "jek41@mail.ru", "serov", "199&8567");
        RegisterEmployeeDtoRequest employee4 = new RegisterEmployeeDtoRequest("Алексей", "Игоревич", "Морозов", "jek1@m6ail.ru", "morozov", "1998&567");
        String jsonEmployee1 = gson.toJson(employee1);
        String jsonEmployee2 = gson.toJson(employee2);
        String jsonEmployee3 = gson.toJson(employee3);
        String jsonEmployee4 = gson.toJson(employee4);

        TokenDtoResponse token1 = gson.fromJson(server.registerEmployee(jsonEmployee1), TokenDtoResponse.class);
        TokenDtoResponse token2 = gson.fromJson(server.registerEmployee(jsonEmployee2), TokenDtoResponse.class);
        TokenDtoResponse token3 = gson.fromJson(server.registerEmployee(jsonEmployee3), TokenDtoResponse.class);
        TokenDtoResponse token4 = gson.fromJson(server.registerEmployee(jsonEmployee4), TokenDtoResponse.class);
        SkillDtoRequest summary1 = new SkillDtoRequest("Java", 3, token1.getToken());
        SkillDtoRequest summary2 = new SkillDtoRequest("JavaScript", 3, token1.getToken());
        SkillDtoRequest summary3 = new SkillDtoRequest("C++", 4, token2.getToken());
        SkillDtoRequest summary4 = new SkillDtoRequest("JavaScript", 5, token2.getToken());
        SkillDtoRequest summary5 = new SkillDtoRequest("Java", 2, token3.getToken());
        SkillDtoRequest summary6 = new SkillDtoRequest("Pascal", 1, token3.getToken());
        SkillDtoRequest summary7 = new SkillDtoRequest("Python", 4, token4.getToken());
        SkillDtoRequest summary8 = new SkillDtoRequest("JavaScript", 5, token4.getToken());
        server.addSummary(gson.toJson(summary1));
        server.addSummary(gson.toJson(summary2));
        server.addSummary(gson.toJson(summary3));
        server.addSummary(gson.toJson(summary4));
        server.addSummary(gson.toJson(summary5));
        server.addSummary(gson.toJson(summary6));
        server.addSummary(gson.toJson(summary7));
        server.addSummary(gson.toJson(summary8));
        Requirement requirement1 = new Requirement("Java", 3, Require.YES);
        Requirement requirement2 = new Requirement("JavaScript", 3, Require.NO);
        Requirement requirement5 = new Requirement("Python", 3, Require.NO);
        Requirement requirement3 = new Requirement("C++", 4, Require.YES);
        Requirement requirement4 = new Requirement("JavaScript", 5, Require.YES);
        List<Requirement> requirementList1 = new ArrayList<>();
        List<Requirement> requirementList2 = new ArrayList<>();
        requirementList1.add(requirement1);
        requirementList1.add(requirement2);
        requirementList1.add(requirement5);
        requirementList2.add(requirement3);
        requirementList2.add(requirement4);
        RegisterEmployerDtoRequest registerEmployerDtoRequest1 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "Jek1", "1qaz2wsx&3edc");
        String jsonEmployer = gson.toJson(registerEmployerDtoRequest1);

        TokenDtoResponse token11 = gson.fromJson(server.registerEmployer(jsonEmployer), TokenDtoResponse.class);

        RequirementDtoRequest requirement11 = new RequirementDtoRequest("Java", 3, Require.YES);
        RequirementDtoRequest requirement22 = new RequirementDtoRequest("JavaScript", 3, Require.NO);
        RequirementDtoRequest requirement55 = new RequirementDtoRequest("Python", 3, Require.NO);
        RequirementDtoRequest requirement33 = new RequirementDtoRequest("C++", 4, Require.YES);
        RequirementDtoRequest requirement44 = new RequirementDtoRequest("JavaScript", 5, Require.YES);
        List<RequirementDtoRequest> requirementList11 = new ArrayList<>();
        List<RequirementDtoRequest> requirementList22 = new ArrayList<>();
        requirementList11.add(requirement11);
        requirementList11.add(requirement22);
        requirementList11.add(requirement55);
        requirementList22.add(requirement33);
        requirementList22.add(requirement44);

        VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest("Стажер", 16000, requirementList11, token11.getToken());
        VacancyDtoRequest vacancyDtoRequest1 = new VacancyDtoRequest("Стажер", 20000, requirementList22, token11.getToken());
        server.addVacancy(gson.toJson(vacancyDtoRequest));
        server.addVacancy(gson.toJson(vacancyDtoRequest1));
        List<Employee> employeeList = new ArrayList<>();
        List<Employee> employeeList1 = new ArrayList<>();
        assertEquals(server.getSkill(gson.toJson(vacancyDtoRequest)), gson.toJson(employeeList));

        employeeList1.add((Employee) server.getDataBase().getPersonByToken(token2.getToken()));
        assertEquals(server.getSkill(gson.toJson(vacancyDtoRequest1)), gson.toJson(employeeList1));


    }


    @Test
    public void testGetSummaryAllLevel() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@ma2il.ru", "kruglov", "1998&567");
        RegisterEmployeeDtoRequest employee2 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Физицкий", "jek1@m3ail.ru", "fixkiy", "199&8567");
        RegisterEmployeeDtoRequest employee3 = new RegisterEmployeeDtoRequest("Константин", "Александрович", "Серов", "jek41@mail.ru", "serov", "199&8567");
        RegisterEmployeeDtoRequest employee4 = new RegisterEmployeeDtoRequest("Алексей", "Игоревич", "Морозов", "jek1@m6ail.ru", "morozov", "1998&567");
        String jsonEmployee1 = gson.toJson(employee1);
        String jsonEmployee2 = gson.toJson(employee2);
        String jsonEmployee3 = gson.toJson(employee3);
        String jsonEmployee4 = gson.toJson(employee4);
        TokenDtoResponse token1 = gson.fromJson(server.registerEmployee(jsonEmployee1), TokenDtoResponse.class);
        TokenDtoResponse token2 = gson.fromJson(server.registerEmployee(jsonEmployee2), TokenDtoResponse.class);
        TokenDtoResponse token3 = gson.fromJson(server.registerEmployee(jsonEmployee3), TokenDtoResponse.class);
        TokenDtoResponse token4 = gson.fromJson(server.registerEmployee(jsonEmployee4), TokenDtoResponse.class);
        SkillDtoRequest summary1 = new SkillDtoRequest("Java", 1, token1.getToken());
        SkillDtoRequest summary2 = new SkillDtoRequest("Pascal", 2, token1.getToken());
        SkillDtoRequest summary3 = new SkillDtoRequest("C++", 1, token2.getToken());
        SkillDtoRequest summary4 = new SkillDtoRequest("JavaScript", 1, token2.getToken());
        SkillDtoRequest summary5 = new SkillDtoRequest("Java", 2, token3.getToken());
        SkillDtoRequest summary6 = new SkillDtoRequest("Pascal", 1, token3.getToken());
        SkillDtoRequest summary7 = new SkillDtoRequest("C++", 2, token4.getToken());
        SkillDtoRequest summary8 = new SkillDtoRequest("JavaScript", 5, token4.getToken());
        server.addSummary(gson.toJson(summary1));
        server.addSummary(gson.toJson(summary2));
        server.addSummary(gson.toJson(summary3));
        server.addSummary(gson.toJson(summary4));
        server.addSummary(gson.toJson(summary5));
        server.addSummary(gson.toJson(summary6));
        server.addSummary(gson.toJson(summary7));
        server.addSummary(gson.toJson(summary8));
        Requirement requirement1 = new Requirement("C++", 3, Require.YES);
        Requirement requirement2 = new Requirement("JavaScript", 1, Require.NO);
        Requirement requirement3 = new Requirement("Java", 2, Require.YES);
        Requirement requirement4 = new Requirement("Pascal", 4, Require.YES);
        List<Requirement> requirementList1 = new ArrayList<>();
        List<Requirement> requirementList2 = new ArrayList<>();
        requirementList1.add(requirement1);
        requirementList1.add(requirement2);
        requirementList2.add(requirement3);
        requirementList2.add(requirement4);
        RegisterEmployerDtoRequest registerEmployerDtoRequest1 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "Jek1", "1qaz2wsx&3edc");
        String jsonEmployer = gson.toJson(registerEmployerDtoRequest1);

        TokenDtoResponse token11 = gson.fromJson(server.registerEmployer(jsonEmployer), TokenDtoResponse.class);

        RequirementDtoRequest requirement11 = new RequirementDtoRequest("C++", 3, Require.YES);
        RequirementDtoRequest requirement22 = new RequirementDtoRequest("JavaScript", 1, Require.NO);
        RequirementDtoRequest requirement33 = new RequirementDtoRequest("Java", 2, Require.YES);
        RequirementDtoRequest requirement44 = new RequirementDtoRequest("Pascal", 4, Require.YES);
        List<RequirementDtoRequest> requirementList11 = new ArrayList<>();
        List<RequirementDtoRequest> requirementList22 = new ArrayList<>();
        requirementList11.add(requirement11);
        requirementList11.add(requirement22);
        requirementList22.add(requirement33);
        requirementList22.add(requirement44);

        VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest("Стажер", 16000, requirementList11, token11.getToken());
        VacancyDtoRequest vacancyDtoRequest1 = new VacancyDtoRequest("Стажер", 20000, requirementList22, token11.getToken());
        server.addVacancy(gson.toJson(vacancyDtoRequest));
        server.addVacancy(gson.toJson(vacancyDtoRequest1));
        List<Employee> employeeList = new ArrayList<>();
        Employee employeel = (Employee) server.getDataBase().getPersonByToken(token2.getToken());
        Employee employeed = (Employee) server.getDataBase().getPersonByToken(token4.getToken());
        employeeList.add(employeel);
        employeeList.add(employeed);

        List<Employee> employeeList1 = new ArrayList<>();
        Employee employeed1 = (Employee) server.getDataBase().getPersonByToken(token1.getToken());
        Employee employeed2 = (Employee) server.getDataBase().getPersonByToken(token3.getToken());
        employeeList1.add(employeed1);
        employeeList1.add(employeed2);


        assertEquals(server.getSummaryAllLevel(gson.toJson(vacancyDtoRequest)), gson.toJson(employeeList));
        assertEquals(server.getSummaryAllLevel(gson.toJson(vacancyDtoRequest1)), gson.toJson(employeeList1));

    }


    @Test
    public void testGetSummaryOnlyOneRequirement() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@ma2il.ru", "kruglov", "1998&567");
        RegisterEmployeeDtoRequest employee2 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Физицкий", "jek1@m3ail.ru", "fixkiy", "199&8567");
        RegisterEmployeeDtoRequest employee3 = new RegisterEmployeeDtoRequest("Константин", "Александрович", "Серов", "jek41@mail.ru", "serov", "199&8567");
        RegisterEmployeeDtoRequest employee4 = new RegisterEmployeeDtoRequest("Алексей", "Игоревич", "Морозов", "jek1@m6ail.ru", "morozov", "1998&567");
        String jsonEmployee1 = gson.toJson(employee1);
        String jsonEmployee2 = gson.toJson(employee2);
        String jsonEmployee3 = gson.toJson(employee3);
        String jsonEmployee4 = gson.toJson(employee4);
        TokenDtoResponse token1 = gson.fromJson(server.registerEmployee(jsonEmployee1), TokenDtoResponse.class);
        TokenDtoResponse token2 = gson.fromJson(server.registerEmployee(jsonEmployee2), TokenDtoResponse.class);
        TokenDtoResponse token3 = gson.fromJson(server.registerEmployee(jsonEmployee3), TokenDtoResponse.class);
        TokenDtoResponse token4 = gson.fromJson(server.registerEmployee(jsonEmployee4), TokenDtoResponse.class);
        SkillDtoRequest summary1 = new SkillDtoRequest("Java", 1, token1.getToken());
        SkillDtoRequest summary2 = new SkillDtoRequest("Pascal", 2, token1.getToken());
        SkillDtoRequest summary3 = new SkillDtoRequest("C++", 2, token2.getToken());
        SkillDtoRequest summary4 = new SkillDtoRequest("JavaScript", 3, token2.getToken());
        SkillDtoRequest summary5 = new SkillDtoRequest("Java", 2, token3.getToken());
        SkillDtoRequest summary6 = new SkillDtoRequest("Pascal", 3, token3.getToken());
        SkillDtoRequest summary7 = new SkillDtoRequest("C++", 3, token4.getToken());
        SkillDtoRequest summary8 = new SkillDtoRequest("JavaScript", 5, token4.getToken());
        server.addSummary(gson.toJson(summary1));
        server.addSummary(gson.toJson(summary2));
        server.addSummary(gson.toJson(summary3));
        server.addSummary(gson.toJson(summary4));
        server.addSummary(gson.toJson(summary5));
        server.addSummary(gson.toJson(summary6));
        server.addSummary(gson.toJson(summary7));
        server.addSummary(gson.toJson(summary8));
        Requirement requirement1 = new Requirement("C++", 2, Require.YES);
        Requirement requirement2 = new Requirement("Java", 1, Require.NO);
        Requirement requirement3 = new Requirement("Pascal", 2, Require.YES);
        Requirement requirement4 = new Requirement("JavaScript", 4, Require.YES);
        List<Requirement> requirementList1 = new ArrayList<>();
        List<Requirement> requirementList2 = new ArrayList<>();
        requirementList1.add(requirement1);
        requirementList1.add(requirement2);
        requirementList2.add(requirement3);
        requirementList2.add(requirement4);
        RegisterEmployerDtoRequest registerEmployerDtoRequest1 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "Jek1", "1qaz2wsx&3edc");
        String jsonEmployer = gson.toJson(registerEmployerDtoRequest1);

        TokenDtoResponse token11 = gson.fromJson(server.registerEmployer(jsonEmployer), TokenDtoResponse.class);

        RequirementDtoRequest requirement11 = new RequirementDtoRequest("C++", 2, Require.YES);
        RequirementDtoRequest requirement22 = new RequirementDtoRequest("Java", 1, Require.NO);
        RequirementDtoRequest requirement33 = new RequirementDtoRequest("Pascal", 2, Require.YES);
        RequirementDtoRequest requirement44 = new RequirementDtoRequest("JavaScript", 4, Require.YES);
        List<RequirementDtoRequest> requirementList11 = new ArrayList<>();
        List<RequirementDtoRequest> requirementList22 = new ArrayList<>();
        requirementList11.add(requirement11);
        requirementList11.add(requirement22);
        requirementList22.add(requirement33);
        requirementList22.add(requirement44);

        VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest("Стажер", 16000, requirementList11, token11.getToken());
        VacancyDtoRequest vacancyDtoRequest1 = new VacancyDtoRequest("Стажер", 20000, requirementList22, token11.getToken());
        server.addVacancy(gson.toJson(vacancyDtoRequest));
        server.addVacancy(gson.toJson(vacancyDtoRequest1));
        List<Employee> employeeList = new ArrayList<>();
        Employee employee33 = (Employee) server.getDataBase().getPersonByToken(token3.getToken());
        Employee employee22 = (Employee) server.getDataBase().getPersonByToken(token2.getToken());
        Employee employee44 = (Employee) server.getDataBase().getPersonByToken(token4.getToken());
        Employee employee11 = (Employee) server.getDataBase().getPersonByToken(token1.getToken());
        employeeList.add(employee22);
        employeeList.add(employee44);
        employeeList.add(employee11);
        employeeList.add(employee33);
}


    @Test
    public void testGetSummaryWithRequire() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@ma2il.ru", "kruglov", "1998&567");
        RegisterEmployeeDtoRequest employee2 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Физицкий", "jek1@m3ail.ru", "fixkiy", "199&8567");
        RegisterEmployeeDtoRequest employee3 = new RegisterEmployeeDtoRequest("Константин", "Александрович", "Серов", "jek41@mail.ru", "serov", "199&8567");
        RegisterEmployeeDtoRequest employee4 = new RegisterEmployeeDtoRequest("Алексей", "Игоревич", "Морозов", "jek1@m6ail.ru", "morozov", "1998&567");
        String jsonEmployee1 = gson.toJson(employee1);
        String jsonEmployee2 = gson.toJson(employee2);
        String jsonEmployee3 = gson.toJson(employee3);
        String jsonEmployee4 = gson.toJson(employee4);
        TokenDtoResponse token1 = gson.fromJson(server.registerEmployee(jsonEmployee1), TokenDtoResponse.class);
        TokenDtoResponse token2 = gson.fromJson(server.registerEmployee(jsonEmployee2), TokenDtoResponse.class);
        TokenDtoResponse token3 = gson.fromJson(server.registerEmployee(jsonEmployee3), TokenDtoResponse.class);
        TokenDtoResponse token4 = gson.fromJson(server.registerEmployee(jsonEmployee4), TokenDtoResponse.class);
        SkillDtoRequest summary1 = new SkillDtoRequest("Java", 1, token1.getToken());
        SkillDtoRequest summary2 = new SkillDtoRequest("Pascal", 2, token1.getToken());
        SkillDtoRequest summary3 = new SkillDtoRequest("C++", 2, token2.getToken());
        SkillDtoRequest summary4 = new SkillDtoRequest("JavaScript", 3, token2.getToken());
        SkillDtoRequest summary5 = new SkillDtoRequest("Java", 2, token3.getToken());
        SkillDtoRequest summary6 = new SkillDtoRequest("Pascal", 3, token3.getToken());
        SkillDtoRequest summary7 = new SkillDtoRequest("C++", 3, token4.getToken());
        SkillDtoRequest summary8 = new SkillDtoRequest("JavaScript", 5, token4.getToken());
        server.addSummary(gson.toJson(summary1));
        server.addSummary(gson.toJson(summary2));
        server.addSummary(gson.toJson(summary3));
        server.addSummary(gson.toJson(summary4));
        server.addSummary(gson.toJson(summary5));
        server.addSummary(gson.toJson(summary6));
        server.addSummary(gson.toJson(summary7));
        server.addSummary(gson.toJson(summary8));
        Requirement requirement1 = new Requirement("C++", 2, Require.YES);
        Requirement requirement2 = new Requirement("Java", 1, Require.NO);
        Requirement requirement3 = new Requirement("Pascal", 2, Require.YES);
        Requirement requirement4 = new Requirement("JavaScript", 4, Require.YES);
        List<Requirement> requirementList1 = new ArrayList<>();
        List<Requirement> requirementList2 = new ArrayList<>();
        requirementList1.add(requirement1);
        requirementList1.add(requirement2);
        requirementList2.add(requirement3);
        requirementList2.add(requirement4);
        RegisterEmployerDtoRequest registerEmployerDtoRequest1 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "Jek1", "1qaz2wsx&3edc");
        String jsonEmployer = gson.toJson(registerEmployerDtoRequest1);

        TokenDtoResponse token11 = gson.fromJson(server.registerEmployer(jsonEmployer), TokenDtoResponse.class);

        RequirementDtoRequest requirement11 = new RequirementDtoRequest("C++", 2, Require.YES);
        RequirementDtoRequest requirement22 = new RequirementDtoRequest("Java", 1, Require.NO);
        RequirementDtoRequest requirement33 = new RequirementDtoRequest("Pascal", 2, Require.YES);
        RequirementDtoRequest requirement44 = new RequirementDtoRequest("JavaScript", 4, Require.YES);
        List<RequirementDtoRequest> requirementList11 = new ArrayList<>();
        List<RequirementDtoRequest> requirementList22 = new ArrayList<>();
        requirementList11.add(requirement11);
        requirementList11.add(requirement22);
        requirementList22.add(requirement33);
        requirementList22.add(requirement44);

        VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest("Стажер", 16000, requirementList11, token11.getToken());
        VacancyDtoRequest vacancyDtoRequest1 = new VacancyDtoRequest("Стажер", 20000, requirementList22, token11.getToken());
        server.addVacancy(gson.toJson(vacancyDtoRequest));
        server.addVacancy(gson.toJson(vacancyDtoRequest1));
        List<Employee> employeeList = new ArrayList<>();

        List<Employee> employeeList1 = new ArrayList<>();
        Employee employee11 = (Employee) server.getDataBase().getPersonByToken(token2.getToken());
        employeeList1.add(employee11);
        Employee employee22 = (Employee) server.getDataBase().getPersonByToken(token4.getToken());
        employeeList1.add(employee22);


        assertEquals(server.getSummaryWithRequire(gson.toJson(vacancyDtoRequest)), gson.toJson(employeeList1));
        assertEquals(server.getSummaryWithRequire(gson.toJson(vacancyDtoRequest1)), gson.toJson(employeeList));

    }

    @Test
    public void testAddSummary() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@ma2il.ru", "kruglov", "1998&567");
        String jsonEmployee1 = gson.toJson(employee1);
        TokenDtoResponse token1 = gson.fromJson(server.registerEmployee(jsonEmployee1), TokenDtoResponse.class);
        SkillDtoRequest summary1 = new SkillDtoRequest("Java", 1, token1.getToken());
        SkillDtoRequest summary2 = new SkillDtoRequest("Pascal", 2, token1.getToken());
        String jsonString = "";
        Assertions.assertEquals(server.addSummary(gson.toJson(summary1)), gson.toJson(jsonString));
        Assertions.assertEquals(server.addSummary(gson.toJson(summary2)), gson.toJson(jsonString));
    }

    @Test
    public void testRemoveSummary() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@ma2il.ru", "kruglov", "1998&567");
        String jsonEmployee1 = gson.toJson(employee1);
        TokenDtoResponse token1 = gson.fromJson(server.registerEmployee(jsonEmployee1), TokenDtoResponse.class);
        SkillDtoRequest summary1 = new SkillDtoRequest("Java", 1, token1.getToken());
        SkillDtoRequest summary2 = new SkillDtoRequest("Pascal", 2, token1.getToken());
        String jsonString = "";
        Assertions.assertEquals(server.addSummary(gson.toJson(summary1)), gson.toJson(jsonString));
        Assertions.assertEquals(server.addSummary(gson.toJson(summary2)), gson.toJson(jsonString));
        Employee employeee = (Employee) server.getDataBase().getPersonByToken(token1.getToken());
        Assertions.assertEquals(employeee.getSummaryList().size(), 2);
        employeee.removeSummary(employeee.getSummaryByDescriptionSkill("Java"));
        Assertions.assertEquals(employeee.getSummaryList().size(), 1);
    }

    @Test
    public void testSetSummaryList() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@ma2il.ru", "kruglov", "1998&567");
        String jsonEmployee1 = gson.toJson(employee1);
        TokenDtoResponse token1 = gson.fromJson(server.registerEmployee(jsonEmployee1), TokenDtoResponse.class);
        SkillDtoRequest summary1 = new SkillDtoRequest("Java", 1, token1.getToken());
        SkillDtoRequest summary2 = new SkillDtoRequest("Pascal", 2, token1.getToken());
        Skill skill3 = new Skill("C#", 5);
        Skill skill4 = new Skill("C++", 2);
        List<Skill> skillList = new ArrayList<>();
        skillList.add(skill3);
        skillList.add(skill4);
        server.addSummary(gson.toJson(summary1));
        server.addSummary(gson.toJson(summary2));
        Employee employeee = (Employee) server.getDataBase().getPersonByToken(token1.getToken());
        employeee.setSummaryList(skillList);
        Assertions.assertEquals(employeee.getSummaryList().get(0).getDescriptionSkill(), "C#");
        Assertions.assertEquals(employeee.getSummaryList().get(1).getDescriptionSkill(), "C++");

    }

}