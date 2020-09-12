package main;
import logic.events.*;
import utils.calendar.CalendarUtils;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        GregorianCalendar cal = CalendarUtils.createCalendarForTime(20,0);
        System.out.println(CalendarUtils.toHoursMinutesString(cal));
    }
}
