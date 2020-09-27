package data;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Filter {
    private String attribute;
    private String operator;
    private Object value;
    private AttributeFilterType type;

    public Filter(){
        attribute = "null";
        operator = "!";
        value = 20;
        type = null;
    }

    public Filter(String attribute, String operator, Object value, AttributeFilterType type) {
        this.attribute = attribute;
        this.operator = operator;
        this.value = value;
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
    public String getOperator() {
        return operator;
    }

    @JsonSetter("operator")
    public void setOperator(String operator) {
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
}
