package com.github.datalking.beans.factory.config;

/**
 * BeanFactory后置处理器 用于修改BeanDefniition
 *
 * @author yaoo on 4/13/18
 */
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);


}
