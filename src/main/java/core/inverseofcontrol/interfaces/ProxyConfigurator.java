package core.inverseofcontrol.interfaces;

/**
 * @author Lazarev Yaroslav
 */


public interface ProxyConfigurator {
    Object replaceWithProxyIsNeeded(Object t, Class implClass);
}
