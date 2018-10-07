package com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonManagedReference_JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ItemWithRef {
    public int id;
    public String itemName;


    public ItemWithRef(int id, String itemName) {
        this.id = id;
        this.itemName = itemName;
    }

    @JsonManagedReference
    public UserWithRef owner;
}
