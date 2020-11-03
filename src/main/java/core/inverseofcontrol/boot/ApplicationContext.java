package core.inverseofcontrol.boot;

import core.inverseofcontrol.annotations.Singleton;
import core.inverseofcontrol.interfaces.Config;
import core.inverseofcontrol.interfaces.Context;
import core.inverseofcontrol.interfaces.ObjectFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Lazarev Yaroslav
 */
public class ApplicationContext implements Context {

    @Setter
    private ObjectFactory factory;
    private final Map<Class, Object> cache = new ConcurrentHashMap<>();
    @Getter
    private final Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    @Override
    public <T> T getObject(Class<T> type) {
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }

        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        T t = factory.createObject(implClass);

        if (implClass.isAnnotationPresent(Singleton.class)) {
            cache.put(type, t);
        }

        return t;


    }
}
