package com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/*
{
    "id": 2,
    "itemName": "book",
    "owner": {
        "id": 1,
        "name": "John",
        "userItems": [
            2
        ]
    }
}
 */

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@NoArgsConstructor
@AllArgsConstructor
public class ItemWithIdentity {
    public int id;
    public String itemName;

    public UserWithIdentity owner;

    public ItemWithIdentity(int id, String itemName) {
        this.id = id;
        this.itemName = itemName;
    }
}