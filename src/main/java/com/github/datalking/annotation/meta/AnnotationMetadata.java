package com.github.datalking.annotation.meta;

import java.util.Map;
import java.util.Set;

/**
 * 注解元数据
 *
 * @author yaoo on 4/9/18
 */
public interface AnnotationMetadata extends ClassMetadata {


    Set<String> getAnnotationTypes();

    boolean hasAnnotation(String annotationName);

    Set<MethodMetadata> getAnnotatedMethods(String annotationName);

    Set<MethodMetadata> getAnnotatedMethods(Class<?> clazz);

    Map<String, Object> getAnnotationAttributes(Class<?> annotationName, boolean classValuesAsString);


//    boolean hasAnnotatedMethods(String annotationName);


}
