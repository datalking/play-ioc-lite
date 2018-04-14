package com.github.datalking.context.support;

import com.github.datalking.beans.factory.config.BeanFactoryPostProcessor;
import com.github.datalking.beans.factory.config.ConfigurableListableBeanFactory;
import com.github.datalking.beans.factory.support.BeanDefinitionRegistry;
import com.github.datalking.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import com.github.datalking.context.annotation.ConfigurationClassPostProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 执行beanFactoryPostProcessors的代理类
 *
 * @author yaoo on 4/13/18
 */
public class PostProcessorRegistrationDelegate {

    public static void invokeBeanFactoryPostProcessors(
            ConfigurableListableBeanFactory beanFactory,
            List<BeanFactoryPostProcessor> beanFactoryPostProcessors) {


        /// 如果beanFactory可注册BeanDefinition
        if (beanFactory instanceof BeanDefinitionRegistry) {

            BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
//          String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);

            List<BeanDefinitionRegistryPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
//        for (String ppName : postProcessorNames) {
//            priorityOrderedPostProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
//        }

            // 添加默认的后置处理器
            ConfigurationClassPostProcessor configurationClassPostProcessor = new ConfigurationClassPostProcessor();
            priorityOrderedPostProcessors.add(configurationClassPostProcessor);

            invokeBeanDefinitionRegistryPostProcessors(priorityOrderedPostProcessors, registry);

        }

        /// 如果beanFactory不可注册BeanDefinition
        else {
            invokeBeanFactoryPostProcessors(beanFactoryPostProcessors, beanFactory);
        }

    }


    private static void invokeBeanDefinitionRegistryPostProcessors(
            Collection<? extends BeanDefinitionRegistryPostProcessor> postProcessors,
            BeanDefinitionRegistry registry) {

        for (BeanDefinitionRegistryPostProcessor postProcessor : postProcessors) {
            postProcessor.postProcessBeanDefinitionRegistry(registry);
        }
    }


    private static void invokeBeanFactoryPostProcessors(
            Collection<? extends BeanFactoryPostProcessor> postProcessors,
            ConfigurableListableBeanFactory beanFactory) {

        for (BeanFactoryPostProcessor postProcessor : postProcessors) {
            postProcessor.postProcessBeanFactory(beanFactory);
        }

    }


}
