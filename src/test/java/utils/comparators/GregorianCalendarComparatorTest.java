package utils.comparators;

import org.junit.Test;
import utils.calendar.CalendarUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class GregorianCalendarComparatorTest {

    @Test
    public void compare00() {
        ArrayList<GregorianCalendar> cals = new ArrayList<>();
        cals.add(CalendarUtils.createForDate(2021, 1, 5));
        cals.add(CalendarUtils.createForDate(2020,9,13));
        cals.add(CalendarUtils.createForDate(2020, 10, 12));

        List<GregorianCalendar> sorted = cals.stream()
                .sorted(new GregorianCalendarComparator(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH))
                .collect(Collectors.toList());

        GregorianCalendar cal0 = sorted.get(0);

        assertEquals(2020, cal0.get(Calendar.YEAR));
        assertEquals(13, cal0.get(Calendar.DAY_OF_MONTH));

        GregorianCalendar cal2 = sorted.get(2);

        assertEquals(2021, cal2.get(Calendar.YEAR));
    }
    @Test
    public void compare01() {
        ArrayList<GregorianCalendar> cals = new ArrayList<>();
        cals.add(CalendarUtils.createForDate(2020, 1, 5));
        cals.add(CalendarUtils.createForDate(2020, 2, 12));
        cals.add(CalendarUtils.createForDate(2020, 1,13));

        List<GregorianCalendar> sorted = cals.stream()
                .sorted(new GregorianCalendarComparator(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH))
                .collect(Collectors.toList());

        GregorianCalendar cal0 = sorted.get(0);

        assertEquals(2020, cal0.get(Calendar.YEAR));
        assertEquals(5, cal0.get(Calendar.DAY_OF_MONTH));

        GregorianCalendar cal2 = sorted.get(2);

        assertEquals(Calendar.FEBRUARY, cal2.get(Calendar.MONTH));
    }

    @Test
    public void compare02() {
        ArrayList<GregorianCalendar> cals = new ArrayList<>();
        cals.add(CalendarUtils.createForDate(2020, 12, 5));
        cals.add(CalendarUtils.createForDate(2021, 6, 2));
        cals.add(CalendarUtils.createForDate(2020, 11,13));

        List<GregorianCalendar> sorted = cals.stream()
                .sorted(new GregorianCalendarComparator(Calendar.DAY_OF_MONTH, Calendar.MONTH, Calendar.YEAR))
                .collect(Collectors.toList());

        GregorianCalendar cal0 = sorted.get(0);

        assertEquals(2021, cal0.get(Calendar.YEAR));
        assertEquals(2, cal0.get(Calendar.DAY_OF_MONTH));

        GregorianCalendar cal2 = sorted.get(2);

        assertEquals(13, cal2.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void compare03() {
        ArrayList<GregorianCalendar> cals = new ArrayList<>();
        cals.add(CalendarUtils.createForDate(2020, 6, 2));
        cals.add(CalendarUtils.createForDate(2021, 6, 2));
        cals.add(CalendarUtils.createForDate(2020, 12,2));

        List<GregorianCalendar> sorted = cals.stream()
                .sorted(new GregorianCalendarComparator(Calendar.DAY_OF_MONTH, Calendar.MONTH, Calendar.YEAR))
                .collect(Collectors.toList());

        GregorianCalendar cal2 = sorted.get(2);

        assertEquals(Calendar.DECEMBER, cal2.get(Calendar.MONTH));
        assertEquals(2020, cal2.get(Calendar.YEAR));

        GregorianCalendar cal0 = sorted.get(0);

        assertEquals(2020, cal0.get(Calendar.YEAR));
        assertEquals(Calendar.JUNE, cal0.get(Calendar.MONTH));

    }
    @Test
    public void compare04() {
        GregorianCalendar cal1 = CalendarUtils.createForDate(2020, 2, 2);
        GregorianCalendar cal2 = CalendarUtils.createForDate(2021,2,2);
        assertEquals(1,
                new GregorianCalendarComparator(Calendar.DAY_OF_MONTH, Calendar.MONTH, Calendar.YEAR)
        .compare(cal2, cal1));
    }
    @Test
    public void compare05() {
        GregorianCalendar cal1 = CalendarUtils.createForDate(2020, 2, 2);
        GregorianCalendar cal2 = CalendarUtils.createForDate(2021,6,2);
        assertEquals(1,
                new GregorianCalendarComparator(Calendar.DAY_OF_MONTH, Calendar.MONTH, Calendar.YEAR)
                        .compare(cal2, cal1));
    }

    @Test
    public void compare06() {
        GregorianCalendar cal1 = CalendarUtils.createForDate(2020, 6, 2);
        GregorianCalendar cal2 = CalendarUtils.createForDate(2021,6,2);
        assertEquals(1,
                new GregorianCalendarComparator(Calendar.DAY_OF_MONTH, Calendar.MONTH, Calendar.YEAR)
                        .compare(cal2, cal1));
    }



}