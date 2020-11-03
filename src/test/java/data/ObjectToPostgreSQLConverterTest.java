package data;

import core.database.maping.PostgreObjectMapper;
import org.junit.Assert;
import org.junit.Test;

public class ObjectToPostgreSQLConverterTest {

    @Test
    public void test000() {
        PostgreObjectMapper converter = new PostgreObjectMapper();
        String toTest = "'str'";

        Assert.assertEquals(toTest, converter.convert("str"));

    }
}