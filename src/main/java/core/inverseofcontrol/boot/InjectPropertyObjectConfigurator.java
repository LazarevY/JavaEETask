package core.inverseofcontrol.boot;

import core.inverseofcontrol.annotations.InjectProperty;
import core.inverseofcontrol.interfaces.ObjectConfigurator;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.reflections.ReflectionUtils.getAllFields;

/**
 * @author Lazarev Yaroslav
 */
public class InjectPropertyObjectConfigurator implements ObjectConfigurator {

    private final Map<String, String> propertiesMap;

    @SneakyThrows
    public InjectPropertyObjectConfigurator() {
        String path = Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                .getResource("applications.properties")).getPath();
        Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
        propertiesMap = lines.map(line -> line.split("="))
                .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
    }

    @Override
    @SneakyThrows
    public void configure(Object t, ApplicationContext context) {
        Class<?> implClass = t.getClass();
        Set<Field> allFields = getAllFields(implClass);
        for (Field field : allFields) {
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);


            if (annotation != null) {
                String value = annotation.value().isEmpty() ?
                        propertiesMap.get(field.getName()) : propertiesMap.get(annotation.value());
                field.setAccessible(true);
                field.set(t, value);
            }
        }
    }
}
