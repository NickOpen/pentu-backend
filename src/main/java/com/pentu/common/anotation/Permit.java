package com.pentu.common.anotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Permit {

    /**
     * Returns the list of permission
     *
     * @return String[] The permission method attributes
     */
    String[] value();
}
