package com.github.datalking.beans;

import com.github.datalking.bean.BeanAllStr;
import com.github.datalking.bean.DataAnalyst;
import com.github.datalking.bean.HelloService;
import com.github.datalking.bean.WorldService;
import com.github.datalking.beans.factory.support.AbstractBeanFactory;
import com.github.datalking.beans.factory.support.BeanDefinitionRegistry;
import com.github.datalking.beans.factory.support.DefaultListableBeanFactory;
import com.github.datalking.beans.factory.xml.XmlBeanDefinitionReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 直接使用BeanFactory作为容器的示例
 */
public class BeanFactoryTest {

    @Test
    public void testLazy() throws Exception {

        // 初始化BeanFactory并注册bean
        AbstractBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) beanFactory);
//        xmlBeanDefinitionReader.loadBeanDefinitions("beans-primitive.xml");
        xmlBeanDefinitionReader.loadBeanDefinitions("beans-circular.xml");


        ///立即初始化
//        ((DefaultListableBeanFactory) beanFactory).preInstantiateSingletons();

//        BeanAllStr beanAllStr = (BeanAllStr) beanFactory.getBean("beanAllStr");
//        System.out.println(beanAllStr);
//
//        DataAnalyst dataAnalyst = (DataAnalyst) beanFactory.getBean("dataAnalyst");
//        System.out.println(dataAnalyst);

        HelloService hService = (HelloService) beanFactory.getBean("helloService");
        WorldService wService = (WorldService) beanFactory.getBean("worldService");
        //hService.print();
        //wService.print();
        assertEquals("hello", hService.getText());
        assertEquals("world", wService.getText());


    }

    @Test
    public void testPreInstantiate() throws Exception {

        // 1.读取配置
//        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
//        xmlBeanDefinitionReader.loadBeanDefinitions("beans.xml");

        // 2.初始化BeanFactory并注册bean
//        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
//        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
//            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
//        }

        // 3.初始化bean
//        beanFactory.preInstantiateSingletons();

        // 4.获取bean
//        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
//        helloWorldService.helloWorld();
    }

}
