package com.github.datalking.beans.factory.support;

import com.github.datalking.annotation.meta.AnnotationMetadata;
import com.github.datalking.annotation.meta.StandardAnnotationMetadata;
import com.github.datalking.beans.factory.config.AnnotatedBeanDefinition;

/**
 * 存储注解元信息的 BeanDefinition
 *
 * @author yaoo on 4/9/18
 */
public class AnnotatedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {

    private final AnnotationMetadata metadata;

    public AnnotatedGenericBeanDefinition(Class<?> beanClass) {
        setBeanClassName(beanClass.getName());
        this.metadata = new StandardAnnotationMetadata(beanClass);
    }

    @Override
    public AnnotationMetadata getMetadata() {
        return metadata;
    }

    @Override
    public String toString() {
        return "AnnoGenericBDef: " + super.toString();
    }


}
