package com.github.datalking.beans.factory.support;

import com.github.datalking.io.Resource;

/**
 * 读取BeanDefinition的根接口
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    //把“配置源”转化为多个BeanDefinition并注册到BeanDefinitionRegistry中
    void loadBeanDefinitions(String location) throws Exception;

//    void loadBeanDefinitions(Resource resource) throws Exception;

//    BeanNameGenerator getBeanNameGenerator();


}
