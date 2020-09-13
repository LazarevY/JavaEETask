package main;
import logic.events.*;
import utils.calendar.CalendarUtils;
import utils.comparators.GregorianCalendarComparator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<GregorianCalendar> cals = new ArrayList<>();
        cals.add(CalendarUtils.createCalendarForDate(2020, 2, 30));

        for (GregorianCalendar c: cals){
            System.out.println(CalendarUtils.toDateString(c));
            c.getTime();
        }
    }
}
