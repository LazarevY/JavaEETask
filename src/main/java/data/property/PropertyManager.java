package data.property;

import annotations.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.List;

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
        List<Method> methods  =
                ReflectionUtils.getAllPropertyGetterAnnotatedWithValue(propertyName, target.getClass());
        if (methods.size() != 1){
            throw new IllegalArgumentException("Target method didn't founded or count of methods more that 1");
        }
        Method m = methods.get(0);
        m.setAccessible(true);
        return m.invoke(target);
    }
}
