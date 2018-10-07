package com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonRootName_JsonRootInfo;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

//The @JsonRootName annotation is used – if wrapping is enabled – to specify the name of the root wrapper to be used.
/*
{
    "User": {
        "id": 1,
        "name": "John"
    }
}
 */

@JsonRootName(value = "user")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class UserWithRoot {
    public int id;
    public String name;

    public UserWithRoot(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
