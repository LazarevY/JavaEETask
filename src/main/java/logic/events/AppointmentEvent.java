package logic.events;

import utils.calendar.CalendarUtils;

import java.util.GregorianCalendar;

public class AppointmentEvent extends BaseEvent {

    public AppointmentEvent(GregorianCalendar date, String eventDescription,
                            String targetPerson, GregorianCalendar appointmentTime) {
        super(date, eventDescription);
        this.targetPerson = targetPerson;
        this.appointmentTime = appointmentTime;
    }

    public String getTargetPerson() {
        return targetPerson;
    }

    public void setTargetPerson(String targetPerson) {
        this.targetPerson = targetPerson;
    }

    public GregorianCalendar getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(GregorianCalendar appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    @Override
    public String shortDescription() {
        return super.shortDescription() + ". Appointment. Date: " + CalendarUtils.toDateString(super.eventDate) +
                ". Person: " + targetPerson + "\n";
    }

    @Override
    public String fullDescription(){
        return shortDescription() +
                "Appointment time: " + CalendarUtils.toHoursMinutesString(appointmentTime) +
                "\nDescription: " + eventDescription + "\n";
    }

    private String targetPerson;
    private GregorianCalendar appointmentTime;
}
