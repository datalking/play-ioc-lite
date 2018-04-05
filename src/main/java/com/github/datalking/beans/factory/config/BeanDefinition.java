package com.github.datalking.beans.factory.config;

import com.github.datalking.beans.MutablePropertyValues;

/**
 * bean内容及属性元数据定义 根接口
 *
 * @author yaoo on 4/3/18
 */
public interface BeanDefinition {

    String getBeanClassName();

    void setBeanClassName(String beanClassName);

    MutablePropertyValues getPropertyValues();

//    boolean isLazyInit();
//    void setLazyInit(boolean lazyInit);
//
//
//    boolean isPrimary();
//    boolean isAutowireCandidate();
//    // bean是否是抽象类，若是，则不会创建实例
//    boolean isAbstract();


}
