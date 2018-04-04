package com.github.datalking.beans.factory.support;

import com.github.datalking.beans.MutablePropertyValues;
import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.io.Resource;

/**
 * BeanDefinition抽象类
 *
 * @author yaoo on 4/3/18
 */
//public abstract class AbstractBeanDefinition implements BeanDefinition, Cloneable {
public abstract class AbstractBeanDefinition implements BeanDefinition {

    private volatile Object beanClass;
    private MutablePropertyValues propertyValues;

//    private Resource resource;
//    private boolean abstractFlag = false;
//    private boolean lazyInit = false;

    protected AbstractBeanDefinition(BeanDefinition original) {
        setBeanClassName(original.getBeanClassName());
        setPropertyValues(new MutablePropertyValues(original.getPropertyValues()));

        // todo
        //		if (original instanceof AbstractBeanDefinition) {
    }

    public AbstractBeanDefinition() {

        setPropertyValues(null);
    }

    public Class<?> getBeanClass() throws IllegalStateException {
        Object beanClassObject = this.beanClass;
        if (beanClassObject == null) {
            throw new IllegalStateException("No bean class specified on bean definition");
        }
        if (!(beanClassObject instanceof Class)) {
            throw new IllegalStateException(
                    "Bean class name [" + beanClassObject + "] has not been resolved into an actual Class");
        }
        return (Class<?>) beanClassObject;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public String getBeanClassName() {
        Object beanClassObject = this.beanClass;
        if (beanClassObject instanceof Class) {
            return ((Class<?>) beanClassObject).getName();
        } else {
            return (String) beanClassObject;
        }
    }

    @Override
    public void setBeanClassName(String beanClassName) {
        this.beanClass = beanClassName;
    }

    public boolean hasBeanClass() {
        return (this.beanClass instanceof Class);
    }

    @Override
    public MutablePropertyValues getPropertyValues() {
        return this.propertyValues;
    }

    public void setPropertyValues(MutablePropertyValues propertyValues) {
        this.propertyValues = (propertyValues != null ? propertyValues : new MutablePropertyValues());
    }


}
