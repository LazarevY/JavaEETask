package core.inverseofcontrol.boot;

import core.inverseofcontrol.interfaces.Config;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

/**
 * @author Lazarev Yaroslav
 */
public class JavaConfig implements Config {

    @Getter
    private final Reflections scanner;
    private final Map<Class, Class> ifc2ImplClass;

    public JavaConfig(String packageToScan, Map<Class, Class> ifc2ImplClass) {
        scanner = new Reflections(packageToScan);
        this.ifc2ImplClass = ifc2ImplClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        return ifc2ImplClass.computeIfAbsent(ifc, aClass -> {

            Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);

            if (classes.size() != 1)
                throw new RuntimeException(ifc + " has 0 or more than one impl, update config");
            return classes.iterator().next();
        });

    }
}
