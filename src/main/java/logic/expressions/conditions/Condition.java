package logic.expressions.conditions;

import data.property.PropertyManager;
import logic.expressions.interfaces.SpecificComparator;

import java.lang.reflect.InvocationTargetException;

public class Condition<TargetType, ValueType> {
    private final SpecificComparator<ValueType> comparator;
    private final PropertyManager propertyManager;
    private final ValueType value;

    public Condition(SpecificComparator<ValueType> comparator, PropertyManager propertyManager, ValueType value) {
        this.comparator = comparator;
        this.propertyManager = propertyManager;
        this.value = value;
    }

    public boolean check(Object instance) {
        try {
            return comparator.compare((ValueType) propertyManager.getValue(instance), value);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            System.err.println("Wrong parameter type");
            e.printStackTrace();
        }
        return false;
    }
}
