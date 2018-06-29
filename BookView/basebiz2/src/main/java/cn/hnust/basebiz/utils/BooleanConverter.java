package cn.hnust.basebiz.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by tjouyang on 2018/4/15.
 *
 * @author tjouyang
 */
public class BooleanConverter implements JsonSerializer<Boolean>, JsonDeserializer<Boolean>{

    @Override
    public Boolean deserialize(JsonElement json, Type typeOfT,
                               JsonDeserializationContext context)
            throws JsonParseException {
        return json.getAsJsonPrimitive().getAsBoolean();
    }

    @Override
    public JsonElement serialize(Boolean src, Type typeOfSrc, JsonSerializationContext context) {
        if (src == null) {
            return new JsonPrimitive(false);
        } else {
            return new JsonPrimitive(src);
        }
    }
}
