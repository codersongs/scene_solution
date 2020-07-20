package com.codersongs.scenesolution.uniqId.annotation;

import com.codersongs.scenesolution.uniqId.enums.DataSourceEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceAnnotaion {
    DataSourceEnum name() default DataSourceEnum.DEFAULT;
}
