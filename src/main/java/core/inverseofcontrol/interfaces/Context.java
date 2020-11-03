package core.inverseofcontrol.interfaces;

/**
 * @author Lazarev Yaroslav
 */
public interface Context {
    <T> T getObject(Class<T> type);
}
