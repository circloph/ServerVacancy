package net.thumbtack.school.hiring;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.dto.*;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.users.Require;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TestServer {
    private Server server = new Server();
    private Gson gson = new Gson();

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void testStartServer() throws IOException {
        File file1 = temporaryFolder.newFile("savedDatafileName.txt");
        server.startServer(file1.getAbsolutePath());
        Assertions.assertTrue(file1.length() == 0);
    }

    @Test
    public void testStopServer() throws ServerException, IOException {
        File file = temporaryFolder.newFile("savedDatafileName.txt");
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
        SkillDtoRequest summary2 = new SkillDtoRequest("Pascal", 2, token1.getToken());
        SkillDtoRequest summary3 = new SkillDtoRequest("C++", 4, token2.getToken());
        SkillDtoRequest summary4 = new SkillDtoRequest("JavaScript", 3, token2.getToken());
        SkillDtoRequest summary5 = new SkillDtoRequest("Java", 2, token3.getToken());
        SkillDtoRequest summary6 = new SkillDtoRequest("Pascal", 1, token3.getToken());
        SkillDtoRequest summary7 = new SkillDtoRequest("C++", 4, token4.getToken());
        SkillDtoRequest summary8 = new SkillDtoRequest("JavaScript", 5, token4.getToken());
        server.addSummary(gson.toJson(summary1));
        server.addSummary(gson.toJson(summary2));
        server.addSummary(gson.toJson(summary3));
        server.addSummary(gson.toJson(summary4));
        server.addSummary(gson.toJson(summary5));
        server.addSummary(gson.toJson(summary6));
        server.addSummary(gson.toJson(summary7));
        server.addSummary(gson.toJson(summary8));
        RequirementDtoRequest requirement1 = new RequirementDtoRequest("C++", 3, Require.YES);
        RequirementDtoRequest requirement2 = new RequirementDtoRequest("JavaScript", 1, Require.NO);
        RequirementDtoRequest requirement3 = new RequirementDtoRequest("C++", 1, Require.YES);
        RequirementDtoRequest requirement4 = new RequirementDtoRequest("JavaScript", 4, Require.YES);
        List<RequirementDtoRequest> requirementList1 = new ArrayList<>();
        List<RequirementDtoRequest> requirementList2 = new ArrayList<>();
        requirementList1.add(requirement1);
        requirementList1.add(requirement2);
        requirementList2.add(requirement3);
        requirementList2.add(requirement4);
        RegisterEmployerDtoRequest registerEmployerDtoRequest1 = new RegisterEmployerDtoRequest("test", "test16", "jek1_e@mail.ru", "Евгений", "Алексеевич", "Круглов", "Jek1", "1qaz2wsx&3edc");
        String jsonEmployer = gson.toJson(registerEmployerDtoRequest1);

        TokenDtoResponse token11 = gson.fromJson(server.registerEmployer(jsonEmployer), TokenDtoResponse.class);

        VacancyDtoRequest vacancyDtoRequest = new VacancyDtoRequest("Стажер", 16000, requirementList1, token11.getToken());
        VacancyDtoRequest vacancyDtoRequest1 = new VacancyDtoRequest("Стажер", 20000, requirementList2, token11.getToken());
        server.addVacancy(gson.toJson(vacancyDtoRequest));
        server.addVacancy(gson.toJson(vacancyDtoRequest1));

        server.stopServer(file.getAbsolutePath());
        Assertions.assertTrue(file.exists());
    }
}
