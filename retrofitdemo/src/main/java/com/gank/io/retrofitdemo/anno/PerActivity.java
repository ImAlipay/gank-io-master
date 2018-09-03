package com.gank.io.retrofitdemo.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * $ClassName$:
 *
 * @author zpl
 * @date $date$
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
