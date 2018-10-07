package com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonValue;
/*
@JsonValue indicates a single method that should be used to serialize the entire instance.
 */

public enum TypeEnumWithValue {
    TYPE1(1, "Type A"), TYPE2(2, "Type 2");

    private Integer id;
    private String name;

    // standard constructors

    @JsonValue
    public String getName() {
        return name;
    }

    TypeEnumWithValue(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


}
