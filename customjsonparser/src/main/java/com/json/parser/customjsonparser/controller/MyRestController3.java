package com.json.parser.customjsonparser.controller;

import com.json.parser.customjsonparser.parser.MyGsonParser3;
import com.json.parser.customjsonparser.parser.MyJacksonParser3;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Log4j2
@RestController
@RequestMapping("/my/3")
public class MyRestController3 {


    @Autowired
    private MyGsonParser3 myGsonParser;

    @Autowired
    private MyJacksonParser3 myJacksonParser;

    private static Map<String, String> myMap;

    @PostMapping("/gson/parse")
    public Map<String, String> parseGsonJson(HttpServletRequest request) {
        String strJson = getStringJsonFromRequest(request);
        myMap = myGsonParser.parseJson(strJson);
        return myMap;
    }

    @GetMapping("/gson/create")
    public Object getJson() {
        log.info(myGsonParser.createJson(myMap));
        return myGsonParser.createJson(myMap);
    }

    private String getStringJsonFromRequest(HttpServletRequest request) {
        String strJson = null;
        try {
            strJson = IOUtils.toString(request.getInputStream());
        } catch (IOException e) {
            log.error("Can't get request, {}", e.getMessage());
        }
        log.info(strJson);
        System.out.println(strJson);
        return strJson;
    }
}
