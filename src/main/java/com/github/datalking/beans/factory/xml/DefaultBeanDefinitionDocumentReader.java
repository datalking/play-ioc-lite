package com.github.datalking.beans.factory.xml;

import com.github.datalking.beans.factory.config.BeanDefinitionHolder;
import com.github.datalking.beans.factory.support.BeanDefinitionReader;
import com.github.datalking.beans.factory.support.BeanDefinitionReaderUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 解析xml Document对象注册BeanDefinition 默认实现类
 *
 * @author yaoo on 4/6/18
 */
public class DefaultBeanDefinitionDocumentReader implements BeanDefinitionDocumentReader {

    private BeanDefinitionParserDelegate delegate;

    private BeanDefinitionReader beanDefinitionReader;


    //    protected BeanDefinitionParserDelegate createDelegate(XmlReaderContext readerContext, Element root, BeanDefinitionParserDelegate parentDelegate) {
    protected BeanDefinitionParserDelegate createDelegate(BeanDefinitionReader bdReader, Element root) {
        BeanDefinitionParserDelegate delegate = new BeanDefinitionParserDelegate(bdReader);
        return delegate;
    }

    @Override
    public void registerBeanDefinitions(Document doc, BeanDefinitionReader bdReader) throws Exception {

        this.beanDefinitionReader = bdReader;
        Element root = doc.getDocumentElement();
        doRegisterBeanDefinitions(root);

    }

    protected void doRegisterBeanDefinitions(Element root) throws Exception {

//        BeanDefinitionParserDelegate parent = this.delegate;
//        this.delegate = createDelegate(getReaderContext(), root, parent);

        // 初始化delegate
        this.delegate = createDelegate(this.beanDefinitionReader, root);

//        preProcessXml(root);

        // ==== 解析xml
        parseBeanDefinitions(root, this.delegate);

//        postProcessXml(root);

    }


    /**
     * 解析xml根节点的直接子节点
     * todo 不支持自定义标签，抛出异常
     *
     * @param root     根节点
     * @param delegate 解析代理
     */
    protected void parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate) throws Exception {

        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                parseDefaultElement(ele, delegate);
            }
        }
    }

    /**
     * 解析xml中bean的元素、属性
     * todo 处理import，alias
     */
    private void parseDefaultElement(Element ele, BeanDefinitionParserDelegate delegate) throws Exception {

//        if (delegate.nodeNameEquals(ele, IMPORT_ELEMENT)) {
//            importBeanDefinitionResource(ele);
//        }
//        else if (delegate.nodeNameEquals(ele, ALIAS_ELEMENT)) {
//            processAliasRegistration(ele);
//        }
//        else if (delegate.nodeNameEquals(ele, BEAN_ELEMENT)) {
        processBeanDefinition(ele, delegate);
//        }
//        else if (delegate.nodeNameEquals(ele, NESTED_BEANS_ELEMENT)) {
//            // recurse
//            doRegisterBeanDefinitions(ele);
//        }
    }

    /**
     * 解析xml元素，并注册到beanDefinitionMap
     *
     * @param ele      元素节点
     * @param delegate 解析器
     */
    private void processBeanDefinition(Element ele, BeanDefinitionParserDelegate delegate) throws Exception {

        // 解析属性并创建bean
        BeanDefinitionHolder bdHolder = delegate.parseBeanDefinitionElement(ele);

        if (bdHolder != null) {

            // 注册到beanDefinitionMap，引用类型字段注册的是字符串
            BeanDefinitionReaderUtils.registerBeanDefinition(bdHolder, beanDefinitionReader.getRegistry());

        }

    }


}

