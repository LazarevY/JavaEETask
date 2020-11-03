package console.io;

import core.inverseofcontrol.annotations.InjectByType;
import core.inverseofcontrol.annotations.Singleton;
import logic.events.Appointment;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Singleton
public class AppointmentReader implements ConsoleClassReader<Appointment> {

    @InjectByType
    private LocalTimeReader timeReader;

    @InjectByType
    private InputManager inputManager;

    @Override
    @SuppressWarnings("duplicated")
    public Appointment safeRead(String msg) {
        LocalDate date = null;

        while (date == null) {
            String s = inputManager
                    .getStringFromStandardInput("Input date of birthday in format yyyy-mm-dd(like 2020-09-20)");
            try {
                date = LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Try again");
                date = null;
            }
        }

        LocalTime time = timeReader.safeRead("Input time in format like 18:30");

        String person = inputManager.getStringFromStandardInput("Input appointment man");
        String desc = inputManager.getStringFromStandardInput("Input description(optional)");

        return new Appointment(date, desc, person, time);
    }
}
