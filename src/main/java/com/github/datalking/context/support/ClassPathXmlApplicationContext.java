package com.github.datalking.context.support;

import com.github.datalking.beans.factory.config.ConfigurableListableBeanFactory;
import com.github.datalking.beans.factory.support.AbstractBeanFactory;
import com.github.datalking.beans.factory.support.BeanDefinitionRegistry;
import com.github.datalking.beans.factory.support.DefaultListableBeanFactory;
import com.github.datalking.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 读取xml配置的ApplicationContext
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new DefaultListableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, DefaultListableBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    /**
     * 读取配置文件并注册bean
     * <p>
     * 默认采用立即初始化
     */
    @Override
    public void refresh() throws Exception {

        //读取xml配置并解析成BeanDefinition
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(((BeanDefinitionRegistry) getBeanFactory()));
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);

        finishBeanFactoryInitialization((ConfigurableListableBeanFactory) beanFactory);

    }


}
