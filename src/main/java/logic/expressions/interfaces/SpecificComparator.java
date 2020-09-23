package logic.expressions.interfaces;

public interface SpecificComparator<Type> {
    boolean compare(Type valueLeft, Type valueRight);
}
