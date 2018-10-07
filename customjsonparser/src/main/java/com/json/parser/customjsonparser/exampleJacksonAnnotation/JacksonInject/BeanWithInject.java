package com.json.parser.customjsonparser.exampleJacksonAnnotation.JacksonInject;

import com.fasterxml.jackson.annotation.JacksonInject;

/*
@JacksonInject is used to indicate a property that will get
its value from the injection and not from the JSON data.
 */
public class BeanWithInject {
    @JacksonInject
    public int id;

    public String name;
}
