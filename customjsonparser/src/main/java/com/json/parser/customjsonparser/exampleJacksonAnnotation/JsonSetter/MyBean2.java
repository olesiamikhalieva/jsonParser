package com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonSetter;

import com.fasterxml.jackson.annotation.JsonSetter;



public class MyBean2 {
    public int id;
    private String name;

    @JsonSetter("name")
    public void setTheName(String name) {
        this.name = name;
    }

    public String getTheName() {
        return name;
    }
}
