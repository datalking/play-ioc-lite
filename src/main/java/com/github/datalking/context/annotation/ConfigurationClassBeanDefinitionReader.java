package com.github.datalking.context.annotation;

import com.github.datalking.annotation.meta.MethodMetadata;
import com.github.datalking.beans.factory.support.BeanDefinitionRegistry;
import com.github.datalking.beans.factory.support.ConfigurationClassBeanDefinition;

import java.util.Set;

/**
 * @author yaoo on 4/13/18
 */
public class ConfigurationClassBeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    public ConfigurationClassBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }


    public void loadBeanDefinitions(Set<ConfigurationClass> configurationModel) {

        for (ConfigurationClass configClass : configurationModel) {

            loadBeanDefinitionsForConfigurationClass(configClass);
        }


    }


    private void loadBeanDefinitionsForConfigurationClass(ConfigurationClass configClass) {

        for (BeanMethod beanMethod : configClass.getBeanMethods()) {

            // 查找带有@Bean注解的方法，并注册BeanDefinition
            loadBeanDefinitionsForBeanMethod(beanMethod);
        }

    }

    private void loadBeanDefinitionsForBeanMethod(BeanMethod beanMethod) {

        ConfigurationClass configClass = beanMethod.getConfigurationClass();

        // 获取方法元信息
        MethodMetadata metadata = beanMethod.getMetadata();
        String methodName = metadata.getMethodName();
        // todo beanName默认为方法名，要支持获取自定义bean名
        String beanName = methodName;

        ConfigurationClassBeanDefinition beanDef = new ConfigurationClassBeanDefinition(configClass, metadata);

        // 设置FactoryBean
        beanDef.setFactoryBeanName(configClass.getBeanName());
        beanDef.setFactoryMethodName(methodName);

        this.registry.registerBeanDefinition(beanName, beanDef);

    }


}
