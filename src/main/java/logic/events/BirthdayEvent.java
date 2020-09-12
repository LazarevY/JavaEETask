package logic.events;

public class BirthdayEvent extends BaseEvent {

    public BirthdayEvent(){
        super();
        targetPerson = DEFAULT_PERSON;
        presentDescription = DEFAULT_PRESENT_DESCRIPTION;
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

    private static final String DEFAULT_PERSON = "Person doesn't setted";
    private static final String DEFAULT_PRESENT_DESCRIPTION = "No present description";
}
