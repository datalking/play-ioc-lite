package com.github.datalking.beans.factory.support;

import com.github.datalking.beans.factory.config.BeanDefinitionHolder;
import com.github.datalking.util.Assert;

/**
 * BeanDefinitionReader相关工具类
 *
 * @author yaoo on 4/4/18
 */
public class BeanDefinitionReaderUtils {

    /**
     * 创建BeanDefinition实例
     *
     * @param className 类名
     * @return BeanDefinition实例
     */
    public static AbstractBeanDefinition createBeanDefinition(String className, ClassLoader classLoader) throws ClassNotFoundException {

        Assert.notNull(className, "className cannot be null when createBeanDefinition()");
        GenericBeanDefinition bd = new GenericBeanDefinition();
        if (classLoader != null) {
            // classLoader不为空时，bean就是对象实例
            bd.setBeanClass(Class.forName(className));
        } else {
            // classLoader为空时，bean就是bean名字符串
            bd.setBeanClassName(className);
        }
        return bd;
    }

    /**
     * 将BeanDefinition注册到BeanDefinitionRegistry集合
     *
     * @param definitionHolder BeanDefinition的包装类实例
     * @param registry         BeanDefinition集合
     */
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
