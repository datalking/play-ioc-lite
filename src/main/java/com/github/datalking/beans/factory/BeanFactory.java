package com.github.datalking.beans.factory;

/**
 * BeanFactory 根接口
 */
public interface BeanFactory {

    //可以用别名查找
    Object getBean(String name) throws Exception;

    //类型可以是接口或者子类,但不能是null
//    <T> T getBean(Class<T> requiredType) throws Exception;
//    <T> T getBean(String name, Class<T> requiredType) throws Exception;

    //不管类是否抽象类,懒加载,是否在容器范围内,只要符合都返回true,所以这边true,不一定能从getBean获取实例
//    boolean containsBean(String name);

//    String[] getAliases(String name);
//    Class<?> getType(String name) throws NoSuchBeanDefinitionException;


//    boolean isPrototype(String name) throws NoSuchBeanDefinitionException;
//    boolean isSingleton(String name) throws NoSuchBeanDefinitionException;


}
