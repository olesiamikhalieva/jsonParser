package com.json.parser.customjsonparser;

import com.json.parser.customjsonparser.parser.MyGsonParser3;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GsonParserTest3 {

    @Autowired
    private MyGsonParser3 myGsonParser;


    private static String json  = "{\"token\": \"token_boken\",\"request_id\": \"simle_request\",\"data\": {\"settings\": {desktop_id\": \"abc1234hj\",\"process_id\": \"java_proc\",\"class_id\": \"425\"}}}";

    @Test
    public void testParser(){
       Map<String, String> map1 = myGsonParser.parseJson(json);
       Map<String, String> map2 = new HashMap<>();
       map2.put("token", "token_boken");
       map2.put("request_id", "simple_request");
       map2.put("setting.desktop_id","abc1234hj");
       map2.put("setting.process_id", "java_proc");
       map2.put("setting.class_id","425");
        myGsonParser.parseJson(json).forEach((k, v) -> System.out.println(k + " -- " + v));
       assertEquals(map1, map2);
    }

}
