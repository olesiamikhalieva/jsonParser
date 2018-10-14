package com.json.parser.customjsonparser.controller;

import com.json.parser.customjsonparser.parser.MyGsonParser2;
import com.json.parser.customjsonparser.parser.MyJacksonParser2;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Log4j2
@RestController
@RequestMapping("/my/2")
public class MyRestController2 {

    @Autowired
    private MyGsonParser2 myGsonParser2;
    @Autowired
    private MyJacksonParser2 myJacksonParser2;


    @PostMapping("/jackson/parse")
    public String parseJacksonJson(HttpServletRequest request) throws IOException {
        String strJson = getStringJsonFromRequest(request);
        myJacksonParser2.parseWithObjectMapper(strJson);
        return "ok";
    }

    @GetMapping("/gson/createJson")
    public Object getJson() {
        return myGsonParser2.createJson();
    }

    @PostMapping("/gson/parse/map")
    public String parse1(HttpServletRequest request) {
        String strJson = getStringJsonFromRequest(request);
        myGsonParser2.parseJsonWithMap(strJson);
        return "ok";
    }

    @PostMapping("/gson/parse/jsonParser")
    public String parse2(HttpServletRequest request) {
        String strJson = getStringJsonFromRequest(request);
        myGsonParser2.parseJsonWithJsonParser(strJson);
        return "ok";
    }

    private String getStringJsonFromRequest(HttpServletRequest request) {
        String strJson = null;
        try {
            strJson = IOUtils.toString(request.getInputStream());
        } catch (IOException e) {
            log.error("Can't get request, {}", e.getMessage());
        }
        log.info(strJson);
        return strJson;
    }


}
