package com.github.datalking.context.support;


import com.github.datalking.beans.factory.config.ConfigurableListableBeanFactory;
import com.github.datalking.beans.factory.support.AbstractBeanFactory;
import com.github.datalking.beans.factory.support.DefaultListableBeanFactory;
import com.github.datalking.context.ConfigurableApplicationContext;

/**
 * ApplicationContext 抽象类
 */
public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {

    protected DefaultListableBeanFactory beanFactory;

//    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<BeanFactoryPostProcessor>();
//    private long startupDate;


    public AbstractApplicationContext() {
        this.beanFactory = new DefaultListableBeanFactory();

    }

    public AbstractApplicationContext(DefaultListableBeanFactory beanFactory) {
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

    public void refresh() throws Exception {
    }


}
