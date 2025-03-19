package com.zeddic.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Reserved {
    /**
     * 保留原因或未来使用计划说明（默认空字符串）
     */
    String reason() default "";
}
