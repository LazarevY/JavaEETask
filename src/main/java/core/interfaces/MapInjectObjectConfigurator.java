package core.interfaces;

import core.ApplicationContext;
import core.annotations.InjectByType;
import core.annotations.InjectMapByEntries;
import core.annotations.MapEntry;
import lombok.SneakyThrows;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MapInjectObjectConfigurator implements ObjectConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : ReflectionUtils.getAllFields(t.getClass())) {
            if (field.isAnnotationPresent(InjectMapByEntries.class)) {

                field.setAccessible(true);

                InjectMapByEntries a = field.getAnnotation(InjectMapByEntries.class);

                Map<String, Object> map = new HashMap<>();

                for (MapEntry mapEntry : a.value()) {
                    map.put(mapEntry.key(), context.getObject(mapEntry.implClass()));
                }

                field.set(t, map);
            }
        }
    }
}
