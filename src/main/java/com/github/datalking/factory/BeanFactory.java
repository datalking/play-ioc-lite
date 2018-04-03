package com.github.datalking.factory;

/**
 * bean的容器
 * <p>
 * 最基本的接口
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;

//    <T> T getBean(Class<T> requiredType) throws Exception;

//    boolean containsBean(String name);

//    String[] getAliases(String name);
//    Class<?> getType(String name) throws NoSuchBeanDefinitionException;


//    boolean isPrototype(String name) throws NoSuchBeanDefinitionException;
//    boolean isSingleton(String name) throws NoSuchBeanDefinitionException;


}
