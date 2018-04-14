package com.github.datalking.beans.factory.xml;

import com.github.datalking.beans.PropertyValue;
import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.beans.factory.config.BeanDefinitionHolder;
import com.github.datalking.beans.factory.config.RuntimeBeanReference;
import com.github.datalking.beans.factory.support.AbstractBeanDefinition;
import com.github.datalking.beans.factory.support.AbstractBeanDefinitionReader;
import com.github.datalking.beans.factory.support.BeanDefinitionReader;
import com.github.datalking.beans.factory.support.BeanDefinitionReaderUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 解析xml元素属性代理类
 *
 * @author yaoo on 4/6/18
 */
public class BeanDefinitionParserDelegate {


    private BeanDefinitionReader beanDefinitionReader;

    public static final String TRUE_VALUE = "true";
    public static final String FALSE_VALUE = "false";
    public static final String DEFAULT_VALUE = "default";

    public static final String BEAN_ELEMENT = "bean";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String SCOPE_ATTRIBUTE = "scope";
    private static final String SINGLETON_ATTRIBUTE = "singleton";
    public static final String LAZY_INIT_ATTRIBUTE = "lazy-init";
    public static final String AUTOWIRE_ATTRIBUTE = "autowire";
    public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";
    public static final String INIT_METHOD_ATTRIBUTE = "init-method";

    public static final String PROPERTY_ELEMENT = "property";
    public static final String REF_ATTRIBUTE = "ref";
    public static final String VALUE_ATTRIBUTE = "value";

    public static final String NULL_ELEMENT = "null";
    public static final String ARRAY_ELEMENT = "array";

    public BeanDefinitionParserDelegate(BeanDefinitionReader bdReader) {

        this.beanDefinitionReader = bdReader;
    }


    protected AbstractBeanDefinition createBeanDefinition(String className) throws ClassNotFoundException {

        return BeanDefinitionReaderUtils.createBeanDefinition(className, null);

    }


    public BeanDefinitionHolder parseBeanDefinitionElement(Element ele) throws Exception {

        String id = ele.getAttribute(ID_ATTRIBUTE);
        String nameAttr = ele.getAttribute(NAME_ATTRIBUTE);

        String beanName;

        /// 最终使用的beanName为id，若id不存在，则使用nameAttr，若name也不存在，则抛出异常
        if (id != null && id.trim().length() > 0) {

            beanName = id;

        } else if (nameAttr != null && nameAttr.trim().length() > 0) {

            beanName = nameAttr;

        } else {
            beanName = ((AbstractBeanDefinitionReader) this.beanDefinitionReader).generateBeanName((BeanDefinition) this.beanDefinitionReader);
        }

        AbstractBeanDefinition beanDefinition = parseBeanDefinitionElement(ele, beanName, null);

        //List<String> aliases = new ArrayList<String>();


        return new BeanDefinitionHolder(beanDefinition, beanName);
    }


    public AbstractBeanDefinition parseBeanDefinitionElement(Element ele, String beanName, BeanDefinition containingBean) throws Exception {


        String className = null;
        // 获取class属性
        if (ele.hasAttribute(CLASS_ATTRIBUTE)) {
            className = ele.getAttribute(CLASS_ATTRIBUTE).trim();
        }

        // 实例化beanDefinition
        AbstractBeanDefinition bd = createBeanDefinition(className);
        // 其他属性的解析
        parseBeanDefinitionAttributes(ele, beanName, containingBean, bd);

        // 构造函数设置
//        parseConstructorArgElements(ele, bd);
        // property信息
        parsePropertyElements(ele, bd);
        // qualifier信息
//        parseQualifierElements(ele, bd);
        return bd;

    }

    public AbstractBeanDefinition parseBeanDefinitionAttributes(Element ele,
                                                                String beanName,
                                                                BeanDefinition containingBean,
                                                                AbstractBeanDefinition bd) {

//        if (containingBean != null) {
//            bd.setScope(containingBean.getScope());
//        }

        String lazyInit = ele.getAttribute(LAZY_INIT_ATTRIBUTE);
        if (DEFAULT_VALUE.equals(lazyInit)) {
            lazyInit = FALSE_VALUE;
        }
        bd.setLazyInit(TRUE_VALUE.equals(lazyInit));

        return bd;


    }

    public void parsePropertyElements(Element beanEle, BeanDefinition bd) throws Exception {

        NodeList nl = beanEle.getChildNodes();

        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element && PROPERTY_ELEMENT.equals(node.getNodeName())) {
                parsePropertyElement((Element) node, bd);
            }
        }

    }

    public void parsePropertyElement(Element ele, BeanDefinition bd) throws Exception {
        String propertyName = ele.getAttribute(NAME_ATTRIBUTE);
        if (propertyName == null || propertyName.length() < 1) {
            throw new Exception("<property>元素必须配置name属性");
        }

//        this.parseState.push(new PropertyEntry(propertyName));
        try {
            if (bd.getPropertyValues().contains(propertyName)) {
                throw new Exception("Multiple 'property' definitions for property '" + propertyName + "' in Element " + ele);
            }
            Object val = parsePropertyValue(ele, bd, propertyName);
            PropertyValue pv = new PropertyValue(propertyName, val);
            //parseMetaElements(ele, pv);
            //pv.setSource(extractSource(ele));
            bd.getPropertyValues().addPropertyValue(pv);
        } finally {
//            this.parseState.pop();
        }

    }

    /**
     * 解析属性名对应的值
     * value和ref只能有1个
     */
    public Object parsePropertyValue(Element ele, BeanDefinition bd, String propertyName) throws Exception {

        boolean hasRefAttribute = ele.hasAttribute(REF_ATTRIBUTE);
        boolean hasValueAttribute = ele.hasAttribute(VALUE_ATTRIBUTE);

        if ((hasRefAttribute && hasValueAttribute)) {
            throw new Exception(ele.getTagName() + " is only allowed to contain either 'ref' attribute OR 'value' attribute ");
        }

        if ((!hasRefAttribute && !hasValueAttribute)) {
            throw new Exception(ele.getTagName() + " must contain either 'ref' attribute OR 'value' attribute ");
        }

        /// 如果是ref属性，返回RuntimeBeanReference对象
        if (hasRefAttribute) {
            String refName = ele.getAttribute(REF_ATTRIBUTE);
            if (refName == null || refName.trim().length() < 1) {
                throw new Exception(ele.getTagName() + " contains empty 'ref' attribute");
            }

            RuntimeBeanReference ref = new RuntimeBeanReference(refName);
            // 设置ref bean的依赖
            ref.setSource(bd.getBeanClassName());

            return ref;

        }
        /// 如果是value属性，转化成String
        else if (hasValueAttribute) {
            // spring使用的是 TypedStringValue
            String valueHolder = ele.getAttribute(VALUE_ATTRIBUTE);

            return valueHolder;
        }
        return null;
    }


}
