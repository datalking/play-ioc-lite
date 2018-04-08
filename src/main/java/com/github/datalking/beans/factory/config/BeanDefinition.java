package com.github.datalking.beans.factory.config;

import com.github.datalking.beans.MutablePropertyValues;

/**
 * bean属性元数据定义 根接口
 * 只定义了公共方法
 *
 * @author yaoo on 4/3/18
 */
public interface BeanDefinition {

    String getBeanClassName();

    void setBeanClassName(String beanClassName);

    MutablePropertyValues getPropertyValues();

    boolean isLazyInit();

    void setLazyInit(boolean lazyInit);

    void setDependsOn(String... dependsOn);

    String[] getDependsOn();

    boolean isSingleton();

//    boolean isPrimary();
//    boolean isPrototype();
//    boolean isAutowireCandidate();
//    // bean是否是抽象类，若是，则不会创建实例
//    boolean isAbstract();


}
