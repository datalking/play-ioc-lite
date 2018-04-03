package com.github.datalking.beans.factory.support;

/**
 * 从配置中读取BeanDefinitionReader
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
