package data.property;

import annotations.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PropertyManager {

    private String propertyName;

    public PropertyManager(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getValue(Object target) throws InvocationTargetException, IllegalAccessException {
        Method m =
                ReflectionUtils.getAllPropertyGetterAnnotatedWithValue(propertyName, target.getClass()).get(0);
        m.setAccessible(true);
        return m.invoke(target);
    }
}
