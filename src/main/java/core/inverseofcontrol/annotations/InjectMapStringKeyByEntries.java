package core.inverseofcontrol.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface InjectMapStringKeyByEntries {
    MapStringKeyEntry[] value() default {};
}
