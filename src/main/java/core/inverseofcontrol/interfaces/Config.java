package core.inverseofcontrol.interfaces;

/**
 * @author Lazarev Yaroslav
 */
public interface Config {
    <T> Class<? extends T> getImplClass(Class<T> ifc);

    org.reflections.Reflections getScanner();
}
