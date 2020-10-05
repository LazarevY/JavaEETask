package console.io;

import logic.events.Birthday;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BirthdayReader implements ConsoleClassReader<Birthday> {
    @Override
    public Birthday safeRead(String msg) {
        LocalDate date = null;

        while (date == null){
            String s = InputManager
                    .getInstance()
                    .getStringFromStandardInput("Input date of birthday in format yyyy-mm-dd(like 2020-09-20)");
            try {
                date = LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            catch (DateTimeException e){
                System.out.println(e.getMessage());
                System.out.println("Try again");
                date = null;
            }
        }

        String person = InputManager.getInstance().getStringFromStandardInput("Input birthday man");
        String desc = InputManager.getInstance().getStringFromStandardInput("Input description(optional)");
        String gift = InputManager.getInstance().getStringFromStandardInput("Input gift description");

        return new Birthday(date, desc, person, gift);

    }
}
