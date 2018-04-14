package com.github.datalking.beans.factory.xml;

import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.beans.factory.xml.XmlBeanDefinitionReader;
import com.github.datalking.io.ResourceLoader;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class XmlBeanDefinitionReaderTest {


    @Test
    public void test() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());

        xmlBeanDefinitionReader.loadBeanDefinitions("beans.xml");

        String[] registry = xmlBeanDefinitionReader.getRegistry().getBeanDefinitionNames();
        Assert.assertTrue(registry.length > 0);

    }

    /**
     * 测试java自带的基于dom的xml读取解析方法
     */
    @Test
    public void testXMLDOM() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(this.getClass().getClassLoader().getResource("beans-primitive.xml").openConnection().getInputStream());
        Element rootElement = doc.getDocumentElement();
        NodeList children = rootElement.getChildNodes();
        Node current = null;
        int count = children.getLength();
        //System.out.println("根节点的child个数是：" + count);

        assertEquals("5", count+"");

//        for (int i = 0; i < count; i++) {
//            current = children.item(i);
//            System.out.println("====" + i + " :" + current.getNodeType() + ", " + current.getNodeName() + ", " + current.getNodeValue());

//            if (current.getNodeType() == Node.ELEMENT_NODE) {
//                Element element = (Element) current;
////                if (element.getTagName().equalsIgnoreCase("tableOfContents")) {
////                    element.setAttribute("showPageNumbers", "no");
////                }
////                System.out.println(element.getTagName());
//            }

//        }

//        System.out.println(doc.getDocumentElement());
    }



}
