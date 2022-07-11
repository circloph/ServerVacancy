package net.thumbtack.school.hiring.service;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.exception.ServerException;
import net.thumbtack.school.hiring.daoimpl.PersonDaoImpl;
import net.thumbtack.school.hiring.dto.ErrorDtoResponse;
import net.thumbtack.school.hiring.dto.LoginDtoRequest;
import net.thumbtack.school.hiring.dto.TokenDtoResponse;
import net.thumbtack.school.hiring.users.Person;

import java.util.UUID;

public class LoginService {
    private PersonDaoImpl employeeDao;

    public LoginService(PersonDaoImpl employeeDao) {
        this.employeeDao = employeeDao;
    }

    public String login(String requestJsonString) {
        Gson gson = new Gson();
        try {
            LoginDtoRequest loginDtoRequest = gson.fromJson(requestJsonString, LoginDtoRequest.class);
            loginDtoRequest.validate(loginDtoRequest);
            Person person = employeeDao.getPersonByLogin(loginDtoRequest.getLogin());
            String token = UUID.randomUUID().toString();
            employeeDao.insertAuthorized(person, token);
            return gson.toJson(new TokenDtoResponse(token));
        } catch (ServerException e) {
            return ErrorDtoResponse.getJsonStringResponse(e.getMessage());
        }
    }

    public void logout(String token) throws ServerException {
        employeeDao.removeKey(token);
    }
}
