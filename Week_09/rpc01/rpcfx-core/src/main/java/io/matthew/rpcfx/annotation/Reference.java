package io.matthew.rpcfx.annotation;

import java.lang.annotation.*;

/**
 * 用于AOP扫描的Bean的注解
 *
 * @author Matthew
 * @date 2021/3/20 16:31
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
public @interface Reference
{
	String version() default "";

	String group() default "";

	String url() default "";
}
