package com.json.parser.customjsonparser.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Component
@Log4j2
public class MyJacksonParser3 {
    public static void main(String[] args) throws IOException {
        String json = "{\n" +
                " \"token\": \"token_boken\",\n" +
                " \"request_id\": \"simple_request\",\n" +
                " \"array\":[\"67\",\"5\"],\n" +
                " \"data\": {\n" +
                "      \"settings\": {\n" +
                "     \"desktop_id\": \"abc1234hj\",\n" +
                "     \"process_id\": \"java_proc\",\n" +
                "     \"class_id\": \"425\"\n" +
                "   }\n" +
                " }\n" +
                "}";
        MyJacksonParser3 myJacksonParser3 = new MyJacksonParser3();
        Map<String, String> map = myJacksonParser3.parseJson(json);
//        System.out.println(myGsonParser3.createJson(map));
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "===========>" + entry.getValue());
        }
    }


    public Map<String, String> parseJson(String jsonFromClient) {

        Map<String, String> requestDataKeyValueMap = new TreeMap<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = objectMapper.readValue(jsonFromClient, new TypeReference<Map<String, Object>>() {
            });
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                // System.out.println(entry.getKey() + "--->" + entry.getValue());
                if (entry.getValue().toString().contains("{")) {
                    String obj = entry.getValue().toString();
                    obj.trim();
                    obj = obj.substring(1);
                    String[] mass = obj.split("\\{");
                    String beforeDot = null;
                    String afterString = null;
                    for (int i = 0; i < mass.length; i++) {
                        beforeDot = mass[0];
                        afterString = mass[1];
                    }
                    beforeDot = beforeDot.replace("=", "");
                    // System.out.println("before===="+ beforeDot);
                    afterString = afterString.replace("}}", "");
                    String[] afterMass = afterString.split(",");
                    //  Map<String,String> mapV = new HashMap<>();
                    for (String s : afterMass) {
                        s.trim();
                        String[] massS = s.split("=");
                        for (int i = 0; i < massS.length; i++) {
                            requestDataKeyValueMap.put(beforeDot + "." + massS[0].trim(), massS[1].trim());
                        }
                    }
                } else if (entry.getValue().toString().contains("[")) {
//                    String value = entry.getValue().toString();
//                    value = value.replaceAll("\\[" ,"");
//                    value = value.replaceAll("\\]" ,"");
//                    String[]mass = value.split(",");

//                    for (int i= 0; i<mass.length; i++) {
//                        mass[i] = "\""+mass[i].trim()+"\"";
//                    }
                   // requestDataKeyValueMap.put(entry.getKey(), Arrays.toString(mass));
                    requestDataKeyValueMap.put(entry.getKey(), entry.getValue().toString());
                } else {
                    // System.out.println("simple obj");
                    requestDataKeyValueMap.put(entry.getKey(), entry.getValue().toString());
                }
            }
        } catch (Exception e) {
            log.error("Error parse json, {}", e);
        }
        return requestDataKeyValueMap;
    }

}
