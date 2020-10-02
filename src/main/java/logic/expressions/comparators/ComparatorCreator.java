package logic.expressions.comparators;

import logic.expressions.interfaces.SpecificComparator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;

public class ComparatorCreator {

    private HashMap<Class<?>, SpecificComparatorFactory<?>> map;

    private static ComparatorCreator instance = null;

    private ComparatorCreator(){
        map = new HashMap<Class<?>, SpecificComparatorFactory<?>>(){{
            put(Integer.class, new IntegerComparatorsFactory());
            put(String.class, new StringComparatorsFactory());
            put(LocalDate.class, new DataComparatorsFactory());
            put(LocalTime.class, new TimeComparatorsFactory());
        }};
    }

    public static ComparatorCreator getInstance(){
        if (instance == null)
            instance = new ComparatorCreator();
        return instance;
    }

    public <T> SpecificComparator<T> createComparator(OperatorType operation, Class<T> tClass){
        if (!map.containsKey(tClass))
            throw new IllegalArgumentException("Not implemented factory for class " + tClass.getName());
        return (SpecificComparator<T>) map.get(tClass).createForOperator(operation);
    }


}
