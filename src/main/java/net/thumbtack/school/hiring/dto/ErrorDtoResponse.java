package net.thumbtack.school.hiring.dto;

import com.google.gson.JsonObject;

public class ErrorDtoResponse {

    public static String getJsonStringResponse(String message) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("error", message);
        return jsonObject.toString();
    }

}
