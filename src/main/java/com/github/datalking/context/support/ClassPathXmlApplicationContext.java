package com.github.datalking.context.support;


import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.beans.factory.support.AbstractBeanFactory;
import com.github.datalking.beans.factory.config.AutowireCapableBeanFactory;
import com.github.datalking.io.ResourceLoader;
import com.github.datalking.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * 读取xml配置的ApplicationContext
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    /**
     * 注册bean
     * <p>
     * 默认采用延迟初始化
     */
    @Override
    public void refresh() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
    }

}
