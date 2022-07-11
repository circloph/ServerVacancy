package net.thumbtack.school.hiring;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.dto.*;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.users.Require;
import net.thumbtack.school.hiring.users.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestDataBase {

    @Test
    public void testGetRegisteredPersons() throws IOException, ServerException {
    Server server = new Server();
    String savedData = "savedDatafileName";
    server.startServer(savedData);
    Gson gson = new Gson();

    RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@ma2il.ru", "kruglov", "1998&567");
    RegisterEmployeeDtoRequest employee2 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Физицкий", "jek1@m3ail.ru", "fixkiy", "199&8567");
    RegisterEmployeeDtoRequest employee3 = new RegisterEmployeeDtoRequest("Константин", "Александрович", "Серов", "jek41@mail.ru", "serov", "199&8567");
    RegisterEmployeeDtoRequest employee4 = new RegisterEmployeeDtoRequest("Алексей", "Игоревич", "Морозов", "jek1@m6ail.ru", "morozov", "1998&567");

    server.registerEmployee(gson.toJson(employee1));
    server.registerEmployee(gson.toJson(employee2));
    server.registerEmployee(gson.toJson(employee3));
    server.registerEmployee(gson.toJson(employee4));

    RegisterEmployerDtoRequest registerEmployerDtoRequest1 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "krurur", "1qaz2wsx&3edc");
    String jsonEmployer = gson.toJson(registerEmployerDtoRequest1);

    server.registerEmployer(jsonEmployer);
    Assertions.assertEquals(server.getDataBase().getRegisteredPersons().size(), 5);

    }

    @Test
    public void testAuthorizedPerson() throws IOException, ServerException {
        Server server = new Server();
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        Gson gson = new Gson();

        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@ma2il.ru", "kruglov", "1998&567");
        RegisterEmployeeDtoRequest employee2 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Физицкий", "jek1@m3ail.ru", "fixkiy", "199&8567");
        RegisterEmployeeDtoRequest employee3 = new RegisterEmployeeDtoRequest("Константин", "Александрович", "Серов", "jek41@mail.ru", "serov", "199&8567");
        RegisterEmployeeDtoRequest employee4 = new RegisterEmployeeDtoRequest("Алексей", "Игоревич", "Морозов", "jek1@m6ail.ru", "morozov", "1998&567");

        String token = gson.fromJson(server.registerEmployee(gson.toJson(employee1)), TokenDtoResponse.class).getToken();
        server.registerEmployee(gson.toJson(employee2));
        server.registerEmployee(gson.toJson(employee3));
        server.registerEmployee(gson.toJson(employee4));

        RegisterEmployerDtoRequest registerEmployerDtoRequest1 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "krurur", "1qaz2wsx&3edc");
        String jsonEmployer = gson.toJson(registerEmployerDtoRequest1);

        server.registerEmployer(jsonEmployer);
        Assertions.assertEquals(server.getDataBase().getAuthorizedPersons().size(), 5);
        server.getLoginService().logout(token);
        Assertions.assertEquals(server.getDataBase().getAuthorizedPersons().size(), 4);

    }

    @Test
    public void testGetPersonByLogin() throws IOException, ServerException {
        Server server = new Server();
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        Gson gson = new Gson();

        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@ma2il.ru", "kruglov", "1998&567");
        RegisterEmployeeDtoRequest employee2 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Физицкий", "jek1@m3ail.ru", "fixkiy", "199&8567");
        RegisterEmployeeDtoRequest employee3 = new RegisterEmployeeDtoRequest("Константин", "Александрович", "Серов", "jek41@mail.ru", "serov", "199&8567");
        RegisterEmployeeDtoRequest employee4 = new RegisterEmployeeDtoRequest("Алексей", "Игоревич", "Морозов", "jek1@m6ail.ru", "morozov", "1998&567");

        server.registerEmployee(gson.toJson(employee2));
        server.registerEmployee(gson.toJson(employee3));
        server.registerEmployee(gson.toJson(employee4));

        RegisterEmployerDtoRequest registerEmployerDtoRequest1 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "krurur", "1qaz2wsx&3edc");
        String jsonEmployer = gson.toJson(registerEmployerDtoRequest1);

        server.registerEmployer(jsonEmployer);

        Assertions.assertEquals(server.getDataBase().getPersonByLogin(employee2.getLogin()).getLogin(), "fixkiy");
    }

    @Test
    public void testRemoveKey() throws IOException, ServerException {
        Server server = new Server();
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        Gson gson = new Gson();

        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@ma2il.ru", "kruglov", "1998&567");
        RegisterEmployeeDtoRequest employee2 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Физицкий", "jek1@m3ail.ru", "fixkiy", "199&8567");
        RegisterEmployeeDtoRequest employee3 = new RegisterEmployeeDtoRequest("Константин", "Александрович", "Серов", "jek41@mail.ru", "serov", "199&8567");
        RegisterEmployeeDtoRequest employee4 = new RegisterEmployeeDtoRequest("Алексей", "Игоревич", "Морозов", "jek1@m6ail.ru", "morozov", "1998&567");

        String token = gson.fromJson(server.registerEmployee(gson.toJson(employee1)), TokenDtoResponse.class).getToken();
        server.registerEmployee(gson.toJson(employee2));
        server.registerEmployee(gson.toJson(employee3));
        server.registerEmployee(gson.toJson(employee4));

        RegisterEmployerDtoRequest registerEmployerDtoRequest1 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "krurur", "1qaz2wsx&3edc");
        String jsonEmployer = gson.toJson(registerEmployerDtoRequest1);

        server.registerEmployer(jsonEmployer);

        Assertions.assertEquals(server.getDataBase().getAuthorizedPersons().size(), 5);
        server.getDataBase().removeKey(token);
        Assertions.assertEquals(server.getDataBase().getAuthorizedPersons().size(), 4);
    }

    @Test
    public void testGetVacancies() throws IOException, ServerException {
    Server server = new Server();
    String savedData = "savedDatafileName";
    server.startServer(savedData);
    Gson gson = new Gson();
    RegisterEmployerDtoRequest employeer = new RegisterEmployerDtoRequest("test", "test16", "Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
    TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employeer)), TokenDtoResponse.class);
    RequirementDtoRequest requirement1 = new RequirementDtoRequest("Java", 2, Require.YES);
    RequirementDtoRequest requirement2 = new RequirementDtoRequest("Pascal", 1, Require.NO);
    RequirementDtoRequest requirement3 = new RequirementDtoRequest("C++", 4, Require.YES);
    RequirementDtoRequest requirement4 = new RequirementDtoRequest("JavaScript", 3, Require.YES);
    List<RequirementDtoRequest> list1 = new ArrayList<>();
    List<RequirementDtoRequest> list2 = new ArrayList<>();
    List<Vacancy> list3 = new ArrayList<>();
    list1.add(requirement1);
    list1.add(requirement2);
    list2.add(requirement3);
    list2.add(requirement4);
    VacancyDtoRequest vacancy1 = new VacancyDtoRequest("Разработчик", 16000, list1, token.getToken());
    VacancyDtoRequest vacancy2 = new VacancyDtoRequest("Стажер", 10000, list2, token.getToken());
    server.addVacancy(gson.toJson(vacancy1));
    server.addVacancy(gson.toJson(vacancy2));

    Requirement requirement11 = new Requirement("Java", 2, Require.YES);
    Requirement requirement22 = new Requirement("Pascal", 1, Require.NO);
    Requirement requirement33 = new Requirement("C++", 4, Require.YES);
    Requirement requirement44 = new Requirement("JavaScript", 3, Require.YES);
    List<Requirement> list11 = new ArrayList<>();
    List<Requirement> list22 = new ArrayList<>();
    list11.add(requirement11);
    list11.add(requirement22);
    list22.add(requirement33);
    list22.add(requirement44);

    list3.add(new Vacancy(vacancy1.getNamePosition(), vacancy1.getSalary(), list11));
    list3.add(new Vacancy(vacancy2.getNamePosition(), vacancy2.getSalary(), list22));

    Assertions.assertEquals(server.getDataBase().getVacancies(), list3);
    }

    @Test
    public void testGetEmployees() throws IOException, ServerException {
        Server server = new Server();
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        Gson gson = new Gson();

        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@ma2il.ru", "kruglov", "1998&567");
        RegisterEmployeeDtoRequest employee2 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Физицкий", "jek1@m3ail.ru", "fixkiy", "199&8567");
        RegisterEmployeeDtoRequest employee3 = new RegisterEmployeeDtoRequest("Константин", "Александрович", "Серов", "jek41@mail.ru", "serov", "199&8567");
        RegisterEmployeeDtoRequest employee4 = new RegisterEmployeeDtoRequest("Алексей", "Игоревич", "Морозов", "jek1@m6ail.ru", "morozov", "1998&567");

        server.registerEmployee(gson.toJson(employee1));
        server.registerEmployee(gson.toJson(employee2));
        server.registerEmployee(gson.toJson(employee3));
        server.registerEmployee(gson.toJson(employee4));

        RegisterEmployerDtoRequest registerEmployerDtoRequest1 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "krurur", "1qaz2wsx&3edc");
        String jsonEmployer = gson.toJson(registerEmployerDtoRequest1);

        server.registerEmployer(jsonEmployer);

        Assertions.assertEquals(server.getDataBase().getEmployees().size(), 4);
    }

    @Test
    public void testGetPersonBySkill() throws IOException, ServerException {
        Server server = new Server();
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        Gson gson = new Gson();

        Person employee1 = new Employee("Станислав", "Григорьевич", "Круглов", "jek1@ma2il.ru", "kruglov", "1998&567");
        Person employee2 = new Employee("Евгений", "Алексеевич", "Физицкий", "jek1@m3ail.ru", "fixkiy", "199&8567");
        Person employee3 = new Employee("Константин", "Александрович", "Серов", "jek41@mail.ru", "serov", "199&8567");
        Person employee4 = new Employee("Алексей", "Игоревич", "Морозов", "jek1@m6ail.ru", "morozov", "1998&567");

        Skill skill1 = new Skill("java", 1);
        Skill skill2 = new Skill("java1", 2);
        Skill skill3 = new Skill("java2", 3);
        Skill skill4 = new Skill("java3", 4);
        Skill skill5 = new Skill("java4", 4);
        server.getDataBase().insertPersonsWithSkill(skill1, employee1);
        server.getDataBase().insertPersonsWithSkill(skill1, employee2);
        server.getDataBase().insertPersonsWithSkill(skill1, employee3);
        server.getDataBase().insertPersonsWithSkill(skill4, employee4);
        server.getDataBase().insertPersonsWithSkill(skill5, employee4);
    }
}
