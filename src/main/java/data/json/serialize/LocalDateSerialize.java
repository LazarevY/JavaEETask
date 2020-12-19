package data.json.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @author Lazarev Yaroslav
 */


public class LocalDateSerialize extends StdSerializer<LocalDate> {

    public LocalDateSerialize() {
        this(null);
    }

    protected LocalDateSerialize(Class<LocalDate> t) {
        super(t);
    }

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("year", localDate.getYear());
        jsonGenerator.writeNumberField("month", localDate.getMonthValue());
        jsonGenerator.writeNumberField("day", localDate.getDayOfMonth());
        jsonGenerator.writeEndObject();
    }
}
