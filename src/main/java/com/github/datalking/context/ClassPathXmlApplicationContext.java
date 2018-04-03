package com.github.datalking.context;


import com.github.datalking.BeanDefinition;
import com.github.datalking.factory.AbstractBeanFactory;
import com.github.datalking.factory.AutowireCapableBeanFactory;
import com.github.datalking.io.ResourceLoader;
import com.github.datalking.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
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
