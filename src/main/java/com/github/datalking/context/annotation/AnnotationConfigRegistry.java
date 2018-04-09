package com.github.datalking.context.annotation;

/**
 * 注解扫描与注册接口
 *
 * @author yaoo on 4/9/18
 */
public interface AnnotationConfigRegistry {

    void scan(String... basePackages);

    void register(Class<?>... annotatedClasses);

}
