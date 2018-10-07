package com.json.parser.customjsonparser.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestDto {


    @Expose
    @SerializedName("rcc")
    private List<Rcc> rcc;

    public static class Sofit {
        @SerializedName("Status")
        private String Status;
        @Expose
        @SerializedName("ru_Sname")
        private String ru_Sname;
        @Expose
        @SerializedName("ru_Lname")
        private String ru_Lname;
        @Expose
        @SerializedName("ru_Fname")
        private String ru_Fname;
        @Expose
        @SerializedName("ua_Sname")
        private String ua_Sname;
        @Expose
        @SerializedName("ua_Lname")
        private String ua_Lname;
        @Expose
        @SerializedName("ua_Fname")
        private String ua_Fname;
        @Expose
        @SerializedName("fio_en")
        private String fio_en;
        @Expose
        @SerializedName("birthday")
        private String birthday;
        @Expose
        @SerializedName("Sex")
        private String Sex;
        @Expose
        @SerializedName("Lang")
        private String Lang;
        @Expose
        @SerializedName("Country2")
        private String Country2;
        @Expose
        @SerializedName("Ser")
        private String Ser;
        @Expose
        @SerializedName("Num")
        private String Num;
        @Expose
        @SerializedName("Type")
        private String Type;
        @Expose
        @SerializedName("date")
        private String date;
        @Expose
        @SerializedName("Country")
        private String Country;
        @Expose
        @SerializedName("Place")
        private String Place;
    }

    public static class Status {
        @Expose
        @SerializedName("position")
        private String position;
    }

    public static class Rcc {
        @Expose(serialize = false, deserialize = false)
        @SerializedName("comment")
        private String comment;
        @Expose(serialize = false, deserialize = false)
        @SerializedName("type_operation")
        private String type_operation;
        @Expose(serialize = false, deserialize = false)
        @SerializedName("token")
        private String token;
        @Expose
        @SerializedName("status")
        private Status status;
        @Expose
        @SerializedName("ovk")
        private List<String> ovk;
        @Expose(serialize = false, deserialize = false)
        @SerializedName("sofit")
        private Sofit sofit;
    }
}
