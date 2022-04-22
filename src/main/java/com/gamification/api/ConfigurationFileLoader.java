package com.gamification.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.util.Objects;

public class ConfigurationFileLoader {

    public static void loadConfiguration(String path) throws Exception {

        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));

        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
        jsonObject.get("configurations").getAsJsonArray().forEach(configuration -> {
            try {
                parseConfiguration(configuration.getAsJsonObject());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
    }

    private static void parseConfiguration(JsonObject jsonObject) throws Exception {
        String gameRule = jsonObject.get("gameRule").getAsString();
        JsonArray params = jsonObject.get("params").getAsJsonArray();
        String task = jsonObject.get("task").getAsString();

        Class<?>[] parameterClasses = new Class<?>[params.size()];
        Object[] parameterValues = new Object[params.size()];
        for (int i = 0; i < params.size(); i++) {
            JsonObject parameterObject = params.get(i).getAsJsonObject();
            parameterClasses[i] = parseType(parameterObject.get("class").getAsString());
            parameterValues[i] = new Gson().fromJson(parameterObject.get("value"), parameterClasses[i]);
        }

        GameRule gr = (GameRule) Class.forName(gameRule).getDeclaredConstructor(parameterClasses).newInstance(parameterValues);

        Class<? extends Task> t = (Class<? extends Task>) Class.forName(task);

        GamificationFacade.getInstance().setGameRule(gr, t);
    }


    private static Class<?> parseType(final String className) throws ClassNotFoundException {
        return switch (className) {
            case "boolean" -> boolean.class;
            case "byte" -> byte.class;
            case "short" -> short.class;
            case "int" -> int.class;
            case "long" -> long.class;
            case "float" -> float.class;
            case "double" -> double.class;
            case "char" -> char.class;
            case "void" -> void.class;
            default -> Class.forName(className);
        };
    }

}
