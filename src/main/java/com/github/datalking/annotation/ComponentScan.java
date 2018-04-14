package com.github.datalking.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注需要自动扫描的包，也会自动扫描该包的所有子包
 *
 * @author yaoo on 4/2/18
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Repeatable(ComponentScans.class)
public @interface ComponentScan {

    String[] value() default {};

    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};

    //Filter[] includeFilters() default {};
    // Filter[] excludeFilters() default {};
    // boolean lazyInit() default false;
    //Class<? extends BeanNameGenerator> nameGenerator() default BeanNameGenerator.class;

}
