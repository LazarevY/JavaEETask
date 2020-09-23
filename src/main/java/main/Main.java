package main;

import logic.events.Birthday;
import logic.events.Event;
import logic.expressions.comparators.ComparatorCreator;
import logic.expressions.conditions.Condition;
import logic.expressions.interfaces.SpecificComparator;

import java.time.LocalDate;
import java.time.Month;

public class Main {
    public static void main(String[] args) {

        Condition<Event, Integer> checkDayMore =
                new Condition<>(
                        (SpecificComparator<Integer>) ComparatorCreator.create(">"),
                        (instance, value, comparator) ->
                                comparator.compare(instance.getEventDate().getDayOfMonth(), value),
                        3
                );

        Event e =
                new Birthday(
                        LocalDate.of(2020, Month.JANUARY, 10),
                        "None",
                        "Person",
                        "Gift"
                );

        System.out.println(checkDayMore.check(e));

    }
}
