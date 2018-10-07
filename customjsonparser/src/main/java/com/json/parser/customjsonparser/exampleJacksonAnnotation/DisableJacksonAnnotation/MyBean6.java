package com.json.parser.customjsonparser.exampleJacksonAnnotation.DisableJacksonAnnotation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "id" })
@AllArgsConstructor
@NoArgsConstructor

/*
The result of serialization before disabling annotations:
{"id":1}
The result of serialization after disabling annotations:
{
    "id":1,
    "name":null
}
 */
public class MyBean6 {
    public int id;
    public String name;
}
