package com.github.datalking.beans.factory.config;

import com.github.datalking.beans.factory.BeanFactory;

/**
 * AutowireCapableBeanFactory 接口
 * <p>
 * 主要用于创建bean
 *
 * @author yaoo on 4/3/18
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    <T> T createBean(Class<T> beanClass) throws Exception;

//    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName);
//    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName);
//    void applyBeanPropertyValues(Object existingBean, String beanName);
//    Object autowire(Class<?> beanClass, int autowireMode, boolean dependencyCheck);
//    void autowireBean(Object existingBean) ;
//    void autowireBeanProperties(Object existingBean, int autowireMode, boolean dependencyCheck);
//    Object configureBean(Object existingBean, String beanName) ;
//
//
//    void destroyBean(Object existingBean);
//    Object initializeBean(Object existingBean, String beanName);
//    <T> NamedBeanHolder<T> resolveNamedBean(Class<T> requiredType);


}
