package com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonAnyGetter;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

/*
The @JsonAnyGetter annotation allows the flexibility of using a Map field as standard properties.
 */
public class ExtendableBean {

    public String name;
    private Map<String, String> properties= new HashMap<>();;

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    public ExtendableBean(String name) {
        this.name = name;
    }


    public void add(String attr1, String val1) {
        properties.put(attr1,val1);
    }
}
