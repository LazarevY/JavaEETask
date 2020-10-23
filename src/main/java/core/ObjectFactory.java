package core;

/**
 * @author LazareV Yaroslav
 */

public interface ObjectFactory {

    <T> T createObject(Class<T> type);

}
