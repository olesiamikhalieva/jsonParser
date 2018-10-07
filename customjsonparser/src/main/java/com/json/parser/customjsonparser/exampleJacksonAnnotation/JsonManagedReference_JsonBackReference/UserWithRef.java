package com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonManagedReference_JsonBackReference;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRef {
    public int id;
    public String name;

    public UserWithRef(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonBackReference
    public List<ItemWithRef> userItems=new ArrayList<>();

    public void addItem(ItemWithRef item) {
        userItems.add(item);
    }
}
