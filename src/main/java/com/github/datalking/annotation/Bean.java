package com.github.datalking.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注为会放入容器中的bean
 *
 * @author yaoo on 4/2/18
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bean {

    String[] value() default {};

    String[] name() default {};

//    Autowire autowire() default Autowire.NO;
//    String initMethod() default "";
//    String destroyMethod() default AbstractBeanDefinition.INFER_METHOD;


}
