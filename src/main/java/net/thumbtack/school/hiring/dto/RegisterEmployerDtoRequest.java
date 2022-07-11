package net.thumbtack.school.hiring.dto;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;

public class RegisterEmployerDtoRequest {
    private String nameCompany;
    private String addressCompany;
    private String email;
    private String firstName;
    private String middleName;
    private String lastName;
    private String login;
    private String password;

    public RegisterEmployerDtoRequest() {
    }

    public RegisterEmployerDtoRequest(String nameCompany, String addressCompany, String firstName, String lastName, String email, String login, String password) {
        this.nameCompany = nameCompany;
        this.addressCompany = addressCompany;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public RegisterEmployerDtoRequest(String nameCompany, String addressCompany, String firstName, String middleName, String lastName, String email, String login, String password) {
        this.nameCompany = nameCompany;
        this.addressCompany = addressCompany;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
    }



    public String getNameCompany() {
        return nameCompany;
    }

    public String getAddressCompany() {
        return addressCompany;
    }

    public String getEmail() {
        return email;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void validate(RegisterEmployerDtoRequest registerEmployerDtoRequest) throws ServerException {
        if (registerEmployerDtoRequest.getNameCompany() == null || registerEmployerDtoRequest.getNameCompany().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_NAME_COMPANY);
        }
        if (registerEmployerDtoRequest.getAddressCompany() == null || registerEmployerDtoRequest.getAddressCompany().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_ADDRESS_COMPANY);
        }
        if (registerEmployerDtoRequest.getFirstName() == null || registerEmployerDtoRequest.getFirstName().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_FIRST_NAME);
        }
        if (registerEmployerDtoRequest.getMiddleName() == null || registerEmployerDtoRequest.getMiddleName().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_MIDDLE_NAME);
        }
        if (registerEmployerDtoRequest.getLastName() == null || registerEmployerDtoRequest.getLastName().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_LAST_NAME);
        }
        if (registerEmployerDtoRequest.getEmail() == null || registerEmployerDtoRequest.getEmail().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_EMAIL);
        }
        if (registerEmployerDtoRequest.getPassword() == null || registerEmployerDtoRequest.getPassword().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_PASSWORD);
        }
    }
}
