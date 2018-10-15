package com.json.parser.customjsonparser;

import com.json.parser.customjsonparser.parser.MyJacksonParser3;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;


public class JacksonParserTest3 {


    private static MyJacksonParser3 myJacksonParser3 = new MyJacksonParser3();

    private static String json = "{\n" +
            " \"token\": \"token_boken\",\n" +
            " \"request_id\": \"simple_request\",\n" +
            " \"data\": {\n" +
            "      \"settings\": {\n" +
            "     \"desktop_id\": \"abc1234hj\",\n" +
            "     \"process_id\": \"java_proc\",\n" +
            "     \"class_id\": \"425\"\n" +
            "   }\n" +
            "\n" +
            " }\n" +
            "}";


    Map<String, String> createExpectedMap() {
        Map<String, String> map2 = new HashMap<>();
        map2.put("token", "token_boken");
        map2.put("request_id", "simple_request");
        map2.put("setting.desktop_id", "abc1234hj");
        map2.put("setting.process_id", "java_proc");
        map2.put("setting.class_id", "425");
        return map2;
    }

    @Test
    //1. Test equal, ignore order
    public void testParser() {
        Map<String, String> map1 = myJacksonParser3.parseJson(json);
        assertThat(map1, is(createExpectedMap()));
    }

    @Test
    //2. Test size
    public void testParserSize() {
        Map<String, String> map1 = myJacksonParser3.parseJson(json);
        assertThat(map1.size(), is(5));
    }

    @Test
    //3. Test map entry, best!
    public void testParserEntry() {
        Map<String, String> map1 = myJacksonParser3.parseJson(json);
        assertThat(map1, IsMapContaining.hasEntry("setting.class_id", "425"));
        assertThat(map1, not(IsMapContaining.hasEntry("r", "ruby")));
    }

    @Test
    //4. Test map key
    public void testParserKey() {
        Map<String, String> map1 = myJacksonParser3.parseJson(json);
        assertThat(map1, IsMapContaining.hasKey("token"));
    }

    @Test
    //5. Test map value
    public void testParserValue() {
        Map<String, String> map1 = myJacksonParser3.parseJson(json);
        assertThat(map1, IsMapContaining.hasValue("425"));
    }

}
