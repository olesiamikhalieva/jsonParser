package com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonCreater;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
The @JsonCreator annotation is used to tune the constructor/factory used in deserialization.
It’s very helpful when we need to deserialize some JSON
that doesn’t exactly match the target entity we need to get.
 */
public class BeanWithCreator {
    public int id;
    public String name;

    @JsonCreator
    public BeanWithCreator(
            @JsonProperty("id") int id,
            @JsonProperty("theName") String name) {
        this.id = id;
        this.name = name;
    }
}
