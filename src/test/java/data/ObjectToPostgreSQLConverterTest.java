package data;

import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectToPostgreSQLConverterTest {

    @Test
    public void test000(){
        ObjectToPostgreSQLConverter converter = new ObjectToPostgreSQLConverter();
        String toTest = "`\"str\"`";

        assertEquals(toTest, converter.convert("str"));

    }
}