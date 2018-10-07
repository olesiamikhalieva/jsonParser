package com.json.parser.customjsonparser.exampleJacksonAnnotation.JacksonMixInAnnotations;

import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonIgnoreType.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Item5 {

    public int id;
    public String itemName;
    public User owner;

}
