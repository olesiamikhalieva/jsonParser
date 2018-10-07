package com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonGetter_JsonPropertyOrder;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;

/*
The @JsonGetter_JsonPropertyOrder annotation is an alternative to the
@JsonProperty annotation to mark the specified method as a getter method.
The @JsonPropertyOrder annotation is used to specify the order of properties on serialization.
 */
@AllArgsConstructor
@JsonPropertyOrder({ "name", "id" })
public class MyBean {
    public int id;
    private String name;

    @JsonGetter("name")
    public String getTheName() {
        return name;
    }
}
