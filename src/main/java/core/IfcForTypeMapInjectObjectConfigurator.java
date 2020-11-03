package core;

import core.inverseofcontrol.annotations.InterfaceForType;
import core.inverseofcontrol.annotations.MapTypeIfcForType;
import core.inverseofcontrol.boot.ApplicationContext;
import core.inverseofcontrol.interfaces.ObjectConfigurator;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.reflections.ReflectionUtils.getAllFields;

public class IfcForTypeMapInjectObjectConfigurator implements ObjectConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : getAllFields(t.getClass(), f -> f.isAnnotationPresent(MapTypeIfcForType.class))) {
            field.setAccessible(true);

            if (!field.getType().isAssignableFrom(Map.class)) {
                throw new Exception("Illegal use annotation " + MapTypeIfcForType.class + ": field " +
                        field.getName() + " is not Map!");
            }

            MapTypeIfcForType a = field.getAnnotation(MapTypeIfcForType.class);

            Map<Class, Object> map = new HashMap<>();
            Set<Class<?>> subTypesOf = context.getConfig().getScanner().getSubTypesOf(a.value())
                    .stream()
                    .filter(aClass -> aClass.isAnnotationPresent(InterfaceForType.class))
                    .collect(Collectors.toSet());

            for (Class<?> aClass : subTypesOf
                    .stream()
                    .filter(aClass -> aClass.isAnnotationPresent(InterfaceForType.class))
                    .filter(aClass -> !aClass.isInterface())
                    .collect(Collectors.toList())) {

                InterfaceForType interfaceForType = aClass.getAnnotation(InterfaceForType.class);
                map.put(interfaceForType.value(), context.getObject(aClass));

            }

            field.set(t, map);

        }
    }
}
