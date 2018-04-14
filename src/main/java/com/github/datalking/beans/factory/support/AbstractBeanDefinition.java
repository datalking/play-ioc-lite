package com.github.datalking.beans.factory.support;

import com.github.datalking.beans.MutablePropertyValues;
import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.io.Resource;

/**
 * BeanDefinition抽象类
 * beanName不在这里，在BeanDefinitionHolder
 *
 * @author yaoo on 4/3/18
 */
public abstract class AbstractBeanDefinition implements BeanDefinition, Cloneable {

    /**
     * 一般情况下，beanDefinitionReader阶段是字符串，createBean阶段是class对象
     */
    private volatile Object beanClass;

    private MutablePropertyValues propertyValues;

    // 懒加载默认false
    private boolean lazyInit = false;

    private String factoryBeanName;

    private String factoryMethodName;

//    private ConstructorArgumentValues constructorArgumentValues;
//    private int autowireMode = AUTOWIRE_NO;
//    private boolean autowireCandidate = true;
//    private String[] dependsOn;
//    private String initMethodName;
//    private String destroyMethodName;
//    private Resource resource;
//    private boolean abstractFlag = false;

    public AbstractBeanDefinition() {
        setPropertyValues(null);
    }

    protected AbstractBeanDefinition(BeanDefinition original) {
        setBeanClassName(original.getBeanClassName());
        setPropertyValues(new MutablePropertyValues(original.getPropertyValues()));
        setLazyInit(original.isLazyInit());
        setFactoryBeanName(original.getFactoryBeanName());
        setFactoryMethodName(original.getFactoryMethodName());

        // todo 拷贝其他字段
        // if (original instanceof AbstractBeanDefinition) {
        // setAutowireCandidate(original.isAutowireCandidate());

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

//    @Override
//    public void setDependsOn(String... dependsOn) {
//        this.dependsOn = dependsOn;
//    }
//
//
//    @Override
//    public String[] getDependsOn() {
//        return this.dependsOn;
//    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public String getFactoryMethodName() {
        return factoryMethodName;
    }

    public void setFactoryMethodName(String factoryMethodName) {
        this.factoryMethodName = factoryMethodName;
    }


    @Override
    public Object clone() {
        return cloneBeanDefinition();
    }

    public abstract AbstractBeanDefinition cloneBeanDefinition();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractBeanDefinition that = (AbstractBeanDefinition) o;

        if (!beanClass.equals(that.beanClass)) return false;
        // if (lazyInit != that.lazyInit) return false;
        if (factoryBeanName != null ? !factoryBeanName.equals(that.factoryBeanName) : that.factoryBeanName != null)
            return false;
        return factoryMethodName != null ? factoryMethodName.equals(that.factoryMethodName) : that.factoryMethodName == null;
    }

    @Override
    public int hashCode() {
        int result = beanClass.hashCode();
        // result = 31 * result + (lazyInit ? 1 : 0);
        result = 31 * result + (factoryBeanName != null ? factoryBeanName.hashCode() : 0);
        result = 31 * result + (factoryMethodName != null ? factoryMethodName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "beanClass=" + beanClass +
                ", factoryBeanName='" + factoryBeanName + '\'' +
                ", factoryMethodName='" + factoryMethodName + '\'' +
                ", lazyInit=" + lazyInit +
                '}';
    }
}
