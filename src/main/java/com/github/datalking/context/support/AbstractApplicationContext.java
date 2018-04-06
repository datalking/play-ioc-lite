package com.github.datalking.context.support;


import com.github.datalking.beans.factory.xml.XmlBeanDefinitionReader;
import com.github.datalking.context.ApplicationContext;
import com.github.datalking.beans.factory.support.AbstractBeanFactory;
import com.github.datalking.io.ResourceLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * ApplicationContext 抽象类
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    protected AbstractBeanFactory beanFactory;

//    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<BeanFactoryPostProcessor>();
//    private long startupDate;


    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws Exception {
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }


    public AbstractBeanFactory getBeanFactory() {
        return beanFactory;
    }

}
