package data;

import data.property.PropertyManager;

import java.util.List;

public class AttributeApplier {

    private List<Attribute> attributes;

    public AttributeApplier(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public void applyFor(Object o) {
        for (Attribute attribute : attributes)
            new PropertyManager(attribute.getName()).setValue(o, attribute.getValue());
    }
}
