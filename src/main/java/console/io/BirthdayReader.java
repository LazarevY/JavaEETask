package console.io;

import core.annotations.InjectByType;
import core.annotations.Singleton;
import logic.events.Birthday;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Singleton
public class BirthdayReader implements ConsoleClassReader<Birthday> {

    @InjectByType
    private InputManager inputManager;

    @Override
    public Birthday safeRead(String msg) {
        LocalDate date = null;

        while (date == null){
            String s = inputManager
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

        String person = inputManager.getStringFromStandardInput("Input birthday man");
        String desc   = inputManager.getStringFromStandardInput("Input description(optional)");
        String gift   = inputManager.getStringFromStandardInput("Input gift description");

        return new Birthday(date, desc, person, gift);

    }
}
