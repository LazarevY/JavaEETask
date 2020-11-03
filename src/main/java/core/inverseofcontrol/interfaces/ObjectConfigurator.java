package core.inverseofcontrol.interfaces;

import core.inverseofcontrol.boot.ApplicationContext;

/**
 * @author Lazarev Yaroslav
 */
public interface ObjectConfigurator {
    void configure(Object t, ApplicationContext context);
}
