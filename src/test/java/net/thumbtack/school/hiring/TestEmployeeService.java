package net.thumbtack.school.hiring;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.dto.*;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestEmployeeService {
    private Server server = new Server();
    private Gson gson = new Gson();


    @Test
    public void testRegisterEmployee() throws IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest registerEmployeeDtoRequest2 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Круглов", "jek1@mail.ru", "krutov&", "12142&124412");
        String stringEmployee2 = gson.toJson(registerEmployeeDtoRequest2);
        String result = server.registerEmployee(stringEmployee2);
        Assertions.assertEquals(gson.fromJson(result, TokenDtoResponse.class).getClass(), TokenDtoResponse.class);
    }

    @Test
    public void testRegisterEmployeeWrongPassword() throws IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest registerEmployeeDtoRequest2 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Круглов", "jek1@mail.ru", "krutov&", "");
        String stringEmployee2 = gson.toJson(registerEmployeeDtoRequest2);
        String result = server.registerEmployee(stringEmployee2);
        assertTrue(result.contains(ServerErrorCode.WRONG_PASSWORD.getMessage()));
    }

    @Test
    public void testRegisterEmployeeWrongFirstName() throws IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest registerEmployeeDtoRequest2 = new RegisterEmployeeDtoRequest("", "Алексеевич", "Круглов", "jek1@mail.ru", "krutov&", "19989999&");
        String stringEmployee2 = gson.toJson(registerEmployeeDtoRequest2);
        String result = server.registerEmployee(stringEmployee2);
        assertTrue(result.contains(ServerErrorCode.WRONG_FIRST_NAME.getMessage()));
    }

    @Test
    public void testRegisterEmployeeWrongLengthLogin() throws IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest registerEmployeeDtoRequest2 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Круглов", "jek1@mail.ru", "", "1998&567");
        String stringEmployee2 = gson.toJson(registerEmployeeDtoRequest2);
        String result = server.registerEmployee(stringEmployee2);
        assertTrue(result.contains(ServerErrorCode.WRONG_LOGIN.getMessage()));
    }

    @Test
    public void testRegisterEmployeeWrongPassword2() throws IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest registerEmployeeDtoRequest2 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Круглов", "jek1@mail.ru", "kruglov", "1998567");
        String stringEmployee2 = gson.toJson(registerEmployeeDtoRequest2);
        String result = server.registerEmployee(stringEmployee2);
        assertTrue(result.contains(ServerErrorCode.WRONG_PASSWORD.getMessage()));
    }

    @Test
    public void testEditData() throws IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployee(gson.toJson(employee1)), TokenDtoResponse.class);
        EditDataEmployeeDtoRequest editDataEmployeeDtoRequest = new EditDataEmployeeDtoRequest("", "Александрович", "Круглов", "joka", "1998&567", token.getToken());
        String jsonEditData = gson.toJson(editDataEmployeeDtoRequest);
        String result = server.editDataEmployee(jsonEditData);
        assertTrue(result.contains(ServerErrorCode.WRONG_FIRST_NAME.getMessage()));
    }

    @Test
    public void testEditData1() throws IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployee(gson.toJson(employee1)), TokenDtoResponse.class);
        EditDataEmployeeDtoRequest editDataEmployeeDtoRequest = new EditDataEmployeeDtoRequest("Стас", "", "Круглов", "joka", "1998&567", token.getToken());
        String jsonEditData = gson.toJson(editDataEmployeeDtoRequest);
        String result = server.editDataEmployee(jsonEditData);
        assertTrue(result.contains(ServerErrorCode.WRONG_MIDDLE_NAME.getMessage()));
    }

    @Test
    public void testEditData2() throws IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployee(gson.toJson(employee1)), TokenDtoResponse.class);
        EditDataEmployeeDtoRequest editDataEmployeeDtoRequest = new EditDataEmployeeDtoRequest("Стас", "Алексеевич", "", "joka", "1998&567", token.getToken());
        String jsonEditData = gson.toJson(editDataEmployeeDtoRequest);
        String result = server.editDataEmployee(jsonEditData);
        assertTrue(result.contains(ServerErrorCode.WRONG_LAST_NAME.getMessage()));
    }

    @Test
    public void testEditData3() throws IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployee(gson.toJson(employee1)), TokenDtoResponse.class);
        EditDataEmployeeDtoRequest editDataEmployeeDtoRequest = new EditDataEmployeeDtoRequest("Стас", "Игоревич", "Круглов", "", "1998&567", token.getToken());
        String jsonEditData = gson.toJson(editDataEmployeeDtoRequest);
        String result = server.editDataEmployee(jsonEditData);
        assertTrue(result.contains(ServerErrorCode.WRONG_EMAIL.getMessage()));
    }

    @Test
    public void testEditData4() throws IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployee(gson.toJson(employee1)), TokenDtoResponse.class);
        EditDataEmployeeDtoRequest editDataEmployeeDtoRequest = new EditDataEmployeeDtoRequest("Стас", "Игоревич", "Круглов", "jek1@mail.ru", "", token.getToken());
        String jsonEditData = gson.toJson(editDataEmployeeDtoRequest);
        String result = server.editDataEmployee(jsonEditData);
        assertTrue(result.contains(ServerErrorCode.WRONG_PASSWORD.getMessage()));
    }

    @Test
    public void testEditSummaryRemove() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployee(gson.toJson(employee1)), TokenDtoResponse.class);
        SkillDtoRequest summary1 = new SkillDtoRequest("Java", 4, token.getToken());
        SkillDtoRequest summary2 = new SkillDtoRequest("Python", 1, token.getToken());
        server.addSummary(gson.toJson(summary1));
        server.addSummary(gson.toJson(summary2));
        Map<String, SkillDtoRequest> editSummary = new HashMap<>();
        SkillDtoRequest skillDtoRequest = new SkillDtoRequest(0);
        editSummary.put("Python", skillDtoRequest);
        EditSummaryDtoRequest editSummaryDtoRequest = new EditSummaryDtoRequest(editSummary, token.getToken());
        String jsonString = "";
        assertEquals(server.editSummary(gson.toJson(editSummaryDtoRequest)), gson.toJson(jsonString));
    }

    @Test
    public void testEditSummary() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployee(gson.toJson(employee1)), TokenDtoResponse.class);
        SkillDtoRequest summary1 = new SkillDtoRequest("Java", 4, token.getToken());
        SkillDtoRequest summary2 = new SkillDtoRequest("Python", 1, token.getToken());
        server.addSummary(gson.toJson(summary1));
        server.addSummary(gson.toJson(summary2));
        Map<String, SkillDtoRequest> editSummary = new HashMap<>();
        SkillDtoRequest skillDtoRequest = new SkillDtoRequest(5);
        editSummary.put("Python", skillDtoRequest);
        EditSummaryDtoRequest editSummaryDtoRequest = new EditSummaryDtoRequest(editSummary, token.getToken());
        String jsonString = "";
        assertEquals(server.editSummary(gson.toJson(editSummaryDtoRequest)), gson.toJson(jsonString));
    }

    @Test
    public void testEditSummaryAdd() throws ServerException, IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployee(gson.toJson(employee1)), TokenDtoResponse.class);
        SkillDtoRequest summary1 = new SkillDtoRequest("Java", 4, token.getToken());
        SkillDtoRequest summary2 = new SkillDtoRequest("Python", 1, token.getToken());
        server.addSummary(gson.toJson(summary1));
        server.addSummary(gson.toJson(summary2));
        Map<String, SkillDtoRequest> editSummary = new HashMap<>();
        SkillDtoRequest skillDtoRequest = new SkillDtoRequest(5);
        editSummary.put("Php", skillDtoRequest);
        EditSummaryDtoRequest editSummaryDtoRequest = new EditSummaryDtoRequest(editSummary, token.getToken());
        String jsonString = "";
        assertEquals(server.editSummary(gson.toJson(editSummaryDtoRequest)), gson.toJson(jsonString));
    }

    @Test
    public void testMakeInactive() throws IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoRequest token = gson.fromJson(server.registerEmployee(gson.toJson(employee1)), TokenDtoRequest.class);
        SkillDtoRequest summary1 = new SkillDtoRequest("Java", 4, token.getToken());
        SkillDtoRequest summary2 = new SkillDtoRequest("Python", 1, token.getToken());
        server.addSummary(gson.toJson(summary1));
        server.addSummary(gson.toJson(summary2));
        String jsonString = "";
        TokenDtoRequest tokenDtoRequest = new TokenDtoRequest(token.getToken());
        assertEquals(server.makeInactive(gson.toJson(token)), gson.toJson(jsonString));
    }

    @Test
    public void testMakeActive() throws IOException {
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoRequest token = gson.fromJson(server.registerEmployee(gson.toJson(employee1)), TokenDtoRequest.class);
        SkillDtoRequest summary1 = new SkillDtoRequest("Java", 4, token.getToken());
        SkillDtoRequest summary2 = new SkillDtoRequest("Python", 1, token.getToken());
        server.addSummary(gson.toJson(summary1));
        server.addSummary(gson.toJson(summary2));
        String jsonString = "";
        TokenDtoRequest tokenDtoRequest = new TokenDtoRequest(token.getToken());
        assertEquals(server.makeActive(gson.toJson(token)), gson.toJson(jsonString));
    }

    @Test
    public void testEditDataEmployee() throws IOException, ServerException {
        String savedData = "savedDatafileName";
        String emptyString = "";
        server.startServer(savedData);
        RegisterEmployeeDtoRequest employee1 = new RegisterEmployeeDtoRequest("Станислав", "Григорьевич", "Круглов", "jek1@mail.ru", "kruglov", "1998&567");
        TokenDtoResponse token = gson.fromJson(server.registerEmployee(gson.toJson(employee1)), TokenDtoResponse.class);
        EditDataEmployeeDtoRequest editDataEmployeeDtoRequest = new EditDataEmployeeDtoRequest("Стас", "Григорьевич", "Крутов", "jek1@mail.ru", "1998&567", token.getToken());
        Assertions.assertEquals(server.editDataEmployee(gson.toJson(editDataEmployeeDtoRequest)),gson.toJson(emptyString));
    }

}
