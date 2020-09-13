package logic.events;

import utils.calendar.CalendarUtils;

import java.util.GregorianCalendar;

public class BirthdayEvent extends BaseEvent {

    public BirthdayEvent(GregorianCalendar date, String eventDescription,
                         String targetPerson, String presentDescription){
        super(date, eventDescription);
        this.targetPerson = targetPerson;
        this.presentDescription = presentDescription;
    }

    private String targetPerson;
    private String presentDescription;

    public String getTargetPerson() {
        return targetPerson;
    }

    public void setTargetPerson(String targetPerson) {
        this.targetPerson = targetPerson;
    }

    public String getPresentDescription() {
        return presentDescription;
    }

    public void setPresentDescription(String presentDescription) {
        this.presentDescription = presentDescription;
    }

    @Override
    public String shortDescription() {
        return super.shortDescription() + ". Birthday. Date: " + CalendarUtils.toDateString(eventDate) +
                ". Person : " + targetPerson + "\n";
    }

    @Override
    public String fullDescription() {
        return shortDescription() + "Present: " + presentDescription +
                "\nEvent description: " + eventDescription + "\n";
    }

    private static final String DEFAULT_PERSON = "Person doesn't setted";
    private static final String DEFAULT_PRESENT_DESCRIPTION = "No present description";
}
