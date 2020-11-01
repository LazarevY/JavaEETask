package core;

import core.ApplicationContext;
import core.annotations.InjectMapClassKeyByEntries;
import core.annotations.InjectMapStringKeyByEntries;
import core.annotations.MapStringKeyEntry;
import core.interfaces.ObjectConfigurator;
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

                if (!field.getType().isAssignableFrom(Map.class)){
                    throw new Exception("Illegal use annotation " + InjectMapStringKeyByEntries.class + ": field " +
                            field.getName() +" is not Map!");
                }

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
