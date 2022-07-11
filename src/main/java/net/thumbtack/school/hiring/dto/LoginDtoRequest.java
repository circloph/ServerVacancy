package net.thumbtack.school.hiring.dto;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;

public class LoginDtoRequest {
    private String login;
    private String password;

    public LoginDtoRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void validate(LoginDtoRequest loginDtoRequest) throws ServerException {
        if (loginDtoRequest.getLogin() == null || loginDtoRequest.getLogin().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_LOGIN);
        }
        if (loginDtoRequest.getPassword() == null || loginDtoRequest.getPassword().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_PASSWORD);
        }
        if (loginDtoRequest.getLogin().length() < 3) {
            throw new ServerException(ServerErrorCode.WRONG_LENGHT_LOGIN);
        }
        if (loginDtoRequest.getPassword().length() < 6) {
            throw new ServerException(ServerErrorCode.WRONG_LENGHT_PASSWORD);
        }
        if (!loginDtoRequest.getPassword().matches("(.*)&(.*)")) {
            throw new ServerException(ServerErrorCode.WRONG_PASSWORD);
        }
    }
}
