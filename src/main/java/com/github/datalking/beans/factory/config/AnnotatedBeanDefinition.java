package com.github.datalking.beans.factory.config;


import com.github.datalking.annotation.meta.AnnotationMetadata;

/**
 * 代表来源于注解的BeanDefiniiton 接口
 *
 * @author yaoo on 4/9/18
 */
public interface AnnotatedBeanDefinition extends BeanDefinition {

    AnnotationMetadata getMetadata();

}
