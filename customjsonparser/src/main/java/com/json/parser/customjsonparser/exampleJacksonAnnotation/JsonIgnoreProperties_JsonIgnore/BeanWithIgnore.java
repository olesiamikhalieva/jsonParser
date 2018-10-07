package com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonIgnoreProperties_JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;

@AllArgsConstructor
//@JsonIgnoreProperties({ "id" })
public class BeanWithIgnore {
    @JsonIgnore
    public int id;
    public String name;
}
