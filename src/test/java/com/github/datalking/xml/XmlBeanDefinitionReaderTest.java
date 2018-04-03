package com.github.datalking.xml;

import com.github.datalking.BeanDefinition;
import com.github.datalking.io.ResourceLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;


public class XmlBeanDefinitionReaderTest {

    @Test
    public void test() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());

        xmlBeanDefinitionReader.loadBeanDefinitions("beans.xml");

        Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        Assert.assertTrue(registry.size() > 0);
    }

}
