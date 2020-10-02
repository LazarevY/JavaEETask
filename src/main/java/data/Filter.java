package data;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import logic.expressions.comparators.OperatorType;

public class Filter<T> {
    private String attribute;
    private OperatorType operator;
    private Object value;
    private Class<T> attributeClass;
    private AttributeFilterType type;

    public Filter(){
        attribute = "null";
        operator = null;
        value = 20;
        attributeClass = null;
        type = null;
    }

    public Filter(String attribute, OperatorType operator, Object value, Class<T> attributeClass, AttributeFilterType type) {
        this.attribute = attribute;
        this.operator = operator;
        this.value = value;
        this.attributeClass = attributeClass;
        this.type = type;
    }

    @JsonGetter("attribute")
    public String getAttribute() {
        return attribute;
    }

    @JsonSetter("attribute")
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @JsonGetter("operator")
    public OperatorType getOperator() {
        return operator;
    }

    @JsonSetter("operator")
    public void setOperator(OperatorType operator) {
        this.operator = operator;
    }

    @JsonGetter("value")
    public Object getValue() {
        return value;
    }

    @JsonSetter("value")
    public void setValue(Object value) {
        this.value = value;
    }

    @JsonGetter("type")
    public AttributeFilterType getType() {
        return type;
    }

    @JsonSetter("type")
    public void setType(AttributeFilterType type) {
        this.type = type;
    }

    public Class<T> getAttributeClass() {
        return attributeClass;
    }

    public void setAttributeClass(Class<T> attributeClass) {
        this.attributeClass = attributeClass;
    }
}
