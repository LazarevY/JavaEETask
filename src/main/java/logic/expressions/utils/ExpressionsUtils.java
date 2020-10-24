package logic.expressions.utils;

import data.Filter;
import data.property.PropertyManager;
import logic.expressions.comparators.ComparatorCreator;
import logic.expressions.conditions.Condition;
import logic.expressions.interfaces.SpecificComparator;
import logic.expressions.predicates.ConditionsPredicate;

import java.util.List;
import java.util.function.Predicate;

public class ExpressionsUtils {

    public static Predicate<Object> buildPredicate(List<Filter<?>> filters, Class dataType) {
        ConditionsPredicate predicate =
                new ConditionsPredicate(dataType);
        for (Filter<?> d : filters) {
            Condition<Object, Object> c =
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
