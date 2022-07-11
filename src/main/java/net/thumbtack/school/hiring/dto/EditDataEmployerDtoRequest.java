package net.thumbtack.school.hiring.dto;

import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;

public class EditDataEmployerDtoRequest {
    private String nameCompany;
    private String addressCompany;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private String token;

    public EditDataEmployerDtoRequest() {
    }

    public EditDataEmployerDtoRequest(String nameCompany, String addressCompany, String firstName, String middleName, String lastName, String email, String password, String token) {
        this.nameCompany = nameCompany;
        this.addressCompany = addressCompany;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.token = token;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public String getAddressCompany() {
        return addressCompany;
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

    public void validate(EditDataEmployerDtoRequest editDataEmployerDtoRequest) throws ServerException {
        if (editDataEmployerDtoRequest.getNameCompany() != null && editDataEmployerDtoRequest.getNameCompany().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_NAME_COMPANY);
        }
        if (editDataEmployerDtoRequest.getAddressCompany() != null && editDataEmployerDtoRequest.getAddressCompany().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_ADDRESS_COMPANY);
        }
        if (editDataEmployerDtoRequest.getFirstName() != null && editDataEmployerDtoRequest.getFirstName().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_FIRST_NAME);
        }
        if (editDataEmployerDtoRequest.getMiddleName() != null && editDataEmployerDtoRequest.getMiddleName().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_MIDDLE_NAME);
        }
        if (editDataEmployerDtoRequest.getLastName() != null && editDataEmployerDtoRequest.getLastName().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_LAST_NAME);
        }
        if (editDataEmployerDtoRequest.getEmail() != null && editDataEmployerDtoRequest.getEmail().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_EMAIL);
        }
        if (editDataEmployerDtoRequest.getPassword() != null && editDataEmployerDtoRequest.getPassword().equals("")) {
            throw new ServerException(ServerErrorCode.WRONG_PASSWORD);
        }
    }
}
