package annotations;

import core.annotations.PropertyGetter;
import core.annotations.PropertySetter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReflectionUtils {
    public static List<Method> getAllAnnotatedMethods(Class<?> targetClass, Class<? extends Annotation> aClass) {
        ArrayList<Method> methods = new ArrayList<>();

        Class<?> c = targetClass;

        while (c != null) {

            for (Method m : c.getDeclaredMethods()) {
                m.setAccessible(false);
                if (m.isAnnotationPresent(aClass))
                    methods.add(m);
            }

            c = c.getSuperclass();

        }

        return methods;

    }

    public static List<Method> getAllPropertyGetterAnnotatedWithValue(String value, Class<?> targetClass){
        return getAllAnnotatedMethods(targetClass, PropertyGetter.class)
                .stream().filter(method -> {
                    method.setAccessible(true);
                    return method.getAnnotation(PropertyGetter.class).value().equals(value);
                })
                .collect(Collectors.toList());
    }

    public static List<Method> getAllPropertySetterAnnotatedWithValue(String value, Class<?> targetClass){
        return getAllAnnotatedMethods(targetClass, PropertySetter.class)
                .stream().filter(method -> {
                    method.setAccessible(true);
                    return method.getAnnotation(PropertySetter.class).value().equals(value);
                })
                .collect(Collectors.toList());
    }
}
