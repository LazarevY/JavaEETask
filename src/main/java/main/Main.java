package main;
import logic.events.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        GregorianCalendar date = new GregorianCalendar(1,Calendar.FEBRUARY,1, 22, 10, 0);
        System.out.println(date.get(Calendar.HOUR_OF_DAY));
    }
}
