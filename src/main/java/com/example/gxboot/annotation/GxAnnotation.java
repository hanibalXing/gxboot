package com.example.gxboot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author gx
 * @ClassName: GxAnnotation
 * @Description: java类作用描述
 * @date 2019/5/18 0:42
 * @Version: 1.0
 * @since
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public  @interface GxAnnotation {
}
