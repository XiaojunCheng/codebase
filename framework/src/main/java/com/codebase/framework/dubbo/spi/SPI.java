package com.codebase.framework.dubbo.spi;

import java.lang.annotation.*;

/**
 * Created by chengxiaojun on 17/2/6.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SPI {

    /**
     * 缺省扩展点名。
     */
    String value() default "";

}
