package core.interfaces;

/**
 * @author Lazarev Yaroslav
 */


public interface ProxyConfigurator {
    Object replaceWithProxyIsNeeded(Object t, Class implClass);
}
