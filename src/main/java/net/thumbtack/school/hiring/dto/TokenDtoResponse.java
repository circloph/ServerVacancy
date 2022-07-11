package net.thumbtack.school.hiring.dto;

public class TokenDtoResponse {
    private String token;

    public TokenDtoResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
