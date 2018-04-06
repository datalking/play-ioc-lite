package com.github.datalking.beans.factory.support;


import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * 从配置中读取BeanDefinition 抽象类
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

//    private Map<String, BeanDefinition> registry;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.registry = registry;

    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
        this.resourceLoader = new ResourceLoader();
    }

    @Deprecated
    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new DefaultListableBeanFactory();
        this.resourceLoader = new ResourceLoader();
    }


    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    public String generateBeanName(BeanDefinition bd) {
        return BeanDefinitionReaderUtils.generateBeanName(bd, this.registry);
    }


}
