package logic.events;

import core.annotations.PropertyGetter;
import core.annotations.PropertySetter;
import database.annotations.Column;
import database.annotations.Entity;

import java.io.Serializable;
import java.time.LocalDate;

@Entity("birthdays")
public class Birthday extends Event implements Serializable {

    public Birthday(LocalDate date, String description,
                    String targetPerson, String gift){
        super(date, description);
        this.targetPerson = targetPerson;
        this.gift = gift;
    }

    public Birthday(){
        super(LocalDate.now(), "");
        targetPerson = "";
        gift = "";
    }

    @Column
    private String targetPerson;
    @Column
    private String gift;

    @PropertyGetter("birthdayPerson")
    public String getTargetPerson() {
        return targetPerson;
    }

    @PropertySetter("birthdayPerson")
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private static final String DEFAULT_PERSON = "Person doesn't setted";
    private static final String DEFAULT_PRESENT_DESCRIPTION = "No present description";
}
