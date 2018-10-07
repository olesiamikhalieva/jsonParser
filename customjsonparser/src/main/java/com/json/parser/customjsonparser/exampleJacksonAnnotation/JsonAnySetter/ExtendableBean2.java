package com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonAnySetter;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

/*
@JsonAnySetter allows you the flexibility of using a Map as standard properties.
On de-serialization, the properties from JSON will simply be added to the map.
{
    "name":"My bean",
    "attr2":"val2",
    "attr1":"val1"
}
 */
public class ExtendableBean2 {
    public String name;
    private Map<String, String> properties = new HashMap<>();

    @JsonAnySetter
    public void add(String key, String value) {
        properties.put(key, value);
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
