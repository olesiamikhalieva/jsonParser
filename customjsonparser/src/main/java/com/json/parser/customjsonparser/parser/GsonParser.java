package com.json.parser.customjsonparser.parser;

import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.json.parser.customjsonparser.dto.CatDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Log4j2
public class GsonParser {

    @Autowired
    private Gson gson;

    public void parseJsonWithMapIteration(String inputJson) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(inputJson).getAsJsonObject();
        JsonArray jsonArray = jsonObject.get("rcc").getAsJsonArray();
        if (!isObjectIsEmpty(jsonObject)) {
            Iterator<JsonElement> jsonArrayIterator = jsonArray.iterator();
            while (jsonArrayIterator.hasNext()) {
                JsonObject portionJsonObject = jsonArrayIterator.next().getAsJsonObject();
                System.out.println(portionJsonObject);
                Set<Map.Entry<String, JsonElement>> entries = portionJsonObject.entrySet();
                for (Map.Entry<String, JsonElement> value : entries) {
                    System.out.println(value.getKey() + " -->" + value.getValue());
                    if (value.getKey().equals("sofit")) {
                        JsonObject sofit = value.getValue().getAsJsonObject();
                        Set<Map.Entry<String, JsonElement>> entries2 = sofit.entrySet();
                        System.out.println("SOFIT ARRAY:");
                        for (Map.Entry<String, JsonElement> value2 : entries2) {
                            System.out.println("array params ->" + value2.getKey() + " -->" + value2.getValue());

                        }
                    }
                    System.out.println();
                    if (value.getKey().equals("status")) {
                        JsonObject status = value.getValue().getAsJsonObject();
                        Set<Map.Entry<String, JsonElement>> entries2 = status.entrySet();
                        System.out.println(" STATUS OBJECT :");
                        for (Map.Entry<String, JsonElement> value2 : entries2) {
                            System.out.println("object params --> " + value2.getKey() + " -->" + value2.getValue());

                        }
                    }
                    System.out.println();
                }

            }
        }
    }

    public void parseJsonWithLinkedTreeMap(String inputJson) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(inputJson).getAsJsonObject();
        JsonArray paymentsArray = jsonObject.getAsJsonArray("rcc");
        for (JsonElement element : paymentsArray) {
            JsonObject paymentObj = element.getAsJsonObject();
            System.out.println(paymentObj);
            String comment = paymentObj.get("comment").getAsString();
            System.out.println(comment);
            String typeOperation = paymentObj.get("type_operation").getAsString();
            System.out.println(typeOperation);
            JsonObject status = paymentObj.get("status").getAsJsonObject();
            System.out.println(status);
            System.out.println(status.get("position"));
        }

    }

    public void parseJson(String inputJson) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(inputJson).getAsJsonObject();
        JsonArray paymentsArray = jsonObject.getAsJsonArray("rcc");
        JsonElement elementFirs = paymentsArray.get(0);
        LinkedTreeMap keyValueMap = gson.fromJson(elementFirs, LinkedTreeMap.class);
        keyValueMap.forEach((k, v) -> {
            JsonElement element = new JsonParser().parse(gson.toJson(v));
            if (element.isJsonObject() && isObjectIsEmpty(element)) {
                log.info("JSON NULL, because value is empty");
            }
            // если это объект и он не пустой
            else if (element.isJsonObject() && !isObjectIsEmpty(element)) {
                log.info("OBJECT -> {}", element);
                log.info(" oblect params: {} - {}", k, v);
            }
            // если это коллекция
            else if (element.isJsonArray()) {
                log.info("JSON ARRAY -> {}", element);
            }
            // если это простое поле
            else if (element.isJsonPrimitive()) {
                log.info("PRIMITIVE -> {} - {}", k, v);
            }
        });

    }

    public Object createJson() {
        //1.Структуры данных
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("Place", "Соборным МВД УВД");
        paramMap.put("date", "2009-08-02");
        paramMap.put("fio_en", "dianorev");

        List<String> stringList = new ArrayList<>(Arrays.asList("id", "tittle"));

        Map<String, String> objectMap = new HashMap<>();
        objectMap.put("position", "first");


        //  2. Генерируем джейсон
        JsonObject parentObject = new JsonObject();
        JsonArray jsonArrayRcc = new JsonArray();
        JsonObject dataObject = new JsonObject();
        JsonObject jsonObject = new JsonObject();
        JsonObject statusObject = new JsonObject();

        JsonElement stringListJsonArray = gson.toJsonTree(stringList, new TypeToken<List<String>>() {
        }.getType());


        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            jsonObject.addProperty(entry.getKey(), entry.getValue());
        }

        String JSONObject = gson.toJson(objectMap);
        // statusObject.addProperty("status", JSONObject);
        dataObject.addProperty("status", JSONObject);
        dataObject.add("sofit", jsonObject);
        dataObject.add("ovk", stringListJsonArray);
        dataObject.addProperty("type_operation", "human_add");
        dataObject.addProperty("comment", "add human person from space");

        jsonArrayRcc.add(dataObject);

        parentObject.add("", jsonArrayRcc);

        return parentObject;
    }

    public String getStrJson() {
        return strJson;
    }

    public String strJson = "{    \n" +
            "\"magaz_id\": \"345bcc1123hjk\",\n" +
            "\"order_id\": \"180420TFD43FDSGJHJ5433\",\n" +
            "\"employer\": \"Dasha\",\n" +
            "     \"data\": {\n" +
            "         \"milk_portion\":{\"brand\":\"milka\"},\n" +
            "         \"client_name\":\"Петро\",\n" +
            "         \"client_surname\":\"Петров\",\n" +
            "         \"client_lastName\":\"Петрович\"\n" +
            "     }\n" +
            "}";

    public String extractMilkFRomJson(String jsonStr) {
        String milkBrend = null;

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(strJson).getAsJsonObject();
        JsonObject dataListJsonObject = jsonObject.get("data").getAsJsonObject();
        if (!isObjectIsEmpty(dataListJsonObject)) {
            JsonElement element = dataListJsonObject.get("milk_portion");
            JsonObject portionJsonObject = element.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entries = portionJsonObject.entrySet();
            for (Map.Entry<String, JsonElement> value : entries) {
                return value.getValue().getAsString();
            }
        }
        return milkBrend;
    }

    public String simpyParseSerialize() {
        CatDto catDto = CatDto.builder().name("mursik").age(2).build();
        return gson.toJson(catDto);
    }

    public void simpyParseDeeerialize(CatDto strJson) {
        CatDto catDto = strJson;
        String jsonText = "{\"name\":\"Мурзик\",\"age\":9}";
        CatDto catDto1 = gson.fromJson(jsonText,catDto.getClass());
        System.out.println(catDto);
        System.out.println(catDto1);
    }

    private boolean isObjectIsEmpty(JsonElement result) {
        return result.toString().equals("{}");
    }
}
