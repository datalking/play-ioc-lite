package com.github.datalking.beans.factory;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * BeanFactory子接口 迭代操作
 *
 * @author yaoo on 4/3/18
 */
public interface ListableBeanFactory extends BeanFactory {

    boolean containsBeanDefinition(String beanName);

    int getBeanDefinitionCount();

    String[] getBeanDefinitionNames();

//    String[] getBeanNamesForType(Class<?> type);

//    <T> Map<String, T> getBeansOfType(Class<T> type);


//    String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType);
//    <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType);

}
