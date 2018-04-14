package com.github.datalking.beans.factory.support;

import com.github.datalking.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * BeanDefinition高级实现类
 *
 * @author yaoo on 4/3/18
 */
public class RootBeanDefinition extends AbstractBeanDefinition {


    volatile Class<?> resolvedTargetType;

    // volatile ResolvableType targetType;
    // volatile ResolvableType factoryMethodReturnType;
    // Object[] resolvedConstructorArguments;


    public RootBeanDefinition() {
        super();
    }

    public RootBeanDefinition(Class<?> beanClass) {
        super();
        setBeanClass(beanClass);
    }

    public RootBeanDefinition(String beanClassName) {
        setBeanClassName(beanClassName);
    }

    public RootBeanDefinition(BeanDefinition original) {
        super(original);
    }

    public RootBeanDefinition(RootBeanDefinition original) {
        super(original);
        this.resolvedTargetType = original.resolvedTargetType;
    }

    public Class<?> getResolvedTargetType() {
        return resolvedTargetType;
    }

    public void setResolvedTargetType(Class<?> resolvedTargetType) {
        this.resolvedTargetType = resolvedTargetType;
    }

    public boolean isFactoryMethod(Method candidate) {
        return (candidate != null && candidate.getName().equals(getFactoryMethodName()));
    }

    @Override
    public RootBeanDefinition cloneBeanDefinition() {
        return new RootBeanDefinition(this);
    }

    @Override
    public boolean equals(Object o) {
        return (this == o || (o instanceof RootBeanDefinition && super.equals(o)));
    }

    @Override
    public String toString() {
        return "RootBDef: " + super.toString();
    }


}
