package com.github.datalking.beans.factory.xml;

import com.github.datalking.beans.factory.config.BeanDefinitionHolder;
import com.github.datalking.beans.factory.support.AbstractBeanDefinitionReader;
import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.beans.factory.config.BeanReference;
import com.github.datalking.beans.PropertyValue;
import com.github.datalking.beans.factory.support.BeanDefinitionReaderUtils;
import com.github.datalking.beans.factory.support.BeanDefinitionRegistry;
import com.github.datalking.beans.factory.support.GenericBeanDefinition;
import com.github.datalking.io.Resource;
import com.github.datalking.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * 从xml文件中读取bean配置
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

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
        registerBeanDefinitions(doc);
        inputStream.close();
    }

    /**
     * 从xml文档对象中解析BeanDefinition，并注册到容器
     * <p>
     * 对应spring中的 DefaultBeanDefinitionDocumentReader 类
     *
     * @param doc xml文档对象
     */
    public void registerBeanDefinitions(Document doc) {
        //获取根元素
        Element root = doc.getDocumentElement();

        parseBeanDefinitions(root);
    }

    /**
     * 解析xml doc
     * todo 考虑处理bean，alias，import
     *
     * @param root 根节点
     */
    protected void parseBeanDefinitions(Element root) {

        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                //对应spring的parseDefaultElement()中的processBeanDefinition()
                processBeanDefinition(ele);
            }
        }
    }


    protected void processBeanDefinition(Element ele) {

        String name = ele.getAttribute("name");
        String className = ele.getAttribute("class");

        BeanDefinition beanDefinition = new GenericBeanDefinition();

        //为beanDefinition添加属性
        processProperty(ele, beanDefinition);

        beanDefinition.setBeanClassName(className);

        BeanDefinitionHolder bdHolder = new BeanDefinitionHolder(beanDefinition,name);
//        getRegistry().put(name, beanDefinition);

        BeanDefinitionReaderUtils.registerBeanDefinition(bdHolder, getRegistry());

    }

    //为beanDefinition解析并添加属性
    private void processProperty(Element ele, BeanDefinition beanDefinition) {

        NodeList propertyNode = ele.getElementsByTagName("property");

        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");

                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                } else {
                    String ref = propertyEle.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }


}
