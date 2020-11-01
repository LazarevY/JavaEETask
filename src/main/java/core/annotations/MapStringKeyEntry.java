package core.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MapStringKeyEntry {
    String key();
    Class<?> implClass();
}
