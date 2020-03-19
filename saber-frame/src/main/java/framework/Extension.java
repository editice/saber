package framework;

import java.lang.annotation.*;

/**
 * @author tinglang
 * @date 2020/3/19.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //can use in method only.
@Inherited
public @interface Extension {

    String bizCode() default "";

    String productCode() default "";

    int priority() default 0;
}
