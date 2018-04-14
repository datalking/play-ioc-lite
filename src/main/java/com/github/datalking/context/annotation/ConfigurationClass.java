package com.github.datalking.context.annotation;

import com.github.datalking.annotation.meta.AnnotationMetadata;
import com.github.datalking.annotation.meta.StandardAnnotationMetadata;
import com.github.datalking.util.Assert;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 被@Configuration注解标记的类
 *
 * @author yaoo on 4/13/18
 */
public class ConfigurationClass {

    private final AnnotationMetadata metadata;

    private String beanName;

    private final Set<BeanMethod> beanMethods = new LinkedHashSet<>();

    // final Set<String> skippedBeanMethods = new HashSet<String>();


    public ConfigurationClass(AnnotationMetadata metadata, String beanName) {
        this.metadata = metadata;
        this.beanName = beanName;
    }

    public ConfigurationClass(Class<?> clazz, String beanName) {
        Assert.notNull(beanName, "Bean name must not be null");
        this.metadata = new StandardAnnotationMetadata(clazz);
        this.beanName = beanName;
    }

    public AnnotationMetadata getMetadata() {
        return this.metadata;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return this.beanName;
    }

    public void addBeanMethod(BeanMethod method) {
        this.beanMethods.add(method);
    }

    public Set<BeanMethod> getBeanMethods() {
        return this.beanMethods;
    }

    @Override
    public boolean equals(Object other) {
        return (this == other ||
                (other instanceof ConfigurationClass && getMetadata().getClassName().equals(((ConfigurationClass) other).getMetadata().getClassName())));
    }

    @Override
    public int hashCode() {
        return getMetadata().getClassName().hashCode();
    }


    @Override
    public String toString() {
        return "ConfClass: " + this.beanName + ", " + this.beanMethods;
    }

}
