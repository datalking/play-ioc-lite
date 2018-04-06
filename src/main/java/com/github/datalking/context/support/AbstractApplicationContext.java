package com.github.datalking.context.support;


import com.github.datalking.beans.factory.config.ConfigurableListableBeanFactory;
import com.github.datalking.beans.factory.support.DefaultListableBeanFactory;
import com.github.datalking.beans.factory.xml.XmlBeanDefinitionReader;
import com.github.datalking.context.ApplicationContext;
import com.github.datalking.beans.factory.support.AbstractBeanFactory;
import com.github.datalking.context.ConfigurableApplicationContext;
import com.github.datalking.io.ResourceLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * ApplicationContext 抽象类
 */
public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {

    protected AbstractBeanFactory beanFactory;

//    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<BeanFactoryPostProcessor>();
//    private long startupDate;


    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) throws Exception {
        //手动调用getBean()方法来触发实例化bean
        ((DefaultListableBeanFactory) getBeanFactory()).preInstantiateSingletons();
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }

    public AbstractBeanFactory getBeanFactory() {
        return beanFactory;
    }

}
