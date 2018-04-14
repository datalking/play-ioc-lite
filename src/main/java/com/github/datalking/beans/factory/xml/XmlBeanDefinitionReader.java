package com.github.datalking.beans.factory.xml;

import com.github.datalking.beans.factory.support.AbstractBeanDefinitionReader;
import com.github.datalking.beans.factory.support.BeanDefinitionReader;
import com.github.datalking.beans.factory.support.BeanDefinitionRegistry;
import com.github.datalking.io.Resource;
import com.github.datalking.io.ResourceLoader;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * 从xml文件中读取BeanDefinition
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    //仅用于单元测试
    @Deprecated
    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    // 加载资源，将inputStream传递给doLoadBeanDefinitions()
    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    public void loadBeanDefinitions(Resource resource) throws Exception {
        InputStream inputStream = resource.getInputStream();
        doLoadBeanDefinitions(inputStream);
    }


    /**
     * 从xml中读取 BeanDefinition
     * 创建xml Document对象，调用registerBeanDefinitions()
     *
     * @param inputStream xml输入流
     */
    private void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(inputStream);
        // 解析并注册bean
        registerBeanDefinitions(doc, this);
        inputStream.close();
    }

    /**
     * 从xml文档对象中解析BeanDefinition，并注册到容器
     * <p>
     * 对应spring中的 DefaultBeanDefinitionDocumentReader 类
     *
     * @param doc xml文档对象
     */
    public void registerBeanDefinitions(Document doc, BeanDefinitionReader bdReader) throws Exception {

        BeanDefinitionDocumentReader documentReader = new DefaultBeanDefinitionDocumentReader();
        documentReader.registerBeanDefinitions(doc, bdReader);


    }


}
