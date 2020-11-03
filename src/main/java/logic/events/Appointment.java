package logic.events;

import core.database.annotations.Column;
import core.database.annotations.Entity;
import core.inverseofcontrol.annotations.PropertyGetter;
import core.inverseofcontrol.annotations.PropertySetter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity("appointments")
public class Appointment extends Event implements Serializable {

    @Column
    private String targetPerson;
    @Column("appointmentTime")
    private LocalTime time;

    public Appointment(LocalDate date, String eventDescription,
                       String targetPerson, LocalTime time) {
        super(date, eventDescription);
        this.targetPerson = targetPerson;
        this.time = time;
    }

    public Appointment() {
        super(LocalDate.now(), "");
        targetPerson = "";
        time = LocalTime.NOON;
    }

    @PropertyGetter("appointmentPerson")
    public String getTargetPerson() {
        return targetPerson;
    }

    @PropertySetter("appointmentPerson")
    public void setTargetPerson(String targetPerson) {
        this.targetPerson = targetPerson;
    }

    @PropertyGetter("appointmentTime")
    public LocalTime getTime() {
        return time;
    }

    @PropertySetter("appointmentTime")
    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String shortDescription() {
        return super.shortDescription() + ". Appointment. Date: " + super.date.toString() +
                ". Person: " + targetPerson + "\n";
    }

    @Override
    public String fullDescription() {
        return shortDescription() +
                "Appointment time: " + time.toString() +
                "\nDescription: " + description + "\n";
    }
}
