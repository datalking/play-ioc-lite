package com.github.datalking.beans.factory.support;


import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.beans.factory.BeanFactory;
import com.github.datalking.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * BeanFactory抽象类
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {


//    private boolean cacheBeanMetadata = true;

//    private final Set<String> alreadyCreated = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>(256));
//    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    // ====删掉====
//    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
//    private final List<String> beanDefinitionNames = new ArrayList<String>();


    protected abstract Object createBean(String beanName, GenericBeanDefinition bd, Object[] args) throws Exception;

//    public void destroyBean(String beanName, Object beanInstance) {

//    protected <T> T doGetBean(

//    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {

//    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

//    public List<BeanPostProcessor> getBeanPostProcessors() {

//    protected void initBeanWrapper(BeanWrapper bw) {


    @Override
    public Object getBean(String name) throws Exception {

//        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
//        if (beanDefinition == null) {
//            throw new IllegalArgumentException("No bean named " + name + " is defined");
//        }
//        Object bean = beanDefinition.getBean();
//
//        if (bean == null) {
//            bean = doCreateBean(beanDefinition);
//        }

        return doGetBean(name,null,null,false);
    }

    protected <T> T doGetBean(final String name, final Class<T> requiredType, final Object[] args, boolean typeCheckOnly) {

        Object targetBean;

        Object sharedInstance = getSingleton(name);



        return targetBean;
    }

//
//    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
//        beanDefinitionMap.put(name, beanDefinition);
//        beanDefinitionNames.add(name);
//    }
//
//    public void preInstantiateSingletons() throws Exception {
//        for (Iterator it = this.beanDefinitionNames.iterator(); it.hasNext(); ) {
//            String beanName = (String) it.next();
//            getBean(beanName);
//        }
//    }


//    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;


}
