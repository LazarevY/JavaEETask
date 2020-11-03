package data.property;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PropertyManager {

    private String propertyName;

    public PropertyManager(String propertyName) {
        this.propertyName = propertyName;
    }

    private static Method getGetter(Class<?> targetClass, String propertyName) {
        //TODO: revert impl
        return null;
    }

    private static Method getSetter(Class<?> targetClass, String propertyName) {
        //TODO: revert impl
        return null;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getValue(Object target) throws InvocationTargetException, IllegalAccessException {

        Method m = getGetter(target.getClass(), propertyName);
        m.setAccessible(true);
        return m.invoke(target);
    }

    public void setValue(Object target, Object value) {
        Method m = getSetter(target.getClass(), propertyName);
        m.setAccessible(true);
        try {
            m.invoke(target, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
