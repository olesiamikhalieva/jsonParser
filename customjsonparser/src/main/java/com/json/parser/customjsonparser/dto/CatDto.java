package com.json.parser.customjsonparser.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class CatDto {

    @SerializedName("name")
    private String name;
    @SerializedName("age")
    private int age;
}
