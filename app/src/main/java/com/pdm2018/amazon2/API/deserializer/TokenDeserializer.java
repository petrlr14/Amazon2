package com.pdm2018.amazon2.API.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.pdm2018.amazon2.models.Login;

import java.lang.reflect.Type;

public class TokenDeserializer implements JsonDeserializer<Login>{
    @Override
    public Login deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Login aux = new Login();
        if (json.getAsJsonObject() != null) {
            JsonObject tokenJsonObject = json.getAsJsonObject();
            if (tokenJsonObject.get("token") != null) {
                aux.setToken(tokenJsonObject.get("token").toString());
            }
        }
        return aux;
    }
}
