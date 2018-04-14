package com.github.datalking.context.annotation;

import com.github.datalking.annotation.Bean;
import com.github.datalking.annotation.Component;
import com.github.datalking.annotation.ComponentScan;
import com.github.datalking.annotation.ComponentScans;
import com.github.datalking.annotation.meta.AnnotationAttributes;
import com.github.datalking.annotation.meta.AnnotationMetadata;
import com.github.datalking.annotation.meta.MethodMetadata;
import com.github.datalking.beans.factory.config.AnnotatedBeanDefinition;
import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.beans.factory.config.BeanDefinitionHolder;
import com.github.datalking.beans.factory.support.AbstractAutowireCapableBeanFactory;
import com.github.datalking.beans.factory.support.AbstractBeanDefinition;
import com.github.datalking.beans.factory.support.BeanDefinitionRegistry;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author yaoo on 4/13/18
 */
public class ConfigurationClassParser {

    private final BeanDefinitionRegistry registry;

    private final ComponentScanAnnotationParser componentScanParser;

    private final Map<ConfigurationClass, ConfigurationClass> configurationClasses = new LinkedHashMap<>();


    public ConfigurationClassParser(BeanDefinitionRegistry registry) {
        this.registry = registry;
        this.componentScanParser = new ComponentScanAnnotationParser(registry);

    }

    public void parse(Set<BeanDefinitionHolder> configCandidates) {

        for (BeanDefinitionHolder holder : configCandidates) {
            BeanDefinition bd = holder.getBeanDefinition();

            if (bd instanceof AnnotatedBeanDefinition) {
                parse(((AnnotatedBeanDefinition) bd).getMetadata(), holder.getBeanName());
            }

        }

    }

    private void parse(AnnotationMetadata metadata, String beanName) {
        processConfigurationClass(new ConfigurationClass(metadata, beanName));
    }

    protected final void parse(Class<?> clazz, String beanName) {
        processConfigurationClass(new ConfigurationClass(clazz, beanName));
    }

    private void processConfigurationClass(ConfigurationClass configClass) {

        // ==== 真正扫描@Configuration、@Bean、@ComponentScan
        doProcessConfigurationClass(configClass);

        // 存储扫描结果
        this.configurationClasses.put(configClass, configClass);

    }


    /**
     * 扫描@ComponentScan、@Bean
     *
     * @param configClass 标注有@Configuration的类的元信息
     */
    private void doProcessConfigurationClass(ConfigurationClass configClass) {

        // ==== 如果configClass标注有@ComponentScan，则获取注解的属性map
//        Set<AnnotationAttributes> componentScans = attributesForRepeatable(configClass.getMetadata(), ComponentScans.class, ComponentScan.class);
        Set<AnnotationAttributes> componentScans = attributesForRepeatable(configClass.getMetadata(), null, ComponentScan.class);

        if (!componentScans.isEmpty()) {
            for (AnnotationAttributes componentScan : componentScans) {

                // 通过ComponentScanAnnotationParser解析@ComponentScan注解
                Set<BeanDefinitionHolder> scannedBeanDefinitions = this.componentScanParser.parse(componentScan, configClass.getMetadata().getClassName());

//                for (BeanDefinitionHolder holder : scannedBeanDefinitions) {
//
//                    AbstractBeanDefinition bd = (AbstractBeanDefinition) holder.getBeanDefinition();
//
//                    Class clazz = ((AbstractAutowireCapableBeanFactory) registry).doResolveBeanClass(bd);
//
//                    // 将配置的各个包下的Component类扫描出来 todo 递归中止条件
//                    if (clazz.isAnnotationPresent(Component.class)) {
//                        parse(clazz, holder.getBeanName());
//                    }
//
//                }
            }
        }

        // ==== 扫描@Bean
        Set<MethodMetadata> beanMethods = retrieveBeanMethodMetadata(configClass);

        for (MethodMetadata methodMetadata : beanMethods) {
            configClass.addBeanMethod(new BeanMethod(methodMetadata, configClass));
        }


    }

    private Set<MethodMetadata> retrieveBeanMethodMetadata(ConfigurationClass configClass) {

        AnnotationMetadata original = configClass.getMetadata();
        Set<MethodMetadata> beanMethods = original.getAnnotatedMethods(Bean.class);
        return beanMethods;


    }

    public Set<ConfigurationClass> getConfigurationClasses() {
        return this.configurationClasses.keySet();
    }


    private Set<AnnotationAttributes> attributesForRepeatable(AnnotationMetadata metadata,
                                                              Class<?> containerClass,
                                                              Class<?> annotationClass) {

        //String containerClassName = containerClass.getName();
        // String annotationClassName = annotationClass.getName();

        Set<AnnotationAttributes> result = new LinkedHashSet<>();

        AnnotationAttributes attrMap = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(annotationClass, false));
        if (attrMap != null) {
            result.add(attrMap);
        }


        //Map<String, Object> container = metadata.getAnnotationAttributes(containerClassName,false);

//        if (container != null && container.containsKey("value")) {
//            for (Map<String, Object> containedAttributes : (Map<String, Object>[]) container.get("value")) {
//                result.add(AnnotationAttributes.fromMap(containedAttributes));
//            }
//        }

        return result;
    }


}
