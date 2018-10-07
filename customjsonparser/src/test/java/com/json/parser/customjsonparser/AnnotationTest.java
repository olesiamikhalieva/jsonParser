package com.json.parser.customjsonparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.CustomJacksonAnnotation.BeanWithCustomAnnotation;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.DisableJacksonAnnotation.MyBean6;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JacksonInject.BeanWithInject;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JacksonMixInAnnotations.MyMixInForIgnoreType;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonAnyGetter.ExtendableBean;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonAnySetter.ExtendableBean2;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonAutoDetect.PrivateBean;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonCreater.BeanWithCreator;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonDeserialize.Event2;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonFilter.BeanWithFilter;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonFormat.Event5;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonGetter_JsonPropertyOrder.MyBean;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonIdentityInfo.ItemWithIdentity;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonIdentityInfo.UserWithIdentity;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonIgnoreProperties_JsonIgnore.BeanWithIgnore;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonIgnoreType.User;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonInclude.MyBean3;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonManagedReference_JsonBackReference.ItemWithRef;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonManagedReference_JsonBackReference.UserWithRef;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonProperty.MyBean5;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonRawValue.RawBean;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonRootName_JsonRootInfo.UserWithRoot;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonSerialize.Event;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonSetter.MyBean2;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonUnwrapped.UnwrappedUser;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonValue.TypeEnumWithValue;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonView.Item;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.JsonView.Views;
import com.json.parser.customjsonparser.exampleJacksonAnnotation.TypeHandlingAnnotations.Zoo;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class AnnotationTest {
    @Test
    public void whenSerializingUsingJsonAnyGetter_thenCorrect()
            throws JsonProcessingException {

        ExtendableBean bean = new ExtendableBean("My bean");
        bean.add("attr1", "val1");
        bean.add("attr2", "val2");

        String result = new ObjectMapper().writeValueAsString(bean);

        assertThat(result, containsString("attr1"));
        assertThat(result, containsString("val1"));
    }

    @Test
    public void whenSerializingUsingJsonGetter_thenCorrect()
            throws JsonProcessingException {

        MyBean bean = new MyBean(1, "My bean");

        String result = new ObjectMapper().writeValueAsString(bean);

        assertThat(result, containsString("My bean"));
        assertThat(result, containsString("1"));
    }

    @Test
    public void whenSerializingUsingJsonRawValue_thenCorrect()
            throws JsonProcessingException {

        RawBean bean = new RawBean("My bean", "{\"attr\":false}");

        String result = new ObjectMapper().writeValueAsString(bean);
        assertThat(result, containsString("My bean"));
        assertThat(result, containsString("{\"attr\":false}"));
    }

    @Test
    public void whenSerializingUsingJsonValue_thenCorrect() throws IOException {

        String enumAsString = new ObjectMapper()
                .writeValueAsString(TypeEnumWithValue.TYPE1);

        assertThat(enumAsString, is("\"Type A\""));
    }


    //????????????????????????
    @Test
    public void whenSerializingUsingJsonRootName_thenCorrect()
            throws JsonProcessingException {

        UserWithRoot user = new UserWithRoot(1, "John");

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        String result = mapper.writeValueAsString(user);

        assertThat(result, containsString("John"));
        assertThat(result, containsString("user"));
    }

    @Test
    public void whenSerializingUsingJsonSerialize_thenCorrect()
            throws JsonProcessingException, ParseException {

        SimpleDateFormat df
                = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

        String toParse = "20-12-2014 02:30:00";
        Date date = df.parse(toParse);
        Event event = new Event("party", date);

        String result = new ObjectMapper().writeValueAsString(event);
        assertThat(result, containsString(toParse));
    }

    @Test
    public void whenDeserializingUsingJsonCreator_thenCorrect()
            throws IOException {

        String json = "{\"id\":1,\"theName\":\"My bean\"}";

        BeanWithCreator bean = new ObjectMapper()
                .readerFor(BeanWithCreator.class)
                .readValue(json);
        assertEquals("My bean", bean.name);
    }

    @Test
    public void whenDeserializingUsingJsonInject_thenCorrect()
            throws IOException {

        String json = "{\"name\":\"My bean\"}";

        InjectableValues inject = new InjectableValues.Std()
                .addValue(int.class, 1);
        BeanWithInject bean = new ObjectMapper().reader(inject)
                .forType(BeanWithInject.class)
                .readValue(json);

        assertEquals("My bean", bean.name);
        assertEquals(1, bean.id);
    }


    @Test
    public void whenDeserializingUsingJsonAnySetter_thenCorrect()
            throws IOException {
        String json
                = "{ \n" +
                "    \"name\":\"My bean\", \n" +
                "    \"attr2\":\"val2\", \n" +
                "    \"attr1\":\"val1\" \n" +
                "} ";

        ExtendableBean2 bean = new ObjectMapper()
                .readerFor(ExtendableBean2.class)
                .readValue(json);

        assertEquals("My bean", bean.name);
        assertEquals("val2", bean.getProperties().get("attr2"));
    }

    @Test
    public void whenDeserializingUsingJsonSetter_thenCorrect()
            throws IOException {

        String json = "{\"id\":1,\"name\":\"My bean\"}";

        MyBean2 bean = new ObjectMapper()
                .readerFor(MyBean2.class)
                .readValue(json);
        assertEquals("My bean", bean.getTheName());
    }


    @Test
    public void whenDeserializingUsingJsonDeserialize_thenCorrect()
            throws IOException {

        String json
                = "{\"name\":\"party\",\"eventDate\":\"20-12-2014 02:30:00\"}";

        SimpleDateFormat df
                = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        Event event = new ObjectMapper()
                .readerFor(Event2.class)
                .readValue(json);

        assertEquals(
                "20-12-2014 02:30:00", df.format(event.eventDate));
    }


    @Test
    public void whenSerializingUsingJsonIgnoreProperties_thenCorrect()
            throws JsonProcessingException {

        BeanWithIgnore bean = new BeanWithIgnore(1, "My bean");

        String result = new ObjectMapper()
                .writeValueAsString(bean);

        assertThat(result, containsString("My bean"));
        assertThat(result, not(containsString("id")));
    }


    @Test
    public void whenSerializingUsingJsonIgnoreType_thenCorrect()
            throws JsonProcessingException, ParseException {

        User.Name name = new User.Name("John", "Doe");
        User user = new User(1, name);

        String result = new ObjectMapper()
                .writeValueAsString(user);

        assertThat(result, containsString("1"));
        assertThat(result, not(containsString("name")));
        assertThat(result, not(containsString("John")));
    }

    @Test
    public void whenSerializingUsingJsonInclude_thenCorrect()
            throws JsonProcessingException {

        MyBean3 bean = new MyBean3(1, null);

        String result = new ObjectMapper()
                .writeValueAsString(bean);

        assertThat(result, containsString("1"));
        assertThat(result, not(containsString("name")));
    }

    @Test
    public void whenSerializingUsingJsonAutoDetect_thenCorrect()
            throws JsonProcessingException {

        PrivateBean bean = new PrivateBean(1, "My bean");

        String result = new ObjectMapper()
                .writeValueAsString(bean);

        assertThat(result, containsString("1"));
        assertThat(result, containsString("My bean"));
    }


    @Test
    public void whenSerializingPolymorphic_thenCorrect()
            throws JsonProcessingException {
        Zoo.Dog dog = new Zoo.Dog("lacy");
        Zoo zoo = new Zoo(dog);

        String result = new ObjectMapper()
                .writeValueAsString(zoo);

        assertThat(result, containsString("type"));
        assertThat(result, containsString("dog"));
    }

    @Test
    public void whenDeserializingPolymorphic_thenCorrect()
            throws IOException {
        String json = "{\"animal\":{\"name\":\"lacy\",\"type\":\"cat\"}}";

        Zoo zoo = new ObjectMapper()
                .readerFor(Zoo.class)
                .readValue(json);

        assertEquals("lacy", zoo.animal.name);
        assertEquals(Zoo.Cat.class, zoo.animal.getClass());
    }

    @Test
    public void whenUsingJsonProperty_thenCorrect()
            throws IOException {
        MyBean5 bean = new MyBean5(1, "My bean");

        String result = new ObjectMapper().writeValueAsString(bean);

        assertThat(result, containsString("My bean"));
        assertThat(result, containsString("1"));

        MyBean5 resultBean = new ObjectMapper()
                .readerFor(MyBean5.class)
                .readValue(result);
        assertEquals("My bean", resultBean.getTheName());
    }


    @Test
    public void whenSerializingUsingJsonFormat_thenCorrect()
            throws JsonProcessingException, ParseException {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        String toParse = "20-12-2014 02:30:00";
        Date date = df.parse(toParse);
        Event5 event = new Event5("party", date);

        String result = new ObjectMapper().writeValueAsString(event);

        assertThat(result, containsString(toParse));
    }

    @Test
    public void whenSerializingUsingJsonUnwrapped_thenCorrect()
            throws JsonProcessingException, ParseException {
        UnwrappedUser.Name name = new UnwrappedUser.Name("John", "Doe");
        UnwrappedUser user = new UnwrappedUser(1, name);

        String result = new ObjectMapper().writeValueAsString(user);

        assertThat(result, containsString("John"));
        assertThat(result, not(containsString("name")));
    }

    @Test
    public void whenSerializingUsingJsonView_thenCorrect()
            throws JsonProcessingException {
        Item item = new Item(2, "book", "John");

        String result = new ObjectMapper()
                .writerWithView(Views.Public.class)
                .writeValueAsString(item);

        assertThat(result, containsString("book"));
        assertThat(result, containsString("2"));
        assertThat(result, not(containsString("John")));
    }


    @Test
    public void whenSerializingUsingJacksonReferenceAnnotation_thenCorrect()
            throws JsonProcessingException {
        UserWithRef user = new UserWithRef(1, "John");
        ItemWithRef item = new ItemWithRef(2, "book", user);
        user.addItem(item);

        String result = new ObjectMapper().writeValueAsString(item);

        assertThat(result, containsString("book"));
        assertThat(result, containsString("John"));
        assertThat(result, not(containsString("userItems")));
    }

    @Test
    public void whenSerializingUsingJsonIdentityInfo_thenCorrect()
            throws JsonProcessingException {
        UserWithIdentity user = new UserWithIdentity(1, "John");
        ItemWithIdentity item = new ItemWithIdentity(2, "book", user);
        user.addItem(item);

        String result = new ObjectMapper().writeValueAsString(item);

        assertThat(result, containsString("book"));
        assertThat(result, containsString("John"));
        assertThat(result, containsString("userItems"));
    }

    @Test
    public void whenSerializingUsingJsonFilter_thenCorrect()
            throws JsonProcessingException {
        BeanWithFilter bean = new BeanWithFilter(1, "My bean");

        FilterProvider filters
                = new SimpleFilterProvider().addFilter(
                "myFilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("name"));

        String result = new ObjectMapper()
                .writer(filters)
                .writeValueAsString(bean);

        assertThat(result, containsString("My bean"));
        assertThat(result, not(containsString("id")));
    }


    @Test
    public void whenSerializingUsingCustomAnnotation_thenCorrect()
            throws JsonProcessingException {
        BeanWithCustomAnnotation bean
                = new BeanWithCustomAnnotation(1, "My bean", null);

        String result = new ObjectMapper().writeValueAsString(bean);

        assertThat(result, containsString("My bean"));
        assertThat(result, containsString("1"));
        assertThat(result, not(containsString("dateCreated")));
    }
//???????????????????????????????????????
    @Test
    public void whenSerializingUsingMixInAnnotation_thenCorrect()
            throws JsonProcessingException {
        Item item = new Item(1, "book", null);

        String result = new ObjectMapper().writeValueAsString(item);
        assertThat(result, containsString("owner"));

        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(User.class, MyMixInForIgnoreType.class);

        result = mapper.writeValueAsString(item);
        assertThat(result, not(containsString("owner")));
    }

    @Test
    public void whenDisablingAllAnnotations_thenAllDisabled()
            throws IOException {
        MyBean6 bean = new MyBean6(1, null);

        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(MapperFeature.USE_ANNOTATIONS);
        String result = mapper.writeValueAsString(bean);

        assertThat(result, containsString("1"));
        assertThat(result, containsString("name"));
    }


}