package com.github.datalking.context.annotation;

import com.github.datalking.beans.factory.config.AnnotatedBeanDefinition;
import com.github.datalking.beans.factory.config.BeanDefinitionHolder;
import com.github.datalking.beans.factory.support.AnnotatedGenericBeanDefinition;
import com.github.datalking.beans.factory.support.BeanDefinitionReaderUtils;
import com.github.datalking.beans.factory.support.BeanDefinitionRegistry;

/**
 * @author yaoo on 4/9/18
 */
public class AnnotatedBeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    public AnnotatedBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }



    public void register(Class<?>... annotatedClasses) {
        for (Class<?> annotatedClass : annotatedClasses) {
            registerBean(annotatedClass);
        }
    }


    public void registerBean(Class<?> annotatedClass) {

        AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(annotatedClass);

//        AnnotationConfigUtils.processCommonDefinitionAnnotations(abd);

        String beanName = BeanDefinitionReaderUtils.generateAnnotatedBeanName(abd, this.registry);

        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd, beanName);
        BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, this.registry);

    }

    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }


}
