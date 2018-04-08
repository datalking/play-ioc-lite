package com.github.datalking.beans.factory.xml;

import com.github.datalking.beans.factory.support.BeanDefinitionReader;
import org.w3c.dom.Document;

/**
 * BeanDefinition注册接口
 *
 * @author yaoo on 4/6/18
 */
public interface BeanDefinitionDocumentReader {

    void registerBeanDefinitions(Document doc, BeanDefinitionReader bdReader) throws Exception;

}
