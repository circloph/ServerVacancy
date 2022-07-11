package net.thumbtack.school.hiring.users;

public enum Require {
    YES("Yes"),
    NO("No");

    private String message;

    Require(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
