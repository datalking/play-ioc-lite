package com.github.datalking.context.annotation;

import com.github.datalking.beans.factory.config.AnnotatedBeanDefinition;
import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.beans.factory.config.BeanDefinitionHolder;
import com.github.datalking.beans.factory.support.AbstractBeanDefinition;
import com.github.datalking.beans.factory.support.BeanDefinitionReaderUtils;
import com.github.datalking.beans.factory.support.BeanDefinitionRegistry;
import com.github.datalking.io.Resource;

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


    public Set<BeanDefinition> findCandidateComponents(String basePackage) {

        Set<BeanDefinition> candidates = new LinkedHashSet<>();

        String packageSearchPath = getPathFromPackageName(basePackage);

        Resource[] resources = getResources(packageSearchPath);

        for (Resource resource : resources) {

            if (isCandidateComponent(metadataReader)) {

            }

        }


        return null;

    }

    private String getPathFromPackageName(String basePackage) {
        if (basePackage.startsWith("/")) {
            basePackage = basePackage.substring(1);
        }
        if (basePackage.endsWith("/")) {
            basePackage = basePackage.substring(0, basePackage.length() - 1);
        }
        return basePackage.replace(".", "/");
    }


    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }


}
