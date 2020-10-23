package data;

import logic.expressions.comparators.OperatorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Filter<T> {
    @Getter @Setter private String attribute;
    @Getter @Setter private OperatorType operator;
    @Getter @Setter private T value;
    @Getter @Setter private Class<T> attributeClass;
    @Getter @Setter private AttributeFilterType type;

    public Filter(){
        attribute = "null";
        operator = null;
        value = null;
        attributeClass = null;
        type = null;
    }

    public Filter(String attribute,
            OperatorType operator,
            T value,
            AttributeFilterType type){
        this(attribute, operator, value, (Class<T>) value.getClass(), type);

    }

}
