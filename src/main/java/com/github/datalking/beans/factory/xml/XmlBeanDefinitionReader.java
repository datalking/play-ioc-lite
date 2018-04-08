package com.github.datalking.beans.factory.xml;

import com.github.datalking.beans.factory.config.BeanDefinitionHolder;
import com.github.datalking.beans.factory.support.AbstractBeanDefinitionReader;
import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.beans.factory.config.BeanReference;
import com.github.datalking.beans.PropertyValue;
import com.github.datalking.beans.factory.support.BeanDefinitionReader;
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

        //获取根元素
//        Element root = doc.getDocumentElement();
//        parseBeanDefinitions(root);

    }

//    /**
//     * 解析xml doc
//     * 只解析beans元素
//     * todo 考虑处理alias，import
//     *
//     * @param root 根节点
//     */
//    protected void parseBeanDefinitions(Element root) throws ClassNotFoundException {
//
//        NodeList nodeList = root.getChildNodes();
//        for (int i = 0, len = nodeList.getLength(); i < len; i++) {
//            Node node = nodeList.item(i);
//
//            // 解析Element类型的节点
//            if (node instanceof Element) {
//                Element ele = (Element) node;
//                //对应spring的 parseDefaultElement() 中的 processBeanDefinition()
//                processBeanDefinition(ele);
//            }
//        }
//    }
//
//
//    /**
//     * 解析并创建BeanDefinition，并添加属性信息，最后将BeanDefinition注册到beanDefinitionMap
//     *
//     * @param ele 节点
//     */
//    private void processBeanDefinition(Element ele) throws ClassNotFoundException {
//
//        // todo name/id可选
//        //对应于spring BeanDefinitionParserDelegate类的parseBeanDefinitionElement()
//        String name = ele.getAttribute("name");
//        // className示例 com.github.datalking.bean.BeanAllStr
//        String className = ele.getAttribute("class");
//
//        // 此时仅创建 BeanDefinition，不加载类
//        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) BeanDefinitionReaderUtils.createBeanDefinition(className, null);
//
//        //为beanDefinition添加属性
//        processProperty(ele, beanDefinition);
//
//        // 包装beanDefinition的名称和别名
//        BeanDefinitionHolder bdHolder = new BeanDefinitionHolder(beanDefinition, name);
//
//        // 对应于spring DefaultBeanDefinitionDocumentReader类的registerBeanDefinitions()方法
//        // 注册到beanDefinitionMap，引用类型字段注册的是字符串
//        BeanDefinitionReaderUtils.registerBeanDefinition(bdHolder, getRegistry());
//
//    }
//
//    /**
//     * 解析xml并为beanDefinition添加属性
//     */
//    private void processProperty(Element ele, BeanDefinition beanDefinition) {
//
//        NodeList propertyNode = ele.getElementsByTagName("property");
//
//        for (int i = 0; i < propertyNode.getLength(); i++) {
//            Node node = propertyNode.item(i);
//            if (node instanceof Element) {
//                Element propertyEle = (Element) node;
//                String name = propertyEle.getAttribute("name");
//                String value = propertyEle.getAttribute("value");
//
//                ///如果value非空
//                if (value != null && value.length() > 0) {
//                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
//
//                }
//                ///如果value为空，就检查ref属性
//                else {
//                    String ref = propertyEle.getAttribute("ref");
//                    if (ref == null || ref.length() == 0) {
//                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
//                                + name + "' must specify a ref or value");
//                    }
//
//                    BeanReference beanReference = new BeanReference(ref);
//                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
//
//                }
//
//            }
//        }
//    }
//

}
