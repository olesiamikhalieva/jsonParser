package com.json.parser.customjsonparser.parser;

public class Test {
    public static void main(String[] args) {
        String s = "setting.ggg_id";
        String[] subStr = s.split(".");
        for (int i = 0; i < subStr.length; i++) {
            System.out.println("Array: " + subStr[i]);
        }
    }
}
