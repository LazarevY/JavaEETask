package logic.events;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public abstract class BaseEvent {

    public BaseEvent(){
        this(new GregorianCalendar(), "");
    }

    public  BaseEvent(GregorianCalendar date, String eventDescription){
        this.eventId = eventIdCounter++;
        eventDate = date;
        this.eventDescription = eventDescription;
    }

    protected GregorianCalendar eventDate;
    protected String eventDescription;
    private final int eventId;

    private static int eventIdCounter = 0;


    public String shortDescription(){
        return "Event " + eventId;
    }

    public String fullDescription(){
        return this.shortDescription();
    }

    @Override
    public String toString() {
        return fullDescription();
    }

    public GregorianCalendar getEventDate() {
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

    public int getEventId() {
        return eventId;
    }
}
