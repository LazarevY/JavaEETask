package core;

import core.interfaces.ObjectFactory;

import java.util.Map;

/**
 * @author Lazarev Yaroslav
 */


public class Application {
    public static ApplicationContext run(String packageToScan, Map<Class, Class> ifc2ImplClass) {
        JavaConfig config = new JavaConfig(packageToScan, ifc2ImplClass);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactoryImpl(context);
        context.setFactory(objectFactory);
        return context;
    }
}
