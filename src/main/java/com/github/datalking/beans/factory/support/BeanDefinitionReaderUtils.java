package com.github.datalking.beans.factory.support;

import com.github.datalking.beans.factory.config.BeanDefinitionHolder;

/**
 * BeanDefinitionReader相关工具类
 *
 * @author yaoo on 4/4/18
 */
public class BeanDefinitionReaderUtils {

    public static AbstractBeanDefinition createBeanDefinition(String parentName, String className) {
        GenericBeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClassName(className);
        return bd;
    }

    public static void registerBeanDefinition(BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry) {

        String beanName = definitionHolder.getBeanName();
        registry.registerBeanDefinition(beanName, definitionHolder.getBeanDefinition());

        // Register aliases for bean name, if any.
//        String[] aliases = definitionHolder.getAliases();
//        if (aliases != null) {
//            for (String alias : aliases) {
//                registry.registerAlias(beanName, alias);
//            }
//        }
    }


}
