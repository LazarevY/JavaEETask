package core.inverseofcontrol.boot;

import core.inverseofcontrol.annotations.InjectByType;
import core.inverseofcontrol.interfaces.ObjectConfigurator;
import lombok.SneakyThrows;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author Lazarev Yaroslav
 */
public class InjectByTypeObjectConfigurator implements ObjectConfigurator {

    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        for (Field field : ReflectionUtils.getAllFields(t.getClass())) {
            if (field.isAnnotationPresent(InjectByType.class)) {
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                field.set(t, object);
            }
        }
    }
}
