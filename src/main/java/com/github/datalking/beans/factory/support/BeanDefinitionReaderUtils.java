package com.github.datalking.beans.factory.support;

import com.github.datalking.beans.factory.config.AnnotatedBeanDefinition;
import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.beans.factory.config.BeanDefinitionHolder;
import com.github.datalking.util.Assert;

/**
 * BeanDefinitionReader相关工具类
 *
 * @author yaoo on 4/4/18
 */
public class BeanDefinitionReaderUtils {

    /**
     * bean名称中字母与计数的分隔符号
     */
    public static final String GENERATED_BEAN_NAME_SEPARATOR = "#";

    /**
     * 创建BeanDefinition实例
     *
     * @param className 类名
     * @return BeanDefinition实例
     */
    public static AbstractBeanDefinition createBeanDefinition(String className, ClassLoader classLoader) throws ClassNotFoundException {

        Assert.notNull(className, "className cannot be null when createBeanDefinition()");
        RootBeanDefinition bd = new RootBeanDefinition();
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

    /**
     * 默认bean名称生成器
     *
     * @param beanDefinition bd
     * @param registry       所有单例bean注册表
     * @return bean名称
     */
    public static String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry registry) {

        String generatedBeanName = beanDefinition.getBeanClassName();
        if (generatedBeanName == null || generatedBeanName.trim().length() == 0) {
            generatedBeanName = "$randomBeanName";
        }

        String id = generatedBeanName;

        int counter = 0;
        while (counter == 0 || registry.containsBeanDefinition(id)) {
            counter++;
            id = generatedBeanName + GENERATED_BEAN_NAME_SEPARATOR + counter;
        }

        return id;

    }

    public static String generateAnnotatedBeanName(AnnotatedBeanDefinition abd, BeanDefinitionRegistry registry) {
        String beanName = abd.getBeanClassName();

        int dotIdx = beanName.lastIndexOf(".");
        if (dotIdx != -1) {
            beanName = beanName.substring(dotIdx + 1);
        }

        // 首字母小写
        beanName = Character.toLowerCase(beanName.charAt(0)) + beanName.substring(1);
        return beanName;


    }
}
