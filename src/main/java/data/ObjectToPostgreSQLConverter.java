package data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class ObjectToPostgreSQLConverter implements ObjectToSQLConverter {
    @Override
    public String convert(Object o) {
        if (o instanceof String)
            return String.format("'%s'", o.toString());
        else if (o instanceof LocalDate)
            return String.format("TIMESTAMP '%s'", o.toString());
        else if (o instanceof LocalTime)
            return String.format("TIME '%s'",
                    ((LocalTime)o).format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        else
            return String.format("%s", o.toString());
    }
}
