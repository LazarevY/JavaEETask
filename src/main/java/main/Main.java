package main;
import logic.events.*;
import utils.calendar.CalendarUtils;

public class Main {
    public static void main(String[] args) {
        BaseEvent e = new BirthdayEvent(
                CalendarUtils.createCalendarForDate(2021, 6, 20),
                "My best friend BD",
                "Andrey",
                "Comrades friendship");
        System.out.println(e.shortDescription());
        System.out.println(e.fullDescription());
    }
}
