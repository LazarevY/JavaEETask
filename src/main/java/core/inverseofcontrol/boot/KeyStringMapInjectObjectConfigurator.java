package core.inverseofcontrol.boot;

import core.inverseofcontrol.annotations.InjectMapStringKeyByEntries;
import core.inverseofcontrol.annotations.MapStringKeyEntry;
import core.inverseofcontrol.interfaces.ObjectConfigurator;
import lombok.SneakyThrows;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class KeyStringMapInjectObjectConfigurator implements ObjectConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : ReflectionUtils.getAllFields(t.getClass())) {
            if (field.isAnnotationPresent(InjectMapStringKeyByEntries.class)) {

                field.setAccessible(true);

                InjectMapStringKeyByEntries a = field.getAnnotation(InjectMapStringKeyByEntries.class);

                Map<String, Object> map = new HashMap<>();

                for (MapStringKeyEntry mapEntry : a.value()) {
                    map.put(mapEntry.key(), context.getObject(mapEntry.implClass()));
                }

                field.set(t, map);
            }
        }
    }
}
