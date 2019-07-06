package com.lyc.love.dagger2demo.dagger2.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Inject;
import javax.inject.Qualifier;

/**
    @作者 lyc
    create date: $time
    des:
**/

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
    String value()  default "";
}
