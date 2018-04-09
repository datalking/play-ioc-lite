package com.github.datalking.context.annotation;

import com.github.datalking.beans.factory.support.AbstractBeanFactory;
import com.github.datalking.beans.factory.support.BeanDefinitionRegistry;
import com.github.datalking.context.support.AbstractApplicationContext;
import com.github.datalking.util.Assert;

/**
 * 读取注解的ApplicationContext
 *
 * @author yaoo on 4/9/18
 */
public class AnnotationConfigApplicationContext extends AbstractApplicationContext implements AnnotationConfigRegistry {

    private final AnnotatedBeanDefinitionReader reader;

    private final ClassPathBeanDefinitionScanner scanner;

    public AnnotationConfigApplicationContext() {
        super();
        this.reader = new AnnotatedBeanDefinitionReader((BeanDefinitionRegistry) this);
        this.scanner = new ClassPathBeanDefinitionScanner((BeanDefinitionRegistry) this);
    }

    public AnnotationConfigApplicationContext(AbstractBeanFactory beanFactory) {
        super(beanFactory);
        this.reader = new AnnotatedBeanDefinitionReader((BeanDefinitionRegistry) this);
        this.scanner = new ClassPathBeanDefinitionScanner((BeanDefinitionRegistry) this);
    }


    public AnnotationConfigApplicationContext(Class<?>... annotatedClasses) {
        this();
        register(annotatedClasses);
        //refresh();
    }

    /**
     * 扫描指定包下的注解类
     *
     * @param basePackages 指定包
     */
    @Override
    public void scan(String... basePackages) {
        Assert.notNull(basePackages, "At least one base package must be specified");
        this.scanner.scan(basePackages);
    }

    /**
     * 直接注册class
     *
     * @param annotatedClasses 要注册的class对象
     */
    @Override
    public void register(Class<?>... annotatedClasses) {
        Assert.notNull(annotatedClasses, "At least one annotated class must be specified");
        this.reader.register(annotatedClasses);
    }


}


