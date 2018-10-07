package com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonSerialize;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonSerialize.CustomDateSerializer;
import lombok.AllArgsConstructor;

import java.util.Date;

/*
@JsonSerialize is used to indicate a custom serializer will be used to marshall the entity.

Let’s look at a quick example – we’re going to use @JsonSerialize to serialize
the eventDate property with a CustomDateSerializer.
 */

@AllArgsConstructor
public class Event {
    public String name;

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date eventDate;
}
