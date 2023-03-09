package com.manitaggarwal.exercise.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;

public class JsonUtils {

    private static final Random random = new SecureRandom();

    public static String getRandomNumber() {
        return String.valueOf(Instant.now().toEpochMilli() + random.nextInt(Integer.MAX_VALUE));
    }

    public static String toJsonString(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            mapper.setVisibility(PropertyAccessor.FIELD,
                    JsonAutoDetect.Visibility.ANY);
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("JsonProcessingException");
        }
    }

    public static Object getObjectFromJson(String jsonString,
                                           Class objectClassType) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            mapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.readValue(jsonString, objectClassType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("JsonProcessingException");
        }
    }

}
