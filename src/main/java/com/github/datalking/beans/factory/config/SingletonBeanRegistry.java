package com.github.datalking.beans.factory.config;

/**
 * 单例bean集合接口
 *
 * @author yaoo on 4/4/18
 */
public interface SingletonBeanRegistry {


    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName) throws Exception;

    boolean containsSingleton(String beanName);

    String[] getSingletonNames();

    int getSingletonCount();

}
