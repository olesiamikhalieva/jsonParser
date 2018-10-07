package com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyBean3 {
    public int id;
    public String name;
}
