package net.thumbtack.school.hiring.dto;

public class EditVacancyStatus {
    private String namePosition;
    private String token;

    public EditVacancyStatus(String namePosition, String token) {
        this.namePosition = namePosition;
        this.token = token;
    }

    public String getNamePosition() {
        return namePosition;
    }

    public String getToken() {
        return token;
    }
}
