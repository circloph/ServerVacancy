// REVU вынесите в отдельный пакет этот enum и исключение
// это не сервисы
package net.thumbtack.school.hiring.exception;

public enum ServerErrorCode {
    WRONG_NAME_COMPANY("Wrong name company"),
    WRONG_ADDRESS_COMPANY("Wrong address company"),
    WRONG_FIRST_NAME("Wrong first name"),
    WRONG_PASSWORD("Wrong password"),
    WRONG_MIDDLE_NAME("Wrong middle name"),
    WRONG_LAST_NAME("Wrong last name"),
    WRONG_EMAIL("Wrong email"),
    WRONG_LOGIN ("Wrong login"),
    PERSON_EXIST("Such a person exists"),
    PERSON_NOT_EXIST("There is no such person"),
    WRONG_LENGHT_LOGIN("Invalid username length"),
    WRONG_LENGHT_PASSWORD("Invalid password length"),
    PERSON_NOT_AUTHORIZED("Person not authorized"),
    WRONG_NAME_POSITION("Wrong name position"),
    WRONG_SALARY("Wrong salary"),
    WRONG_DESCRIPTION_SKILL("Wrong description skill"),
    WRONG_PROFICIENCY_LEVEL("Wrong proficiency skill"),
    WRONG_RESPONSE("Wrong response"),
    WRONG_JSON("Wrong json");

    private String message;

    ServerErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
