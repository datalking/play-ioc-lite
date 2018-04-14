package com.github.datalking.annotation.meta;

import com.github.datalking.annotation.ComponentScan;
import com.github.datalking.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 注解元数据 默认实现类
 *
 * @author yaoo on 4/9/18
 */
public class StandardAnnotationMetadata extends StandardClassMetadata implements AnnotationMetadata {

    private final Annotation[] annotations;


    public StandardAnnotationMetadata(Class<?> introspectedClass) {
        super(introspectedClass);
        this.annotations = introspectedClass.getAnnotations();
    }

    @Override
    public Set<String> getAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
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


    @Override
    public Set<MethodMetadata> getAnnotatedMethods(String annotationName) {

        Method[] methods = getIntrospectedClass().getDeclaredMethods();
        Set<MethodMetadata> annotatedMethods = new LinkedHashSet<>();

        String basePackage = "";
        Class clazz = null;
        try {
            clazz = Class.forName(basePackage + StringUtils.firstLetterUpperCase(annotationName));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (Method method : methods) {

            /// 若是 非桥接方法，且方法上存在注解，且方法上有注解annotationName
            if (!method.isBridge() && method.getAnnotations().length > 0 &&
                    method.isAnnotationPresent(clazz)) {

                annotatedMethods.add(new StandardMethodMetadata(method));
            }
        }

        return annotatedMethods;
    }

    public Set<MethodMetadata> getAnnotatedMethods(Class<?> clazz) {

        Method[] methods = getIntrospectedClass().getDeclaredMethods();
        Set<MethodMetadata> annotatedMethods = new LinkedHashSet<>();

        for (Method method : methods) {

            /// 若是 非桥接方法，且方法上存在注解，且方法上有注解annotationName
            if (!method.isBridge() && method.getAnnotations().length > 0 && method.isAnnotationPresent((Class<? extends Annotation>) clazz)) {

                annotatedMethods.add(new StandardMethodMetadata(method));
            }
        }

        return annotatedMethods;
    }


    /**
     * 提取注解中的键值对
     * <p>
     * todo 抽象出通用的提取注解所有键值对的工具
     *
     * @param annotationClass     注解类
     * @param classValuesAsString 值是否转为str，默认false
     * @return 键值对
     */
    //public Map<String, Object> getAnnotationAttributes(String annotationName, boolean classValuesAsString) {
    public Map<String, Object> getAnnotationAttributes(Class<?> annotationClass, boolean classValuesAsString) {

        if (!getIntrospectedClass().isAnnotationPresent((Class<? extends Annotation>) annotationClass)) {
            return null;
        }

        Map<String, Object> annoMap = new LinkedHashMap<>();

        String annotationName = annotationClass.getName();
        final String componentScanAnnoFullPack = "com.github.datalking.annotation.ComponentScan";
        if (componentScanAnnoFullPack.equals(annotationName)) {
            annoMap.put("basePackages", getIntrospectedClass().getAnnotation(ComponentScan.class).basePackages());
            annoMap.put("basePackageClasses", getIntrospectedClass().getAnnotation(ComponentScan.class).basePackageClasses());
            annoMap.put("value", getIntrospectedClass().getAnnotation(ComponentScan.class).value());
        }
        return annoMap;
    }

//    public boolean isAnnotated(String annotationName) {
//        return (this.annotations.length > 0 &&
//                AnnotatedElementUtils.isAnnotated(getIntrospectedClass(), annotationName));
//    }


//    @Override
//    public boolean hasAnnotatedMethods(String annotationName) {
//        return false;
//    }


}
