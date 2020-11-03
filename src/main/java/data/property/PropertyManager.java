package data.property;

import core.inverseofcontrol.annotations.PropertyGetter;
import core.inverseofcontrol.annotations.PropertySetter;
import org.reflections.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class PropertyManager {

    private String propertyName;

    public PropertyManager(String propertyName) {
        this.propertyName = propertyName;
    }

    private static Method getGetter(Class<?> targetClass, String propertyName) {
        List<Method> methods =
                new ArrayList<>(ReflectionUtils.getAllMethods(targetClass,
                        m -> m.isAnnotationPresent(PropertyGetter.class) &&
                                m.getAnnotation(PropertyGetter.class).value().equals(propertyName)));
        if (methods.size() != 1) {
            throw new IllegalArgumentException("Target method didn't founded or count of methods more that 1");
        }
        return methods.get(0);
    }

    private static Method getSetter(Class<?> targetClass, String propertyName) {
        List<Method> methods =
                new ArrayList<>(ReflectionUtils.getAllMethods(targetClass,
                        m -> m.isAnnotationPresent(PropertySetter.class) &&
                                m.getAnnotation(PropertySetter.class).value().equals(propertyName)));
        if (methods.size() != 1) {
            throw new IllegalArgumentException("Target method didn't founded or count of methods more that 1");
        }
        return methods.get(0);
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
