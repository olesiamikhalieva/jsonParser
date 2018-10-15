package com.json.parser.customjsonparser.parser;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Component
@Log4j2
public class MyGsonParser3 {


    private static Gson gson = new Gson();


    public static void main(String[] args) {
        String json = "{\n" +
                " \"token\": \"token_boken\",\n" +
                " \"request_id\": \"simple_request\",\n" +
                " \"data\": {\n" +
                "      \"settings\": {\n" +
                "     \"desktop_id\": \"abc1234hj\",\n" +
                "     \"process_id\": \"java_proc\",\n" +
                "     \"class_id\": \"425\"\n" +
                "   }\n" +
                " }\n" +
                "}";
        MyGsonParser3 myGsonParser3 = new MyGsonParser3();
        Map<String, String> map = myGsonParser3.parseJson(json);
        System.out.println(myGsonParser3.createJson(map));
    }

    public boolean isObjectIsEmpty(JsonElement result) {
        return result.toString().equals("{}");
    }

    public Map<String, String> parseJson(String jsonFromClient) {

        Map<String, String> requestDataKeyValueMap = new TreeMap<>();
        try {
            LinkedTreeMap keyValueMap = null;

            //парсим, ложим и наполняемм мапу requestDataKeyValueMap только поля родительского объекта
            keyValueMap = gson.fromJson(jsonFromClient, LinkedTreeMap.class);
            parseJsonByConventionOnlyPrimitiveType(requestDataKeyValueMap, gson, keyValueMap);
            //парсим,ложим и наполняем  мапу requestDataKeyValueMap все поля согласно конфенции пульсара для вложенных объектов и полей "data"
            keyValueMap = (LinkedTreeMap) gson.fromJson(jsonFromClient, LinkedTreeMap.class).get("data");
            parseJsonByConventionAndSaveKeyValueToMap(requestDataKeyValueMap, gson, keyValueMap);

        } catch (Exception e) {
            log.error("Error parse json, {}", e);
        }
        return requestDataKeyValueMap;
    }


    private void parseJsonByConventionAndSaveKeyValueToMap(Map<String, String> requestDataKeyValueMap, Gson gson, LinkedTreeMap keyValueMap) {
        keyValueMap.forEach((key, value) -> {
            // System.out.println(key + " --" + value);
            JsonElement result = new JsonParser().parse(gson.toJson(value));
            // если это объект и он  пустой
            if (result.isJsonObject() && isObjectIsEmpty(result)) {
                log.info("JSON NULL, because value is empty, key = {} - value = {},", key, value);
                value = null;
                log.info("set null to value, k = {}, value = {}", key, value);
            }
            // если это объект и он не пустой
            else if (result.isJsonObject() && !isObjectIsEmpty(result)) {
                convertObjectKeyValueByConventions(requestDataKeyValueMap, key, value);
            }
            // если это коллекция
            else if (result.isJsonArray() && !value.toString().equals("[]")) {
                // значение сохраняем как коллекцию в джейсон формате
                requestDataKeyValueMap.put(key.toString(), gson.toJson(value));
            }
            // если это простое поле
            else if (result.isJsonPrimitive()) {
                requestDataKeyValueMap.put(key.toString(), value.toString());

            }
        });
    }


    private void parseJsonByConventionOnlyPrimitiveType(Map<String, String> requestDataKeyValueMap, Gson gson, LinkedTreeMap keyValueMap) {
        keyValueMap.forEach((key, value) -> {
            JsonElement result = new JsonParser().parse(gson.toJson(value));
            // если это простое поле
            if (result.isJsonPrimitive()) {
                requestDataKeyValueMap.put(key.toString(), value.toString());
            }
        });
    }


    private void convertObjectKeyValueByConventions(Map<String, String> requestDataKeyValueMap, Object k, Object v) {
        String value = v.toString().replaceAll("[{}]", "");
        //берем значение и парсим  в массив, где получаем массив
        String[] valueMass = value.split(",");
        for (String mass : valueMass) {
            // после чего парсим по знаку "=" и ложим соответственно как ключ значение
            mass = setEmptyStringIfValueNotPresent(mass);
            String[] inMass = mass.split("=");
            String docKey = k.toString() + "." + inMass[0].trim();
            String docVal = inMass[1].trim();
            requestDataKeyValueMap.put(docKey, docVal);
        }
    }

    private String setEmptyStringIfValueNotPresent(String mass) {
        if (mass.endsWith("=")) {
            mass = mass + " ";
        }
        return mass;
    }

    public Object createJson(Map<String, String> map) {
        JsonObject parentObj = new JsonObject();
        Set<Map.Entry<String, String>> parentMap = map.entrySet();
        for (Map.Entry<String, String> value : parentMap) {
            String k = value.getKey();//setting.process_id
            JsonObject nestedObj = null;
            if (k.contains(".")) {
                nestedObj = new JsonObject();
                for (Map.Entry<String, String> entry : getNestedObjMap(map).entrySet()) {
                    nestedObj.addProperty(entry.getKey(), entry.getValue());
                }
            parentObj.add(getBeforeDotValue(map), nestedObj);
        } else {
            parentObj.addProperty(value.getKey(), value.getValue());
        }
    }
        return parentObj;
}

    private Map<String, String> getNestedObjMap(Map<String, String> map) {
        Map<String, String> nestedObj = new HashMap<>();
        Set<Map.Entry<String, String>> parentMap = map.entrySet();
        for (Map.Entry<String, String> value : parentMap) {
            String k = value.getKey();
            if (k.contains(".")) {
               // System.out.println(k);
                String[] valueMass = k.split("\\.");
                nestedObj.put(valueMass[1], value.getValue());
            }
        }
        return nestedObj;
    }


    private String getBeforeDotValue(Map<String, String> map) {
        String beforeDot = null;
        Set<Map.Entry<String, String>> parentMap = map.entrySet();
        for (Map.Entry<String, String> value : parentMap) {
            String k = value.getKey();
            if (k.contains(".")) {
                String[] valueMass = k.split("\\.");
                beforeDot = valueMass[0];
            }
        }
      //  System.out.println("beforeDot ------" + beforeDot);
        return beforeDot;
    }
}


