package console.io;

import core.annotations.InjectByType;
import logic.events.Appointment;
import ui.console.commands.Input;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class AppointmentReader implements ConsoleClassReader<Appointment> {

    @InjectByType
    private Input input;

    @Override
    @SuppressWarnings("duplicated")
    public Appointment safeRead(String msg) {
        LocalDate date = null;

        while (date == null) {
            String s = InputManager
                    .getInstance()
                    .getStringFromStandardInput("Input date of birthday in format yyyy-mm-dd(like 2020-09-20)");
            try {
                date = LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again");
                date = null;
            }
        }

        LocalTime time = (LocalTime) input.execute(Map.of("type", LocalTime.class
        , "msg", "Input time in format like 18:30"))
                .getReturnMap().get("input");

        String person = InputManager.getInstance().getStringFromStandardInput("Input appointment man");
        String desc = InputManager.getInstance().getStringFromStandardInput("Input description(optional)");

        return new Appointment(date, desc, person, time);
    }
}
