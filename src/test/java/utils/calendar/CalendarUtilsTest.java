package utils.calendar;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class CalendarUtilsTest {

    @Test
    public void createCalendarForTimeTest00() {
        GregorianCalendar cal = CalendarUtils.createForTime(10, 10);
        assertEquals(10, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(10, cal.get(Calendar.MINUTE));
    }

    @Test
    public void createCalendarForTimeTest01() {
        GregorianCalendar cal = CalendarUtils.createForTime(12, 0);
        assertEquals(12, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(0, cal.get(Calendar.MINUTE));
    }


    @Test
    public void createCalendarForTimeTest02() {
        GregorianCalendar cal = CalendarUtils.createForTime(0, 30);
        assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(30, cal.get(Calendar.MINUTE));
    }

    @Test
    public void toHoursMinutesString00() {
        GregorianCalendar cal = CalendarUtils.createForTime(12,30);
        assertEquals(new String("12:30"), CalendarUtils.toHoursMinutesString(cal));
    }

    @Test
    public void toHoursMinutesString01() {
        GregorianCalendar cal = CalendarUtils.createForTime(16,20);
        assertEquals("16:20", CalendarUtils.toHoursMinutesString(cal));
    }

    @Test
    public void toHoursMinutesString02() {
        GregorianCalendar cal = CalendarUtils.createForTime(0,0);
        assertEquals("00:00", CalendarUtils.toHoursMinutesString(cal));
    }

    @Test
    public void createCalenderForDate00(){
        GregorianCalendar cal = CalendarUtils.createForDate(2020, 9, 13);
        assertEquals(2020, cal.get(Calendar.YEAR));
        assertEquals(9, cal.get(Calendar.MONTH));
        assertEquals(13, cal.get(Calendar.DAY_OF_MONTH));
    }
    @Test
    public void createCalenderForDate01(){
        GregorianCalendar cal = CalendarUtils.createForDate(2020, 6, 2);
        assertEquals(2020, cal.get(Calendar.YEAR));
        assertEquals(6, cal.get(Calendar.MONTH));
        assertEquals(2, cal.get(Calendar.DAY_OF_MONTH));
    }
}