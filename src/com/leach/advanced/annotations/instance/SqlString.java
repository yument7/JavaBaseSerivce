package com.leach.advanced.annotations.instance;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 自定义注解 -- SqlNumber
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SqlString{
    String name() default "";
    String type() default "varchar2";
    int length() default 30;
    Constraint constraint() default @Constraint;
}
