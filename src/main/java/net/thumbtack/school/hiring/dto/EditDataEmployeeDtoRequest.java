package net.thumbtack.school.hiring.dto;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;

public class EditDataEmployeeDtoRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private String token;

    public EditDataEmployeeDtoRequest(String firstName, String middleName, String lastName, String email, String password, String token) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.token = token;
    }

    public String getFirstName() {
        return firstName;
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

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void validate(EditDataEmployeeDtoRequest editDataEmployeeDtoRequest) throws ServerException {
        if (editDataEmployeeDtoRequest.getFirstName() != null && editDataEmployeeDtoRequest.getFirstName().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_FIRST_NAME);
        }
        if (editDataEmployeeDtoRequest.getMiddleName() != null && editDataEmployeeDtoRequest.getMiddleName().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_MIDDLE_NAME);
        }
        if (editDataEmployeeDtoRequest.getLastName() != null && editDataEmployeeDtoRequest.getLastName().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_LAST_NAME);
        }
        if (editDataEmployeeDtoRequest.getEmail() != null && editDataEmployeeDtoRequest.getEmail().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_EMAIL);
        }
        if (editDataEmployeeDtoRequest.getPassword() != null && editDataEmployeeDtoRequest.getPassword().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_PASSWORD);
        }
    }
}
