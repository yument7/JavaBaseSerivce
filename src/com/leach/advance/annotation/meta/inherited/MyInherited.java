package com.leach.advance.annotation.meta.inherited;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyInherited {
    String name() default "inherited annotation";
}
