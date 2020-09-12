package logic.events;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public abstract class BaseEvent {

    public BaseEvent(){
        this(new GregorianCalendar(), "");
    }

    public  BaseEvent(GregorianCalendar date, String eventDescription){
        eventDate = date;
        this.eventDescription = eventDescription;
    }


    public String shortDescription(){
        return "Empty description";
    }

    public String fullDescription(){
        return "Empty description";
    }

    @Override
    public String toString() {
        return fullDescription();
    }

    private GregorianCalendar eventDate;
    private String eventDescription;

    public GregorianCalendar eventDate() {
        return eventDate;
    }

    public void setEventDate(GregorianCalendar eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEvent baseEvent = (BaseEvent) o;
        return eventDate.equals(baseEvent.eventDate) &&
                eventDescription.equals(baseEvent.eventDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventDate, eventDescription);
    }
}
