package net.thumbtack.school.hiring.dto;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Primitives;
import net.thumbtack.school.hiring.exception.ServerErrorCode;
import net.thumbtack.school.hiring.exception.ServerException;


public class JsonConverter {

    public static  <T> T fromJson(String json, Class<T> classOfT) throws ServerException {
        try {
            Gson gson = new Gson();
            Object object = gson.fromJson((String)json, (Class<T>) classOfT);
            return Primitives.wrap(classOfT).cast(object);
        } catch (JsonSyntaxException e) {
            throw new ServerException(ServerErrorCode.WRONG_JSON);
        }
    }
}
