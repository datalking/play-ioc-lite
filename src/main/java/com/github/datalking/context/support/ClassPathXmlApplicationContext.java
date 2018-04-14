package com.github.datalking.context.support;

import com.github.datalking.beans.factory.support.DefaultListableBeanFactory;

/**
 * 读取xml配置的ApplicationContext
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    //private Resource[] configResources;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, true, new DefaultListableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, DefaultListableBeanFactory beanFactory) throws Exception {
        this(configLocation, true, beanFactory);
    }


    public ClassPathXmlApplicationContext(String configLocation, boolean enableRefresh, DefaultListableBeanFactory beanFactory) throws Exception {

        super(configLocation, beanFactory);

        if (enableRefresh) {
            refresh();
        }

    }

//    protected Resource[] getConfigResources() {
//        return this.configResources;
//    }

}
