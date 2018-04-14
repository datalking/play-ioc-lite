package com.github.datalking.beans.factory.support;

import com.github.datalking.annotation.Bean;
import com.github.datalking.annotation.meta.AnnotationMetadata;
import com.github.datalking.annotation.meta.MethodMetadata;
import com.github.datalking.beans.factory.config.AnnotatedBeanDefinition;
import com.github.datalking.context.annotation.ConfigurationClass;

import java.lang.reflect.Method;

/**
 * 由@Configuration、@Bean注解扫描到的方法生成的BeanDefinition
 * 与 {@link AnnotatedGenericBeanDefinition}类似
 *
 * @author yaoo on 4/11/18
 */
public class ConfigurationClassBeanDefinition extends RootBeanDefinition implements AnnotatedBeanDefinition {

    private final AnnotationMetadata annotationMetadata;

    private final MethodMetadata factoryMethodMetadata;


    public ConfigurationClassBeanDefinition(ConfigurationClass configClass, MethodMetadata beanMethodMetadata) {
        super();
        this.annotationMetadata = configClass.getMetadata();
        this.factoryMethodMetadata = beanMethodMetadata;
    }

    public ConfigurationClassBeanDefinition(RootBeanDefinition original, ConfigurationClass configClass, MethodMetadata beanMethodMetadata) {
        super(original);
        this.annotationMetadata = configClass.getMetadata();
        this.factoryMethodMetadata = beanMethodMetadata;
    }

    private ConfigurationClassBeanDefinition(ConfigurationClassBeanDefinition original) {
        super(original);
        this.annotationMetadata = original.annotationMetadata;
        this.factoryMethodMetadata = original.factoryMethodMetadata;
    }

    @Override
    public boolean isFactoryMethod(Method candidate) {
        // 检查方法是否有@Bean注解
        return (super.isFactoryMethod(candidate) && candidate.isAnnotationPresent(Bean.class));
    }


    @Override
    public AnnotationMetadata getMetadata() {
        return null;
    }

    public MethodMetadata getFactoryMethodMetadata() {
        return this.factoryMethodMetadata;
    }


    @Override
    public ConfigurationClassBeanDefinition cloneBeanDefinition() {
        return new ConfigurationClassBeanDefinition(this);
    }

    @Override
    public boolean equals(Object o) {
        return (this == o || (o instanceof ConfigurationClassBeanDefinition && super.equals(o)));
    }

    @Override
    public String toString() {
        return "ConfBDef: " + super.toString();
    }


}
