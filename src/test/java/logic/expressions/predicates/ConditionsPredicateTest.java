package logic.expressions.predicates;

import data.AttributeFilterType;
import data.Filter;
import logic.events.Birthday;
import logic.events.Event;
import logic.expressions.comparators.OperatorType;
import logic.expressions.utils.ExpressionsUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ConditionsPredicateTest {

    @Test
    public void test000(){
        Event e = new Birthday(LocalDate.of(2020, Month.APRIL, 14),
                "Desc",
                "Person",
                "Gift");


        List<Filter<?>> filters =
                Arrays.asList(
                        new Filter<>("day", OperatorType.Equal, 14, Integer.class, AttributeFilterType.Or),
                        new Filter<>("month", OperatorType.Equal, Month.APRIL.getValue(), Integer.class, AttributeFilterType.Or)
                );

        ConditionsPredicate<Event> conditionsPredicate =
                (ConditionsPredicate<Event>) ExpressionsUtils.buildPredicate(filters);

        assertTrue(conditionsPredicate.test(e));
    }
    @Test
    public void test001(){
        Event e = new Birthday(LocalDate.of(2020, Month.APRIL, 14),
                "Desc",
                "Person",
                "Gift");


        List<Filter<?>> filters =
                Arrays.asList(
                        new Filter<>("day", OperatorType.Equal, 14, Integer.class, AttributeFilterType.Or),
                        new Filter<>("month", OperatorType.Equal, Month.FEBRUARY.getValue(), Integer.class, AttributeFilterType.Or)
                );

        ConditionsPredicate<Event> conditionsPredicate =
                (ConditionsPredicate<Event>) ExpressionsUtils.buildPredicate(filters);

        assertTrue(conditionsPredicate.test(e));
    }

    @Test
    public void test002(){
        Event e = new Birthday(LocalDate.of(2020, Month.APRIL, 14),
                "Desc",
                "Person",
                "Gift");


        List<Filter<?>> filters =
                Arrays.asList(
                        new Filter<>("day", OperatorType.Equal, 31, Integer.class, AttributeFilterType.Or),
                        new Filter<>("month", OperatorType.Equal, Month.APRIL.getValue(), Integer.class, AttributeFilterType.Or)
                );

        ConditionsPredicate<Event> conditionsPredicate =
                (ConditionsPredicate<Event>) ExpressionsUtils.buildPredicate(filters);

        assertTrue(conditionsPredicate.test(e));
    }

    @Test
    public void test003(){
        Event e = new Birthday(LocalDate.of(2020, Month.APRIL, 14),
                "Desc",
                "Person",
                "Gift");


        List<Filter<?>> filters =
                Arrays.asList(
                        new Filter<>("day", OperatorType.Equal, 31, Integer.class, AttributeFilterType.Or),
                        new Filter<>("month", OperatorType.Equal, Month.AUGUST.getValue(), Integer.class, AttributeFilterType.Or)
                );

        ConditionsPredicate<Event> conditionsPredicate =
                (ConditionsPredicate<Event>) ExpressionsUtils.buildPredicate(filters);

        assertFalse(conditionsPredicate.test(e));
    }
    @Test
    public void test004(){
        Event e = new Birthday(LocalDate.of(2020, Month.APRIL, 14),
                "Desc",
                "Person",
                "Gift");


        List<Filter<?>> filters =
                Collections.emptyList();

        ConditionsPredicate<Event> conditionsPredicate =
                (ConditionsPredicate<Event>) ExpressionsUtils.buildPredicate(filters);

        assertFalse(conditionsPredicate.test(e));
    }

    @Test
    public void test005(){
        Event e = new Birthday(LocalDate.of(2020, Month.APRIL, 14),
                "Desc",
                "Person",
                "Gift");


        List<Filter<?>> filters =
                Arrays.asList(
                        new Filter<>("day", OperatorType.Equal, 14, Integer.class, AttributeFilterType.Or),
                        new Filter<>("month", OperatorType.Equal, Month.APRIL.getValue(), Integer.class, AttributeFilterType.Or),
                        new Filter<>("year", OperatorType.Equal, 2019, Integer.class, AttributeFilterType.And)
                );

        ConditionsPredicate<Event> conditionsPredicate =
                (ConditionsPredicate<Event>) ExpressionsUtils.buildPredicate(filters);

        assertFalse(conditionsPredicate.test(e));
    }

    @Test
    public void test006(){
        Event e = new Birthday(LocalDate.of(2020, Month.APRIL, 14),
                "Desc",
                "Person",
                "Gift");


        List<Filter<?>> filters =
                Arrays.asList(
                        new Filter<>("day", OperatorType.Equal, 14, Integer.class, AttributeFilterType.Or),
                        new Filter<>("month", OperatorType.Equal, Month.APRIL.getValue(), Integer.class, AttributeFilterType.Or),
                        new Filter<>("year", OperatorType.Equal, 2020, Integer.class, AttributeFilterType.And),
                        new Filter<>("year", OperatorType.Equal, 2019, Integer.class, AttributeFilterType.And)
                );

        ConditionsPredicate<Event> conditionsPredicate =
                (ConditionsPredicate<Event>) ExpressionsUtils.buildPredicate(filters);

        assertFalse(conditionsPredicate.test(e));
    }

    @Test
    public void test007(){
        Event e = new Birthday(LocalDate.of(2020, Month.APRIL, 14),
                "Desc",
                "Person",
                "Gift");


        List<Filter<?>> filters =
                Arrays.asList(
                        new Filter<>("day", OperatorType.More, 10, Integer.class, AttributeFilterType.Or),
                        new Filter<>("month", OperatorType.Equal, Month.APRIL.getValue(), Integer.class, AttributeFilterType.Or),
                        new Filter<>("year", OperatorType.Equal, 2020, Integer.class, AttributeFilterType.And),
                        new Filter<>("day", OperatorType.Equal, 14, Integer.class, AttributeFilterType.And)
                );

        ConditionsPredicate<Event> conditionsPredicate =
                (ConditionsPredicate<Event>) ExpressionsUtils.buildPredicate(filters);

        assertTrue(conditionsPredicate.test(e));
    }



}