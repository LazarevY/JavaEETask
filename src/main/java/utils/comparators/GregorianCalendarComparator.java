package utils.comparators;

import java.util.*;

public class GregorianCalendarComparator implements Comparator<GregorianCalendar> {

    public GregorianCalendarComparator(int field1, Integer...fields){
        fieldsForCompare = new ArrayList<>();
        fieldsForCompare.add(field1);
        fieldsForCompare.addAll(Arrays.asList(fields));
    }

    @Override
    public int compare(GregorianCalendar cal1, GregorianCalendar cal2) {
        int c = 0;
        for (Integer param: fieldsForCompare){
            c = Integer.compare(cal1.get(param), cal2.get(param));
            if (c != 0)
                break;
        }

        return c;
    }

    private ArrayList<Integer> fieldsForCompare;
}
