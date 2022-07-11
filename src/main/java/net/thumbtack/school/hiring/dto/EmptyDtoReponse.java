package net.thumbtack.school.hiring.dto;

import com.google.gson.Gson;

public class EmptyDtoReponse {

    public static String getJsonStringResponse() {
        Gson gson = new Gson();
        String jsonString = "";
        return gson.toJson(jsonString);
    }
}
