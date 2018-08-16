package com.rich.rest.aop;

import java.lang.annotation.*;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAction {

	String value() default "";

	boolean enabled() default true;
}
