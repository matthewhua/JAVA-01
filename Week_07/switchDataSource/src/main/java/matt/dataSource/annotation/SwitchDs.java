package matt.dataSource.annotation;

import java.lang.annotation.*;

/**
 * @author Matthew
 * @date 2021-03-06 20:43
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Documented
public @interface SwitchDs {

    String value() default "";


    /**
     * readOnly = true,并且value为空的情况下会从从库里面进行选择。
     * @return
     */
    boolean readOnly() default false;

}
