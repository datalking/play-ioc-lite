package com.github.datalking.annotation.meta;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 注解元数据 默认实现类
 *
 * @author yaoo on 4/9/18
 */
public class StandardAnnotationMetadata implements AnnotationMetadata {

    private final Class<?> introspectedClass;
    private final Annotation[] annotations;


    public StandardAnnotationMetadata(Class<?> clazz) {
        this.introspectedClass = clazz;
        this.annotations = clazz.getAnnotations();
    }

    @Override
    public Set<String> getAnnotationTypes() {
        Set<String> types = new LinkedHashSet<String>();
        for (Annotation ann : this.annotations) {
            types.add(ann.annotationType().getName());
        }
        return types;
    }

    @Override
    public boolean hasAnnotation(String annotationName) {
        for (Annotation ann : this.annotations) {
            if (ann.annotationType().getName().equals(annotationName)) {
                return true;
            }
        }
        return false;
    }


//    public boolean isAnnotated(String annotationName) {
//        return (this.annotations.length > 0 &&
//                AnnotatedElementUtils.isAnnotated(getIntrospectedClass(), annotationName));
//    }

//    @Override
//    public Set<MethodMetadata> getAnnotatedMethods(String annotationName) {
//        return null;
//    }

//    @Override
//    public boolean hasAnnotatedMethods(String annotationName) {
//        return false;
//    }


}
