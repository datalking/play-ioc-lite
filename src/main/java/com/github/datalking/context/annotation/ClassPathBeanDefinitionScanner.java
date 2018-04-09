package com.github.datalking.context.annotation;

import com.github.datalking.annotation.Component;
import com.github.datalking.beans.factory.config.AnnotatedBeanDefinition;
import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.beans.factory.config.BeanDefinitionHolder;
import com.github.datalking.beans.factory.support.AbstractBeanDefinition;
import com.github.datalking.beans.factory.support.AnnotatedGenericBeanDefinition;
import com.github.datalking.beans.factory.support.BeanDefinitionReaderUtils;
import com.github.datalking.beans.factory.support.BeanDefinitionRegistry;
import com.github.datalking.io.Resource;
import com.github.datalking.util.Assert;
import com.github.datalking.util.ResourceUtils;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.github.datalking.beans.factory.support.BeanDefinitionReaderUtils.registerBeanDefinition;

/**
 * @author yaoo on 4/9/18
 */
public class ClassPathBeanDefinitionScanner {

    private final BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this(registry, true);
    }

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        this.registry = registry;
    }


    public void scan(String... basePackages) {

        doScan(basePackages);

    }

    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {

        Set<BeanDefinitionHolder> beanDefinitions = new LinkedHashSet<>();

        for (String basePackage : basePackages) {

            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);

            for (BeanDefinition candidate : candidates) {

                String beanName = BeanDefinitionReaderUtils.generateAnnotatedBeanName((AnnotatedBeanDefinition) candidate, this.registry);
//                if (candidate instanceof AbstractBeanDefinition) {
//                    postProcessBeanDefinition((AbstractBeanDefinition) candidate, beanName);
//                }
//                if (candidate instanceof AnnotatedBeanDefinition) {
//                    AnnotationConfigUtils.processCommonDefinitionAnnotations((AnnotatedBeanDefinition) candidate);
//                }

                BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(candidate, beanName);
                beanDefinitions.add(definitionHolder);

                registerBeanDefinition(definitionHolder, this.registry);
            }
        }

        return beanDefinitions;

    }

    /**
     * 获取指定包下的.class文件，生成BeanDefinition
     *
     * @param basePackage 指定包全限定名
     * @return 包下所有class对应的BeanDefinition
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {

        Set<BeanDefinition> candidates = new LinkedHashSet<>();

        Set<Class> classSet = ResourceUtils.getAllClassFromPackage(basePackage, true);

        for (Class c : classSet) {

            if (isCandidateComponent(c)) {
                AnnotatedBeanDefinition abd = new AnnotatedGenericBeanDefinition(c);
                candidates.add(abd);
            }
        }

        return candidates;

    }


    private boolean isCandidateComponent(Class clazz) {

//        Annotation[] annos = clazz.getDeclaredAnnotations();
//        for (Annotation anno : annos) {
//            // 判断注解类型是否含有@Component
//            if (anno.getClass().getName().equals(Component.class.getName())) {
//                System.out.println("==== " + anno.getClass().getName() + "是Component");
//                return true;
//            }
//        }

        if (clazz.isAnnotationPresent(Component.class)){
            return true;
        }

        return false;
    }

    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }


}
