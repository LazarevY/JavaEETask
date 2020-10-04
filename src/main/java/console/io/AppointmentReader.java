package console.io;

import logic.events.Appointment;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AppointmentReader implements ConsoleClassReader<Appointment>{
    @Override
    @SuppressWarnings("duplicated")
    public Appointment safeRead() {
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

        LocalTime time = null;

        while (time == null){
            String s = InputManager
                    .getInstance()
                    .getStringFromStandardInput("Input date of birthday in format hh:mm(like 18:30)");
            try {
                time = LocalTime.parse(s);
            }
            catch (DateTimeException e){
                System.out.println(e.getMessage());
                System.out.println("Try again");
                time = null;
            }
        }

        String person = InputManager.getInstance().getStringFromStandardInput("Input appointment man");
        String desc = InputManager.getInstance().getStringFromStandardInput("Input description(optional)");

        return new Appointment(date, desc, person, time);
    }
}
