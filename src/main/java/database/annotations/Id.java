package database.annotations;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Lazarev Yaroslav
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
    AutoGen autoGen() default AutoGen.TRUE;
}
