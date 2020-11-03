package core.inverseofcontrol.boot;

import core.inverseofcontrol.annotations.InjectMapClassKeyByEntries;
import core.inverseofcontrol.annotations.MapKeyClassEntry;
import core.inverseofcontrol.interfaces.ObjectConfigurator;
import lombok.SneakyThrows;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class KeyClassMapInjectObjectConfigurator implements ObjectConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : ReflectionUtils.getAllFields(t.getClass(), field -> field.isAnnotationPresent(InjectMapClassKeyByEntries.class))) {

            field.setAccessible(true);

            InjectMapClassKeyByEntries a = field.getAnnotation(InjectMapClassKeyByEntries.class);

            Map<Class<?>, Object> map = new HashMap<>();

            for (MapKeyClassEntry mapEntry : a.value()) {
                map.put(mapEntry.key(), context.getObject(mapEntry.implClass()));
            }

            field.set(t, map);

        }
    }
}
