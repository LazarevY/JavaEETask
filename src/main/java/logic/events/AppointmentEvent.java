package logic.events;

import utils.calendar.CalendarUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.GregorianCalendar;

public class AppointmentEvent extends Event {

    public AppointmentEvent(LocalDate date, String eventDescription,
                            String targetPerson, LocalTime time) {
        super(date, eventDescription);
        this.targetPerson = targetPerson;
        this.time = time;
    }

    public String getTargetPerson() {
        return targetPerson;
    }

    public void setTargetPerson(String targetPerson) {
        this.targetPerson = targetPerson;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String shortDescription() {
        return super.shortDescription() + ". Appointment. Date: " + super.date.toString() +
                ". Person: " + targetPerson + "\n";
    }

    @Override
    public String fullDescription(){
        return shortDescription() +
                "Appointment time: " + time.toString() +
                "\nDescription: " + description + "\n";
    }

    private String targetPerson;
    private LocalTime time;
}
