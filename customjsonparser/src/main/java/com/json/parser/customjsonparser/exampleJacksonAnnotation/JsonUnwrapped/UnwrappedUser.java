package com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonUnwrapped;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


/*
{
    "id":1,
    "firstName":"John",
    "lastName":"Doe"
} - result
 */

@AllArgsConstructor
@NoArgsConstructor
public class UnwrappedUser {
    public int id;

    @JsonUnwrapped
    public Name name;
@NoArgsConstructor
@AllArgsConstructor
    public static class Name {
        public String firstName;
        public String lastName;
    }
}
