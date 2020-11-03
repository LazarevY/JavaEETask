package core.inverseofcontrol.boot;

import core.inverseofcontrol.interfaces.ObjectConfigurator;
import core.inverseofcontrol.interfaces.ObjectFactory;
import core.inverseofcontrol.interfaces.ProxyConfigurator;
import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lazarev Yaroslav
 */

public class ObjectFactoryImpl implements ObjectFactory {

    private final ApplicationContext context;
    private final List<ObjectConfigurator> configurators = new ArrayList<>();
    private final List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactoryImpl(ApplicationContext context) {
        this.context = context;
        for (Class<? extends ObjectConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }

        for (Class<? extends ProxyConfigurator> aClass : context.getConfig().getScanner().getSubTypesOf(ProxyConfigurator.class)) {
            proxyConfigurators.add(aClass.getDeclaredConstructor().newInstance());
        }


    }

    @Override
    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        T t = implClass.getDeclaredConstructor().newInstance();
        configure(t);
        invokeInitMethods(implClass, t);
        t = wrapWithProxyIsNeeded(implClass, t);

        return t;
    }

    private <T> T wrapWithProxyIsNeeded(Class<T> implClass, T t) {
        for (ProxyConfigurator proxyConfigurator : proxyConfigurators) {
            t = (T) proxyConfigurator.replaceWithProxyIsNeeded(t, implClass);
        }
        return t;
    }

    private <T> void configure(T t) {
        configurators.forEach(objectConfigurator -> objectConfigurator.configure(t, context));
    }

    private <T> void invokeInitMethods(Class<T> implClass, T t) throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.setAccessible(true);
                method.invoke(t);
            }
        }
    }
}
