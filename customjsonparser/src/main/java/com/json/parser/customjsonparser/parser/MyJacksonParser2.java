package com.json.parser.customjsonparser.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@Log4j2
public class MyJacksonParser2 {

    public void parseWithObjectMapper(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        String street = jsonNode.get("address").get("friend_addr").get("street").asText();
        System.out.println("street---------->"+street);
        System.out.println(jsonNode.get("address").get("friend_addr").findValue("codes"));
    }



    //        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(strJson);
//        List<JsonNode> nodeList = jsonNode.findParents("rcc");
//        for (JsonNode node : nodeList) {
//
//            System.out.println("+++");
//            System.out.println( node.findValues("ovk"));
//
//            System.out.println("+++");
//            for (int i = 0; i < node.findValues("ovk").size(); i++) {
//                System.out.println(node.findValues("ovk").get(0));
//            }
//        }
//        JsonNode parentElement = jsonNode.get("rcc");
//        JsonNode sofit = parentElement.get(0).get("sofit");
//        String place = sofit.get("Place").asText();
//        System.out.println("place is -> " + place);
//        System.out.println("-----");
//
//        Iterator<JsonNode> elementsNode = parentElement.elements();
//        while (elementsNode.hasNext()) {
//            JsonNode node = elementsNode.next();
//            System.out.println(node.get("comment"));
//
//            JsonNode nd = node.get("sofit");
//            System.out.println(nd.get("Sex"));
//
//        }
//
//        JsonNode node = objectMapper.readValue(strJson, JsonNode.class);
//
//        System.out.println(node.findValues("Sex").get(0));
//        System.out.println(node.findValues("Type").get(0));
//        System.out.println(node.findValues("fio_en").get(0));
//        System.out.println(node.findValues("token").get(0));


//            JsonElement jelement = new JsonParser().parse(strJson);
//            JsonObject jobject = jelement.getAsJsonObject();
//
//            JsonArray jarray = jobject.getAsJsonArray("rcc");
//            System.out.println(jarray);
//            for (JsonElement jsonElement : jarray) {
//                JsonObject json = jsonElement.getAsJsonObject();
//                System.out.println(json);
//                JsonObject sofit = json.get("sofit").getAsJsonObject();
//                String place = sofit.get("Place").getAsString();
//                System.out.println(place);
    //      }

    //          JsonParser parser = new JsonParser();




    /*
        todo Read Object From JSON String
        ObjectMapper objectMapper1 = new ObjectMapper();
        Car car1 = objectMapper1.readValue(strJson, Car.class);


        todo Read Object From JSON Reader
        ObjectMapper objectMapper2 = new ObjectMapper();
        Reader reader = new StringReader(strJson);
        Car car2 = objectMapper2.readValue(reader, Car.class);


        todo Read Object From JSON File
        ObjectMapper objectMapper3 = new ObjectMapper();
        File file = new File("data/car.json");
        Car car3 = objectMapper3.readValue(file, Car.class);

        todo Read Object From JSON via URL
        ObjectMapper objectMapper4 = new ObjectMapper();
        URL url = new URL("file:data/car.json");
        Car car4 = objectMapper4.readValue(url, Car.class);

        todo Read Object From JSON InputStream
        ObjectMapper objectMapper5 = new ObjectMapper();
        InputStream input = new FileInputStream("data/car.json");
        Car car5 = objectMapper5.readValue(input, Car.class);

        todo Read Object From JSON Byte Array
        ObjectMapper objectMapper6 = new ObjectMapper();
        byte[] bytes = strJson.getBytes("UTF-8");
        Car car6 = objectMapper6.readValue(bytes, Car.class);

        todo Read Object Array From JSON Array String
        String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
        ObjectMapper objectMapper7 = new ObjectMapper();
        Car[] cars7 = objectMapper7.readValue(jsonArray, Car[].class);

        todo Read Object List From JSON Array String
        String jsonArray2 = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
        ObjectMapper objectMapper8 = new ObjectMapper();
        List<Car> cars1 = objectMapper8.readValue(jsonArray2, new TypeReference<List<Car>>(){});

        todo Read Map from JSON String
        String jsonObject = "{\"brand\":\"ford\", \"doors\":5}";
        ObjectMapper objectMapper9 = new ObjectMapper();
        Map<String, Object> jsonMap = objectMapper9.readValue(jsonObject,
                new TypeReference<Map<String,Object>>(){});

        todo Ignore Unknown JSON Fields
        ObjectMapper objectMapper11 = new ObjectMapper();
        objectMapper11.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        todo Fail on Null JSON Values for Primitive Types
        ObjectMapper objectMapper10 = new ObjectMapper();
        objectMapper10.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

*/
}
