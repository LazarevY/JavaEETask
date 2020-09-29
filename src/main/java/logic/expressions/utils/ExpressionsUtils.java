package logic.expressions.utils;

import data.Filter;
import logic.events.Appointment;
import logic.events.Birthday;
import logic.events.Event;
import logic.expressions.comparators.ComparatorCreator;
import logic.expressions.conditions.Condition;
import logic.expressions.interfaces.ConditionChecker;
import logic.expressions.interfaces.SpecificComparator;

public class ExpressionsUtils {

    public static Condition<? extends Event,?> createConditionForFilter(Filter filter){
        switch (filter.getAttribute()){
            case "day":
                return new Condition<>(
                        (SpecificComparator<Integer>) ComparatorCreator.create(filter.getOperator()),
                        (instance, value, comparator) ->
                                comparator.compare(instance.getEventDate().getDayOfMonth(), value),
                        (Integer) filter.getValue()
                );
            case "month":
                return new Condition<>(
                        (SpecificComparator<Integer>) ComparatorCreator.create(filter.getOperator()),
                        (instance, value, comparator) ->
                                comparator.compare(instance.getEventDate().getMonthValue(), value),
                        (Integer) filter.getValue()
                );
            case "year":
                return new Condition<>(
                        (SpecificComparator<Integer>) ComparatorCreator.create(filter.getOperator()),
                        (instance, value, comparator) ->
                                comparator.compare(instance.getEventDate().getYear(), value),
                        (Integer) filter.getValue()
                );
            case "id":
                return new Condition<>(
                        (SpecificComparator<Integer>) ComparatorCreator.create(filter.getOperator()),
                        (instance, value, comparator) ->
                                comparator.compare(instance.getId(), value),
                        (Integer) filter.getValue()
                );
            case "appointmentPerson":
                return new Condition<Appointment, String>(
                        (SpecificComparator<String>) ComparatorCreator.create(filter.getOperator()),
                        (instance, value, comparator) ->
                                comparator.compare(instance.getTargetPerson(), value),
                        (String) filter.getValue()
                );
            case "birthdayPerson":
                return new Condition<Birthday, String>(
                        (SpecificComparator<String>) ComparatorCreator.create(filter.getOperator()),
                        (instance, value, comparator) ->
                                comparator.compare(instance.getTargetPerson(), value),
                        (String) filter.getValue()
                );
            case "appointmentHour":
                return new Condition<Appointment, Integer>(
                        (SpecificComparator<Integer>) ComparatorCreator.create(filter.getOperator()),
                        (instance, value, comparator) ->
                                comparator.compare(instance.getTime().getHour(), value),
                        (Integer) filter.getValue()
                );
            case "appointmentMinute":
                return new Condition<Appointment, Integer>(
                        (SpecificComparator<Integer>) ComparatorCreator.create(filter.getOperator()),
                        (instance, value, comparator) ->
                                comparator.compare(instance.getTime().getMinute(), value),
                        (Integer) filter.getValue()
                );

        }

        return null;
    }

}
