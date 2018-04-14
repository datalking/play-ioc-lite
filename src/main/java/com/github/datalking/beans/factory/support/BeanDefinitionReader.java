package com.github.datalking.beans.factory.support;

import com.github.datalking.io.Resource;

/**
 * 读取配置文件并创建BeanDefinition 根接口
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    // 读取location配置文件信息，并创建BeanDefinition，然后注册到BeanDefinitionRegistry
    void loadBeanDefinitions(String location) throws Exception;

//    void loadBeanDefinitions(Resource resource) throws Exception;

//    BeanNameGenerator getBeanNameGenerator();


}
