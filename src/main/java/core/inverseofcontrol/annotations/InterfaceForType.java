package core.inverseofcontrol.annotations;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface InterfaceForType {
    Class<?> value();
}



