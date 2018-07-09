package com.system.util.database;

import java.lang.annotation.*;

/**
 * @Auther: 李景然
 * @Date: 2018/6/22 20:41
 * @Description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface DataSwitch {
    String dataSource() default "";
}
