package net.banking.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.banking.models.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ObjectToJson {

    final static private ObjectMapper instance = new ObjectMapper();

    private ObjectToJson(){

    }

    public static String getJsonObject(Object obj) throws JsonProcessingException {
        return instance.writeValueAsString(obj);
    }
}
