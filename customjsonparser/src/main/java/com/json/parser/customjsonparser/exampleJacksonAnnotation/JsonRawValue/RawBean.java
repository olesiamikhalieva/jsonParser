package com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonRawValue;

import com.fasterxml.jackson.annotation.JsonRawValue;

/*
@JsonRawValue is used to instruct the Jackson to serialize a property exactly as is.
{
    "name":"My bean",
    "json":{
        "attr":false
    }
}
 */
public class RawBean {
    public String name;

    @JsonRawValue
    public String json;

    public RawBean(String name, String json) {
        this.name = name;
        this.json = json;
    }
}