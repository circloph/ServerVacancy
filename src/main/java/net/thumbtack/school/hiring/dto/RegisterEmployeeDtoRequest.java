package net.thumbtack.school.hiring.dto;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;

public class RegisterEmployeeDtoRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String login;
    private String password;

    public RegisterEmployeeDtoRequest() {
    }

    public RegisterEmployeeDtoRequest(String firstName, String middleName, String lastName, String email, String login, String password) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPassword() {
        return password;
    }

    public void validate(RegisterEmployeeDtoRequest registerEmployeeDtoRequest) throws ServerException {
        if (registerEmployeeDtoRequest.getFirstName() == null || registerEmployeeDtoRequest.getFirstName().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_FIRST_NAME);
        }
        if (registerEmployeeDtoRequest.getMiddleName() == null || registerEmployeeDtoRequest.getMiddleName().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_MIDDLE_NAME);
        }
        if (registerEmployeeDtoRequest.getLastName() == null || registerEmployeeDtoRequest.getLastName().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_LAST_NAME);
        }
        if (registerEmployeeDtoRequest.getEmail() == null || registerEmployeeDtoRequest.getEmail().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_EMAIL);
        }
        if (registerEmployeeDtoRequest.getLogin() == null || registerEmployeeDtoRequest.getLogin().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_LOGIN);
        }
        if (registerEmployeeDtoRequest.getPassword() == null || registerEmployeeDtoRequest.getPassword().equals("") || !registerEmployeeDtoRequest.getPassword().matches("(.*)&(.*)")) {
            throw new ServerException(ServerErrorCode.WRONG_PASSWORD);
        }
    }
}
