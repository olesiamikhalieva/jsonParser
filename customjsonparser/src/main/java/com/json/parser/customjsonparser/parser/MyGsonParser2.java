package com.json.parser.customjsonparser.parser;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Log4j2
public class MyGsonParser2 {

    @Autowired
    private Gson gson;

    public void parseJsonWithJsonParser(String inputJson) {
        JsonParser jsonParser = new JsonParser();
        JsonObject json = jsonParser.parse(inputJson).getAsJsonObject();
        JsonObject address = json.get("address").getAsJsonObject();
        JsonObject friend_addr = address.get("friend_addr").getAsJsonObject();
        String street = friend_addr.get("street").toString();
        System.out.println(street);
    }

    public void parseJsonWithMap(String inputJson) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(inputJson).getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> parentMap = jsonObject.entrySet();
        for (Map.Entry<String, JsonElement> value : parentMap) {
            // System.out.println(value.getKey() + " -->" + value.getValue());
            if (value.getKey().equals("address")) {
                JsonObject addressObj = value.getValue().getAsJsonObject();
                Set<Map.Entry<String, JsonElement>> addressMap = addressObj.entrySet();
                for (Map.Entry<String, JsonElement> value2 : addressMap) {
                    if (value2.getKey().equals("friend_addr")) {
                        JsonObject friend_addrObj = value2.getValue().getAsJsonObject();
                        Set<Map.Entry<String, JsonElement>> friend_addrMap = friend_addrObj.entrySet();
                        for (Map.Entry<String, JsonElement> value3 : friend_addrMap) {
                            if (value3.getKey().equals("street")) {
                                System.out.println("street:" + value3.getKey() + "---->" + value3.getValue());
                            }
                        }
                    }
                }
            }
        }
    }


    public Object createJson() {

        List<Integer> code = new ArrayList<>(Arrays.asList(12345, 2345));
        List<Integer> phoneNumbers = new ArrayList<>(Arrays.asList(123456, 987654));
        List<String> cities = new ArrayList<>(Arrays.asList("Los Angeles", "New York"));

        //  2. Генерируем джейсон

        JsonObject parentObj = new JsonObject();

        JsonObject addressObj = new JsonObject();

        JsonObject friend_addrObj = new JsonObject();

        JsonObject propertiesObj = new JsonObject();

        JsonElement codeElement = gson.toJsonTree(code, new TypeToken<List<String>>() {
        }.getType());

        JsonElement phoneNumberElement = gson.toJsonTree(phoneNumbers, new TypeToken<List<String>>() {
        }.getType());

        JsonElement citiesElement = gson.toJsonTree(cities, new TypeToken<List<String>>() {
        }.getType());

        parentObj.addProperty("empID", 100);
        parentObj.addProperty("name", "David");
        parentObj.addProperty("permanent", false);

        friend_addrObj.add("code", codeElement);
        friend_addrObj.addProperty("street","one");

        addressObj.add("friend_addr", friend_addrObj);
        addressObj.addProperty("street", "BTM 1st Stage");
        addressObj.addProperty("city", "Bangalore");
        addressObj.addProperty("zipcode",560100);
        parentObj.add("address",addressObj);

        parentObj.add("phoneNumber",phoneNumberElement);

        parentObj.addProperty("role", "Manager");

        parentObj.add("cities", citiesElement);

        propertiesObj.addProperty( "age", "28 years");
        propertiesObj.addProperty( "salary", "1000 Rs" );
        parentObj.add("properties", propertiesObj);

        return parentObj;
    }
}

