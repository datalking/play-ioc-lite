package com.github.datalking.context.annotation;

import com.github.datalking.annotation.Configuration;
import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.beans.factory.config.BeanDefinitionHolder;
import com.github.datalking.beans.factory.config.ConfigurableListableBeanFactory;
import com.github.datalking.beans.factory.support.AbstractAutowireCapableBeanFactory;
import com.github.datalking.beans.factory.support.AbstractBeanDefinition;
import com.github.datalking.beans.factory.support.BeanDefinitionRegistry;
import com.github.datalking.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import com.github.datalking.beans.factory.support.RootBeanDefinition;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 扫描@Configuration、@Bean、@ComponentScan执行类
 * 实现了BeanFactoryPostProcessor接口
 *
 * @author yaoo on 4/13/18
 */
public class ConfigurationClassPostProcessor implements BeanDefinitionRegistryPostProcessor {


    //private ConfigurationClassBeanDefinitionReader reader;


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {

        // 扫描@Configuration、@Bean、@ComponentScan
        processConfigBeanDefinitions(registry);

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {

        // 可以作增强处理
        // enhanceConfigurationClasses(beanFactory);

    }

    private void processConfigBeanDefinitions(BeanDefinitionRegistry registry) {

        String[] candidateNames = registry.getBeanDefinitionNames();

        // 保存所有带有注解@Configuration的对象
        Set<BeanDefinitionHolder> configCandidates = new LinkedHashSet<>();

        for (String beanName : candidateNames) {

            BeanDefinition beanDef = registry.getBeanDefinition(beanName);

            if (beanDef.getBeanClassName() == null) {
                return;
            }

            /// 加载类
            Class beanClass = null;
            beanClass = ((AbstractAutowireCapableBeanFactory) registry).doResolveBeanClass((AbstractBeanDefinition) beanDef);
            ((AbstractBeanDefinition) beanDef).setBeanClass(beanClass);

            // 获取所有被@Configuration标注的类 对应的BeanDefinition
            // spring的实现所设置的范围包括几乎所有可以表示@Bean的注解
            if (beanClass != null && beanClass.isAnnotationPresent(Configuration.class)) {
                configCandidates.add(new BeanDefinitionHolder(beanDef, beanName));
            }
        }

        // 若没有注解@Configuration标注，则直接返回
        if (configCandidates.isEmpty()) {
            return;
        }

        // todo configCandidates按照指定的@Order排序

        ConfigurationClassParser parser = new ConfigurationClassParser(registry);

        // 扫描@Bean、@ComponentScan、@Import
        parser.parse(configCandidates);

        Set<ConfigurationClass> configClasses = new LinkedHashSet<>(parser.getConfigurationClasses());

        ConfigurationClassBeanDefinitionReader reader = new ConfigurationClassBeanDefinitionReader(registry);

        // 将标注@Bean注解的bean注册到beanDefinitionMap
        reader.loadBeanDefinitions(configClasses);

    }


}
