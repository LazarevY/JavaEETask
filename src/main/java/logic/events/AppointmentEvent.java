package logic.events;

import java.util.GregorianCalendar;

public class AppointmentEvent {

    public AppointmentEvent(String targetPerson, GregorianCalendar appointmentTime) {
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

    private String targetPerson;
    private GregorianCalendar appointmentTime;
}
