package logic.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import core.database.annotations.AutoGen;
import core.database.annotations.Column;
import core.database.annotations.Id;
import core.inverseofcontrol.annotations.PropertyGetter;
import core.inverseofcontrol.annotations.PropertySetter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Birthday.class, name = "birthday"),
        @JsonSubTypes.Type(value = Appointment.class, name = "appointment")
})
public abstract class Event implements Serializable {

    @Column
    protected LocalDate date = LocalDate.ofEpochDay(1);
    @Column
    protected String description = "";
    @Id(autoGen = AutoGen.TRUE)
    @Column
    private int id;


    public Event() {

    }

    public Event(LocalDate date, String description) {
        this.date = date;
        this.description = description;
    }


    public String shortDescription() {
        return "Event id: " + id;
    }

    public String fullDescription() {
        return this.shortDescription();
    }

    @Override
    public String toString() {
        return fullDescription();
    }

    @PropertyGetter("eventDate")
    public LocalDate getEventDate() {
        return date;
    }

    @PropertySetter("eventDate")
    public void setEventDate(LocalDate date) {
        this.date = date;
    }

    @PropertyGetter("eventDescription")
    public String getDescription() {
        return description;
    }

    @PropertySetter("eventDescription")
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event baseEvent = (Event) o;
        return date.equals(baseEvent.date) &&
                description.equals(baseEvent.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description);
    }

    @PropertyGetter("id")
    public int getId() {
        return id;
    }

    @PropertySetter("id")
    public void setId(int id) {
        this.id = id;
    }

    @PropertyGetter("day")
    private int getDay() {
        return date.getDayOfMonth();
    }

    @PropertySetter("day")
    private void setDay(int value) {
        date = LocalDate.of(date.getYear(), date.getMonth(), value);
    }

    @PropertyGetter("month")
    private int getMonthValue() {
        return date.getMonthValue();
    }

    @PropertySetter("month")
    private void setMonth(int value) {
        date = LocalDate.of(date.getYear(), value, date.getDayOfMonth());
    }

    @PropertyGetter("year")
    private int getYear() {
        return date.getYear();
    }

    @PropertySetter("year")
    private void setYear(int value) {
        date = LocalDate.of(value, date.getMonth(), date.getDayOfMonth());
    }
}
