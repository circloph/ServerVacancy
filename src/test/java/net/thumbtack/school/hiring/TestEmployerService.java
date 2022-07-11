package net.thumbtack.school.hiring;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.dto.*;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.users.Require;
import net.thumbtack.school.hiring.users.Employer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEmployerService {
    private Server server = new Server();
    private Gson gson = new Gson();

    @Test
    public void testRegisterEmployer() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest registerEmployerDtoRequest = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "Jek1", "1qaz2wsx&3edc");
        String jsonString = gson.toJson(registerEmployerDtoRequest);
        Assertions.assertEquals(gson.fromJson(server.registerEmployer(jsonString), TokenDtoResponse.class).getClass(), TokenDtoResponse.class);
    }

    @Test
    public void testEditDataEmployer() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employeer = new RegisterEmployerDtoRequest("test", "test16", "Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employeer)), TokenDtoResponse.class);
        EditDataEmployerDtoRequest editDataEmployerDtoRequest = new EditDataEmployerDtoRequest(null, "proverka", "", "Александрович", "Круглов", "joka", "1998&567", token.getToken());
        String jsonEditData = gson.toJson(editDataEmployerDtoRequest);
        String result = server.editDataEmployer(jsonEditData);
        assertTrue(result.contains(ServerErrorCode.WRONG_FIRST_NAME.getMessage()));
    }

    @Test
    public void testEditDataEmployer1() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employeer = new RegisterEmployerDtoRequest("test", "test16", "Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employeer)), TokenDtoResponse.class);
        EditDataEmployerDtoRequest editDataEmployerDtoRequest = new EditDataEmployerDtoRequest("yandex", "proverka", "Артем", "", "Круглов", "joka", "1998&567", token.getToken());
        String jsonEditData = gson.toJson(editDataEmployerDtoRequest);
        String result = server.editDataEmployer(jsonEditData);
        assertTrue(result.contains(ServerErrorCode.WRONG_MIDDLE_NAME.getMessage()));
    }

    @Test
    public void testEditDataEmployer2() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employeer = new RegisterEmployerDtoRequest("test", "test16", "Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employeer)), TokenDtoResponse.class);
        EditDataEmployerDtoRequest editDataEmployerDtoRequest = new EditDataEmployerDtoRequest("yandex", "proverka", "Николай", "Александрович", "", "joka", "1998&567", token.getToken());
        String jsonEditData = gson.toJson(editDataEmployerDtoRequest);
        String result = server.editDataEmployer(jsonEditData);
        assertTrue(result.contains(ServerErrorCode.WRONG_LAST_NAME.getMessage()));
    }

    @Test
    public void testEditDataEmployer3() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employeer = new RegisterEmployerDtoRequest("test", "test16", "Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employeer)), TokenDtoResponse.class);
        EditDataEmployerDtoRequest editDataEmployerDtoRequest = new EditDataEmployerDtoRequest("yandex", "proverka", "Руслан", "Александрович", "Круглов", "", "1998&567", token.getToken());
        String jsonEditData = gson.toJson(editDataEmployerDtoRequest);
        String result = server.editDataEmployer(jsonEditData);
        assertTrue(result.contains(ServerErrorCode.WRONG_EMAIL.getMessage()));
    }

    @Test
    public void testEditDataEmployer4() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employeer = new RegisterEmployerDtoRequest("test", "test16", "Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employeer)), TokenDtoResponse.class);
        EditDataEmployerDtoRequest editDataEmployerDtoRequest = new EditDataEmployerDtoRequest("thum", "proverka", "Арсен", "Александрович", "Круглов", "joka", "", token.getToken());
        String jsonEditData = gson.toJson(editDataEmployerDtoRequest);
        String result = server.editDataEmployer(jsonEditData);
        assertTrue(result.contains(ServerErrorCode.WRONG_PASSWORD.getMessage()));
    }

    @Test
    public void testEditVacancy() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employeer = new RegisterEmployerDtoRequest("test", "test16", "Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employeer)), TokenDtoResponse.class);
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

        Map<String, VacancyDtoRequest> editVacancy = new HashMap<>();
        Map<String, RequirementDtoRequest> editRequirement = new HashMap<>();

        String keyVacancy = "Стажер";
        VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest("Курсант", 1000);
        editVacancy.put(keyVacancy, vacancyDtoRequest);

        String keyRequirement = "C++";
        RequirementDtoRequest requirementDtoRequest = new RequirementDtoRequest("Php", 5, Require.NO);
        editRequirement.put(keyRequirement, requirementDtoRequest);

        EditVacancyDtoRequest editVacancyDtoRequest = new EditVacancyDtoRequest(editVacancy, editRequirement, token.getToken());
        String jsonString = "";
        assertEquals(server.editVacancy(gson.toJson(editVacancyDtoRequest)), gson.toJson(jsonString));

    }

    @Test
    public void testEditVacancy1() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employeer = new RegisterEmployerDtoRequest("test", "test16", "Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employeer)), TokenDtoResponse.class);
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

        Map<String, VacancyDtoRequest> editVacancy = new HashMap<>();
        Map<String, RequirementDtoRequest> editRequirement = new HashMap<>();

        String keyVacancy = "Стажер";
        VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest("Курсант", 1000);
        editVacancy.put(keyVacancy, vacancyDtoRequest);

        String keyRequirement = null;
        RequirementDtoRequest requirementDtoRequest = new RequirementDtoRequest(null, 0, null);
        editRequirement.put(keyRequirement, requirementDtoRequest);

        EditVacancyDtoRequest editVacancyDtoRequest = new EditVacancyDtoRequest(editVacancy, editRequirement, token.getToken());
        String jsonString = "";
        server.editVacancy(gson.toJson(editVacancyDtoRequest));
        assertEquals(server.editVacancy(gson.toJson(editVacancyDtoRequest)), gson.toJson(jsonString));

    }

    @Test
    public void testEditVacancy2() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employeer = new RegisterEmployerDtoRequest("test", "test16", "Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employeer)), TokenDtoResponse.class);
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

        Map<String, VacancyDtoRequest> editVacancy = new HashMap<>();
        Map<String, RequirementDtoRequest> editRequirement = new HashMap<>();

        String keyVacancy = "Стажер";
        VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest(null, 1000);
        editVacancy.put(keyVacancy, vacancyDtoRequest);

        String keyRequirement = "C++";
        RequirementDtoRequest requirementDtoRequest = new RequirementDtoRequest("Php", 5, Require.NO);
        editRequirement.put(keyRequirement, requirementDtoRequest);

        EditVacancyDtoRequest editVacancyDtoRequest = new EditVacancyDtoRequest(editVacancy, editRequirement, token.getToken());
        String jsonString = "";
        server.editVacancy(gson.toJson(editVacancyDtoRequest));
        assertEquals(server.editVacancy(gson.toJson(editVacancyDtoRequest)), gson.toJson(jsonString));

    }

    @Test
    public void testEditVacancyRemoveVacancy() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employeer = new RegisterEmployerDtoRequest("test", "test16", "Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employeer)), TokenDtoResponse.class);
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

        Map<String, VacancyDtoRequest> editVacancy = new HashMap<>();
        Map<String, RequirementDtoRequest> editRequirement = new HashMap<>();

        String keyVacancy = "Стажер";
        VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest(null, 0);
        editVacancy.put(keyVacancy, vacancyDtoRequest);

        String keyRequirement = null;
        RequirementDtoRequest requirementDtoRequest = new RequirementDtoRequest(null, 0, null);
        editRequirement.put(keyRequirement, requirementDtoRequest);

        EditVacancyDtoRequest editVacancyDtoRequest = new EditVacancyDtoRequest(editVacancy, editRequirement, token.getToken());
        String jsonString = "";
        server.editVacancy(gson.toJson(editVacancyDtoRequest));
        assertEquals(server.editVacancy(gson.toJson(editVacancyDtoRequest)), gson.toJson(jsonString));
    }

    @Test
    public void testEditVacancyAddRequirement() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employeer = new RegisterEmployerDtoRequest("test", "test16", "Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employeer)), TokenDtoResponse.class);
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

        Map<String, VacancyDtoRequest> editVacancy = new HashMap<>();
        Map<String, RequirementDtoRequest> editRequirement = new HashMap<>();

        String keyVacancy = "Стажер";
        VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest(null, 0);
        editVacancy.put(keyVacancy, vacancyDtoRequest);

        String keyRequirement = null;
        RequirementDtoRequest requirementDtoRequest = new RequirementDtoRequest("Тестер", 30000, Require.YES);
        editRequirement.put(keyRequirement, requirementDtoRequest);

        EditVacancyDtoRequest editVacancyDtoRequest = new EditVacancyDtoRequest(editVacancy, editRequirement, token.getToken());
        String jsonString = "";
        server.editVacancy(gson.toJson(editVacancyDtoRequest));
        assertEquals(server.editVacancy(gson.toJson(editVacancyDtoRequest)), gson.toJson(jsonString));

    }

    @Test
    public void testEditVacancyRemoveRequirement() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employeer = new RegisterEmployerDtoRequest("test", "test16", "Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employeer)), TokenDtoResponse.class);
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

        Map<String, VacancyDtoRequest> editVacancy = new HashMap<>();
        Map<String, RequirementDtoRequest> editRequirement = new HashMap<>();

        String keyVacancy = "Стажер";
        VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest(null, 0);
        editVacancy.put(keyVacancy, vacancyDtoRequest);

        String keyRequirement = "JavaScript";
        RequirementDtoRequest requirementDtoRequest = new RequirementDtoRequest(null, 0, null);
        editRequirement.put(keyRequirement, requirementDtoRequest);

        EditVacancyDtoRequest editVacancyDtoRequest = new EditVacancyDtoRequest(editVacancy, editRequirement, token.getToken());
        String jsonString = "";
        server.editVacancy(gson.toJson(editVacancyDtoRequest));
        assertEquals(server.editVacancy(gson.toJson(editVacancyDtoRequest)), gson.toJson(jsonString));

    }

    @Test
    public void testEditDataEmployer5() throws IOException, ServerException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("test", "test16","Стас", "Алексеевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployer(gson.toJson(employer1)), TokenDtoResponse.class);
        EditDataEmployerDtoRequest editDataEmployerDtoRequest = new EditDataEmployerDtoRequest("test", "test16","Стас", "Алексеевич", "Круглов", "jek1@mail.ru", "1998&567", token.getToken());
        Assertions.assertEquals(server.editDataEmployer(gson.toJson(editDataEmployerDtoRequest)),gson.toJson("") );
    }


    @Test
    public void testGetVacancyByNamePosition() throws IOException, ServerException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployerDtoRequest employer1 = new RegisterEmployerDtoRequest("test", "test16","jek1@mail.ru", "Стас", "Алексеевич", "Круглов", "kruglov", "1998&567");
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
        Assertions.assertEquals(employer.getVacancyByNamePosition("Разработчик").getSalary(),16000 );
    }
}
