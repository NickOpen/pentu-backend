package com.pentu.common.anotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 用于Mark 处理数据的对象
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Manager {
}
