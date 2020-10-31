package data;

import lombok.Getter;
import lombok.Setter;

public class Attribute {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private Object value;

    public Attribute(String name, Object value) {
        this.name = name;
        this.value = value;
    }

}
