package com.json.parser.customjsonparser.exampleJacksonAnnotation.CustomJacksonAnnotation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@CustomAnnotation

/*
{
    "name":"My bean",
    "id":1
}
 */
public class BeanWithCustomAnnotation {
    public int id;
    public String name;
    public Date dateCreated;
}
