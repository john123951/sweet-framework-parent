package sweet.framework.interceptor.annotation;

import java.lang.annotation.*;

/**
 * Created by sweet on 1/1/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,})
@Documented
@Inherited
public @interface Trace {
}
