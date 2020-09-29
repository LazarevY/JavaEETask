package logic.events;

import annotations.PropertyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public abstract class Event {

    protected LocalDate date = LocalDate.ofEpochDay(1);
    protected String description = "";

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

    public LocalDate getEventDate() {
        return date;
    }

    public void setEventDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

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

    public void setId(int id) {
        this.id = id;
    }

    @PropertyGetter("day")
    private int getDay(){
        return date.getDayOfMonth();
    }
}
