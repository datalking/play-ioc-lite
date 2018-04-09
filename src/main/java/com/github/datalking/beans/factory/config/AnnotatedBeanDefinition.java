package com.github.datalking.beans.factory.config;


import com.github.datalking.annotation.meta.AnnotationMetadata;

/**
 * @author yaoo on 4/9/18
 */
public interface AnnotatedBeanDefinition extends BeanDefinition {

    AnnotationMetadata getMetadata();

}
