package com.leach.advanced.annotations.instance;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解 -- SqlTime
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlTime{
    String name() default "";
    String type() default "date";
    Constraint constraint() default @Constraint;
}
