package com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonFilter;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@JsonFilter("myFilter")
@NoArgsConstructor
@AllArgsConstructor
public class BeanWithFilter {
    public int id;
    public String name;
}
