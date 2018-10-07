package com.json.parser.customjsonparser.controller;


import com.json.parser.customjsonparser.parser.GsonParser;
import com.json.parser.customjsonparser.dto.CatDto;
import com.json.parser.customjsonparser.dto.TestDto;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/gson")
@Log4j2
public class GsonController {

    @Autowired
    private GsonParser gsonParser;

    @PostMapping("/")
    public Object gsontTest(HttpServletRequest request) {
        String inputJson = null;
        inputJson = getJsonFromRequest(request);

        gsonParser.parseJson(inputJson);

        return " ok";
    }

    @PostMapping("/expose")
    public Object gsontTest(@RequestBody TestDto testDto) {
        System.out.println(testDto);
        return testDto;
    }

    @GetMapping("/get")
    public Object gsontTestGet() {
        return gsonParser.createJson();
    }

    @GetMapping("/cat")
    public String gsonGetCAt() {
        return gsonParser.simpyParseSerialize();
    }

    @PostMapping("/cat")
    public void gsonSendCAt(@RequestBody CatDto catDto) {
        gsonParser.simpyParseDeeerialize(catDto);
    }

    private String getJsonFromRequest(HttpServletRequest request) {
        String inputJson = null;
        try {
            inputJson = IOUtils.toString(request.getInputStream());
        } catch (IOException e) {
            log.error("Can't get request, {}", e.getMessage());
        }
        return inputJson;
    }
}
