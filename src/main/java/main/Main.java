package main;
import database.emulation.DataBaseEmulation;
import logic.events.*;
import utils.calendar.CalendarUtils;

import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        BaseEvent e = new BirthdayEvent(
                CalendarUtils.createCalendarForDate(2021, 6, 20),
                "My best friend BD",
                "Andrey",
                "Comrades friendship");
        BaseEvent e1 = new BirthdayEvent(
                CalendarUtils.createCalendarForDate(2022, 6, 20),
                "My best friend BD",
                "Andrey",
                "Comrades friendship");
        BaseEvent e2 = new BirthdayEvent(
                CalendarUtils.createCalendarForDate(2020, 6, 20),
                "My best friend BD",
                "Andrey",
                "Comrades friendship");

        DataBaseEmulation dataBaseEmulation = new DataBaseEmulation();
        dataBaseEmulation.insertEvent(e);
        dataBaseEmulation.insertEvent(e1);
        dataBaseEmulation.insertEvent(e2);

        for (BaseEvent evt: dataBaseEmulation.
                eventsForPredicate(
                        ev->ev.getEventDate().get(Calendar.YEAR) == 2020
                ))
            System.out.println(evt.shortDescription());

    }
}
