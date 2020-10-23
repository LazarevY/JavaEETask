package core;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Lazarev Yaroslav
 */

public class ObjectFactoryImpl implements ObjectFactory {


    private static ObjectFactoryImpl ourInstance = new ObjectFactoryImpl();
    private Config config;
    private List<ObjectConfigurator> configurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactoryImpl() {
        config = new JavaConfig("src/main/java", new HashMap<>());
        for (Class<? extends ObjectConfigurator> aClass : config.getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    public static ObjectFactoryImpl getInstance() {
        return ourInstance;
    }

    @Override
    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        T t = implClass.getDeclaredConstructor().newInstance();

        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t));

        return t;

    }
}
