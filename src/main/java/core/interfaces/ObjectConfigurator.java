package core.interfaces;

import core.ApplicationContext;

/**
 * @author Lazarev Yaroslav
 */
public interface ObjectConfigurator {
    void configure(Object t, ApplicationContext context);
}
