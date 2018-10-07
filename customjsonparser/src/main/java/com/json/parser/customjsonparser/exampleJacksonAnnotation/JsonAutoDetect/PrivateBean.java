package com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonAutoDetect;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;

/*
@JsonAutoDetect is used to override the default semantics of which properties are visible and which are not.
 */

@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PrivateBean {
    private int id;
    private String name;
}
