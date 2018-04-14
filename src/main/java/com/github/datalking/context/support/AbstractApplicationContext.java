package com.github.datalking.context.support;


import com.github.datalking.beans.factory.config.BeanFactoryPostProcessor;
import com.github.datalking.beans.factory.config.ConfigurableListableBeanFactory;
import com.github.datalking.beans.factory.support.AbstractBeanFactory;
import com.github.datalking.beans.factory.support.BeanDefinitionRegistry;
import com.github.datalking.beans.factory.support.DefaultListableBeanFactory;
import com.github.datalking.beans.factory.xml.XmlBeanDefinitionReader;
import com.github.datalking.context.ConfigurableApplicationContext;
import com.github.datalking.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * ApplicationContext 抽象类
 */
public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {

    protected DefaultListableBeanFactory beanFactory;

    private String configLocation;

    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<>();
//    private long startupDate;

    public AbstractApplicationContext() {
        // 针对使用注解，不使用xml的情况
        this.configLocation = "";
        this.beanFactory = new DefaultListableBeanFactory();

    }

    public AbstractApplicationContext(String configLocation) {
        this.configLocation = configLocation;
        this.beanFactory = new DefaultListableBeanFactory();

    }

    public AbstractApplicationContext(String configLocation, DefaultListableBeanFactory beanFactory) {
        this.configLocation = configLocation;
        this.beanFactory = beanFactory;
    }

    public AbstractApplicationContext(DefaultListableBeanFactory beanFactory) {
        this.configLocation = "";
        this.beanFactory = beanFactory;
    }


    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }

    public AbstractBeanFactory getBeanFactory() {
        return beanFactory;
    }

    /**
     * 读取配置文件并注册bean
     * <p>
     * 默认采用立即初始化
     */
    @Override
    public void refresh() {

        try {
            // 读取xml配置文件
            obtainFreshBeanFactory();


            // 激活各种BeanFactoryPostProcessor，如扫描@Configuration、@Bean、@ComponentScan
            invokeBeanFactoryPostProcessors(beanFactory);

            // 注册拦截bean创建的bean处理器，这里只是注册，真正的调用是在getBean时候
            // 将各种Defnition转换成RootBeanDefinition
            //registerBeanPostProcessors(beanFactory);

            // 通过调用getBean()创建非懒加载而是需要立即实例化的bean
            finishBeanFactoryInitialization(beanFactory);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void obtainFreshBeanFactory() throws Exception {
        if (configLocation != null && !configLocation.trim().equals("")) {
            //读取xml配置并解析成BeanDefinition
            XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(((BeanDefinitionRegistry) getBeanFactory()));
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        }
    }

    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {

        // beanFactoryPostProcessors默认为空
        PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(beanFactory, getBeanFactoryPostProcessors());

    }

//    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
//        //PostProcessorRegistrationDelegate.registerBeanPostProcessors(beanFactory, this);
//    }

    private void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) throws Exception {

        //手动调用getBean()方法来触发实例化bean
        beanFactory.preInstantiateSingletons();
    }


    public List<BeanFactoryPostProcessor> getBeanFactoryPostProcessors() {
        return this.beanFactoryPostProcessors;
    }

    public void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor) {
        Assert.notNull(postProcessor, "BeanFactoryPostProcessor must not be null");
        this.beanFactoryPostProcessors.add(postProcessor);
    }

}
