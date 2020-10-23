package logic.expressions.utils;

import data.Filter;
import data.property.PropertyManager;
import logic.events.Event;
import logic.expressions.comparators.ComparatorCreator;
import logic.expressions.conditions.Condition;
import logic.expressions.interfaces.SpecificComparator;
import logic.expressions.predicates.ConditionsPredicate;

import java.util.List;

public class ExpressionsUtils {

    public static ConditionsPredicate<?> buildPredicate(List<Filter<?>> filters) {
        ConditionsPredicate<Event> predicate =
                new ConditionsPredicate<>();
        for (Filter<?> d : filters) {
            Condition<Event, ?> c =
                    new Condition<>(
                            (SpecificComparator<Object>) ComparatorCreator
                                    .getInstance()
                                    .createComparator(d.getOperator(), d.getAttributeClass()),
                            new PropertyManager(d.getAttribute()),
                            d.getValue());
            predicate.addCondition(c, d.getType());
        }
        return predicate;
    }

}
