package core.inverseofcontrol.boot;

import core.inverseofcontrol.interfaces.ProxyConfigurator;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Lazarev Yaroslav
 */


public class DeprecatedHandlerProxyConfigurator implements ProxyConfigurator {
    @Override
    public Object replaceWithProxyIsNeeded(Object t, Class implClass) {
        if (implClass.isAnnotationPresent(Deprecated.class)) {
            if (implClass.getInterfaces().length == 0) {
                Enhancer.create(implClass, (InvocationHandler) (proxy, method, args) -> getInvocationHandlerLogic(t, method, args));
            }
            return Proxy.newProxyInstance(implClass.getClassLoader(),
                    implClass.getInterfaces(), (proxy, method, args) -> getInvocationHandlerLogic(t, method, args));
        } else {
            return t;
        }
    }

    private Object getInvocationHandlerLogic(Object t, Method method, Object[] args) throws IllegalAccessException, InvocationTargetException {
        System.out.println("Detect call method '" + method.getName() + "' of deprecated class " + t.getClass());
        return method.invoke(t, args);
    }
}
