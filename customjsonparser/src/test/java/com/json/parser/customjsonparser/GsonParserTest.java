package com.json.parser.customjsonparser;

import com.json.parser.customjsonparser.parser.GsonParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GsonParserTest {

    GsonParser gsonParser;

    @Before
    public void setUp() {
        gsonParser = new GsonParser();
    }

    String stJson = "{    \n" +
            "\"magaz_id\": \"345bcc1123hjk\",\n" +
            "\"order_id\": \"180420TFD43FDSGJHJ5433\",\n" +
            "\"employer\": \"Dasha\",\n" +
            "     \"data\": {\n" +
            "         \"milk_portion\":{\"brand\":\"milka\"},\n" +
            "         \"client_name\":\"Петро\",\n" +
            "         \"client_surname\":\"Петров\",\n" +
            "         \"client_lastName\":\"Петрович\"\n" +
            "     }\n" +
            "}";

    @Test
    public void extractMilkFRomJson() {
        String brand = "milka";

        String milkBrand = gsonParser.extractMilkFRomJson(stJson);

        assertEquals(brand, milkBrand);
    }


}