package com.github.datalking.beans.factory.support;

import com.github.datalking.beans.MutablePropertyValues;
import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.io.Resource;

/**
 * BeanDefinition抽象类
 * beanName不在这里，在BeanDefinitionHolder
 * todo 实现clone
 *
 * @author yaoo on 4/3/18
 */
public abstract class AbstractBeanDefinition implements BeanDefinition, Cloneable {

    // reader阶段是字符串，createBean阶段是class对象
    private volatile Object beanClass;

    private MutablePropertyValues propertyValues;

    // 懒加载默认false
    private boolean lazyInit = false;

    private String[] dependsOn;

//    private String initMethodName;
//    private Resource resource;
//    private boolean abstractFlag = false;

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

        // 类尚未加载，抛出异常
        if (!(beanClassObject instanceof Class)) {
            throw new IllegalStateException("Bean class name [" + beanClassObject + "] has not been resolved into an actual Class");
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

    @Override
    public boolean isLazyInit() {
        return this.lazyInit;
    }

    @Override
    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    @Override
    public void setDependsOn(String... dependsOn) {
        this.dependsOn = dependsOn;
    }

    /**
     * Return the bean names that this bean depends on.
     */
    @Override
    public String[] getDependsOn() {
        return this.dependsOn;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
