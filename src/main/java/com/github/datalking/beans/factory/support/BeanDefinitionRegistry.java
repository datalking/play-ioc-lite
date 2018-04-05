
package com.github.datalking.beans.factory.support;


import com.github.datalking.beans.factory.config.BeanDefinition;

/**
 * BeanDefinition集合 操作的接口
 * <p>
 * 一次只能注册一个BeanDefinition，且只能自己构造BeanDefinition类来注册
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

//    void removeBeanDefinition(String beanName);

    BeanDefinition getBeanDefinition(String beanName);

    boolean containsBeanDefinition(String beanName);


    String[] getBeanDefinitionNames();

//    int getBeanDefinitionCount();
//    boolean isBeanNameInUse(String beanName);

}
