package console.io;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateReader implements ConsoleClassReader<LocalDate> {
    @Override
    public LocalDate safeRead(String msg) {
        LocalDate date = null;

        while (date == null){
            String s = InputManager
                    .getInstance()
                    .getStringFromStandardInput(msg + " Format like (2020-09-22)");
            try {
                date = LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            catch (DateTimeException e){
                System.out.println(e.getMessage());
                System.out.println("Try again");
                date = null;
            }
        }
        return date;
    }
}
