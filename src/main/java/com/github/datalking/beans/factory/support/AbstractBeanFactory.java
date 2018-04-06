package com.github.datalking.beans.factory.support;


import com.github.datalking.beans.PropertyValue;
import com.github.datalking.beans.factory.ObjectFactory;
import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.beans.factory.BeanFactory;
import com.github.datalking.beans.factory.config.BeanReference;
import com.github.datalking.beans.factory.config.ConfigurableBeanFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * BeanFactory抽象类
 * getBean(String name)的入口
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {


//    private boolean cacheBeanMetadata = true;

//    private final Set<String> alreadyCreated = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>(256));
//    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    // ====删掉====
//    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
//    private final List<String> beanDefinitionNames = new ArrayList<String>();


//    public void destroyBean(String beanName, Object beanInstance) {

//    protected <T> T doGetBean(

//    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {

//    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

//    public List<BeanPostProcessor> getBeanPostProcessors() {

//    protected void initBeanWrapper(BeanWrapper bw) {


    protected abstract Object createBean(String beanName, GenericBeanDefinition bd, Object[] args) throws Exception;

    protected abstract BeanDefinition getBeanDefinition(String beanName);


    @Override
    public Object getBean(String name) throws Exception {

        return doGetBean(name, null, null, false);
    }

    protected <T> T doGetBean(final String name, final Class<T> requiredType, final Object[] args, boolean typeCheckOnly) throws Exception {

        /// 如果name已存在，则直接返回bean
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null && args == null) {
            return (T) sharedInstance;
        }
        ///如果name不存在，则新建bean

        Object targetBean;
        ///todo 先检查并创建name所依赖的bean

        ///再创建单例bean
        Object bean = createBean(name, (GenericBeanDefinition) getBeanDefinition(name), args);
        targetBean = getSingleton(name, (ObjectFactory) () -> bean);

        //写成下面这样会异常，createBean()未执行，会先执行getSingleton()
        //targetBean = getSingleton(name, (ObjectFactory) () -> createBean(name, (GenericBeanDefinition) getBeanDefinition(name), args));

        return (T) targetBean;
    }


//
//    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
//        beanDefinitionMap.put(name, beanDefinition);
//        beanDefinitionNames.add(name);
//    }
//


//    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;


}
