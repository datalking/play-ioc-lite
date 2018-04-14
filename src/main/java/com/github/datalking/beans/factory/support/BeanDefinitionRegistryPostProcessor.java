package com.github.datalking.beans.factory.support;

import com.github.datalking.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author yaoo on 4/13/18
 */
public interface BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor {

    void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry);


}
