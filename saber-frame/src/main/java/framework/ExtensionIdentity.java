package framework;

import java.lang.annotation.*;

/**
 * @author tinglang
 * @date 2020/3/19.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) //can use in method only.
@Inherited
public @interface ExtensionIdentity {

    String code();

    String desc() default "";
}
