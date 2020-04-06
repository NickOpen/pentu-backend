package com.pentu.common.anotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 用于Mark 与业务无关的Service
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Helper {
}
