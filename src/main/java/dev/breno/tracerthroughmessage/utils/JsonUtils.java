package dev.breno.tracerthroughmessage.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils {
    private static final JsonMapper jsonMapper = JsonMapper.builder()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .build();

    private JsonUtils() {}

    public static String toJson(Object object) throws JsonProcessingException {
        jsonMapper.registerModule(new JavaTimeModule());
        return jsonMapper.writeValueAsString(object);
    }

    public static <T> T fromJson(String json, Class<T> type) throws JsonProcessingException {
        jsonMapper.registerModule(new JavaTimeModule());
        return jsonMapper.readValue(json, type);
    }
}
