package com.github.datalking.beans.factory.config;

/**
 * @author yaoo on 4/4/18
 */
public interface SingletonBeanRegistry  {


    void registerSingleton(String beanName, Object singletonObject);
    Object getSingleton(String beanName);
    boolean containsSingleton(String beanName);
    String[] getSingletonNames();
    int getSingletonCount();

}
