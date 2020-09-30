package logic.events;

import annotations.PropertyGetter;
import utils.calendar.CalendarUtils;

import java.time.LocalDate;
import java.util.Date;

public class Birthday extends Event {

    public Birthday(LocalDate date, String description,
                    String targetPerson, String gift){
        super(date, description);
        this.targetPerson = targetPerson;
        this.gift = gift;
    }

    private String targetPerson;
    private String gift;

    @PropertyGetter("birthdayPerson")
    public String getTargetPerson() {
        return targetPerson;
    }

    public void setTargetPerson(String targetPerson) {
        this.targetPerson = targetPerson;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    @Override
    public String shortDescription() {
        return super.shortDescription() + ". Birthday. Date: " + date.toString() +
                ". Person : " + targetPerson + "\n";
    }

    @Override
    public String fullDescription() {
        return shortDescription() + "Present: " + gift +
                "\nEvent description: " + description + "\n";
    }

    private static final String DEFAULT_PERSON = "Person doesn't setted";
    private static final String DEFAULT_PRESENT_DESCRIPTION = "No present description";
}
