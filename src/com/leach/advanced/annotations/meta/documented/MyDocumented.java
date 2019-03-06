package com.leach.advanced.annotations.meta.documented;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface MyDocumented{
    String value() default "这是@Documented注解为文档添加的注释";
}
