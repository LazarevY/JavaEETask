package utils.calendar;

import utils.comparators.GregorianCalendarComparator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarUtils {
    public static GregorianCalendar createForTime(int hours, int minutes, int seconds) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR, hours);
        cal.set(Calendar.MINUTE, minutes);
        cal.set(Calendar.SECOND, seconds);
        return cal;
    }
    public static GregorianCalendar createForTime(int hours, int minutes) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, hours);
        cal.set(Calendar.MINUTE, minutes);
        return cal;
    }

    public static GregorianCalendar createForDate(int year, int month, int day){
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal;
    }

    public static String toHoursMinutesString(GregorianCalendar calendar){
        return new SimpleDateFormat("HH:mm").format(calendar.getTime());
    }

    public static String toHoursMinutesSecondsString(GregorianCalendar calendar){
        return new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());
    }

    public static String toDateString(GregorianCalendar calendar){
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
    }
}
