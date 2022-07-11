package net.thumbtack.school.hiring;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.dto.LoginDtoRequest;
import net.thumbtack.school.hiring.dto.RegisterEmployeeDtoRequest;
import net.thumbtack.school.hiring.dto.TokenDtoResponse;
import net.thumbtack.school.hiring.server.Server;
import net.thumbtack.school.hiring.exception.ServerException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;


public class TestLoginService {

    @Test
    public void testLogin() throws ServerException, IOException {
        Server server = new Server();
        String savedData = "savedDatafileName";
        server.startServer(savedData);
        Gson gson = new Gson();
        LoginDtoRequest loginDtoRequest = new LoginDtoRequest("Jek1", "1234&5678");
        String requestJsonString = gson.toJson(loginDtoRequest);
        RegisterEmployeeDtoRequest registerEmployeeDtoRequest2 = new RegisterEmployeeDtoRequest("Евгений", "Алексеевич", "Круглов", "jek1@mail.ru", "Jek1", "1234&5678");
        String employee = gson.toJson(registerEmployeeDtoRequest2);
        TokenDtoResponse tokenDtoResponse = gson.fromJson(server.registerEmployee(employee), TokenDtoResponse.class);
        server.logout(tokenDtoResponse.getToken());
        server.login(requestJsonString);
        Assertions.assertNotEquals(server.login(requestJsonString), gson.toJson(tokenDtoResponse));
    }
}
