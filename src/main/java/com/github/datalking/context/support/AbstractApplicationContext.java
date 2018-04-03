package com.github.datalking.context.support;


import com.github.datalking.context.ApplicationContext;
import com.github.datalking.beans.factory.support.AbstractBeanFactory;

/**
 * ApplicationContexto抽象类
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws Exception {
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }


}
