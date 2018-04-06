package com.github.datalking.beans.factory.support;

import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.beans.factory.config.ConfigurableListableBeanFactory;
import com.github.datalking.exception.NoSuchBeanDefinitionException;
import com.github.datalking.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * BeanFactory默认实现类
 * ** 核心类 **
 *
 * @author yaoo on 4/3/18
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory
        implements ConfigurableListableBeanFactory, BeanDefinitionRegistry, Serializable {

    /**
     * 所有BeanDefinition
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    /**
     * 所有bean名称
     */
    private volatile List<String> beanDefinitionNames = new ArrayList<>(256);


    //所有单例和非单例的bean
//    private final Map<Class<?>, String[]> allBeanNamesByType = new ConcurrentHashMap<Class<?>, String[]>(64);
//    //是否允许同名bean注册
//    private boolean allowBeanDefinitionOverriding = true;
//    private volatile boolean configurationFrozen = false;

    public DefaultListableBeanFactory() {
        super();
    }

    // ======== BeanDefinitionRegistry interface ========

    /**
     * 注册beanDefinition到beanDefinitionMap
     *
     * @param beanName       bean名称
     * @param beanDefinition bean属性元信息对象
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {

        synchronized (this.beanDefinitionMap) {
            //存储beanDefinition到map
            this.beanDefinitionMap.put(beanName, beanDefinition);

            List<String> updatedDefinitions = new ArrayList<>(this.beanDefinitionNames.size() + 1);
            updatedDefinitions.addAll(this.beanDefinitionNames);
            updatedDefinitions.add(beanName);
            this.beanDefinitionNames = updatedDefinitions;
        }

    }

//    @Override
//    public void removeBeanDefinition(String beanName) {
//
//        BeanDefinition bd = this.beanDefinitionMap.remove(beanName);
//        if (bd == null) throw new NoSuchBeanDefinitionException(beanName);
//        synchronized (this.beanDefinitionMap) {
//            List<String> updatedDefinitions = new ArrayList<>(this.beanDefinitionNames);
//            updatedDefinitions.remove(beanName);
//            this.beanDefinitionNames = updatedDefinitions;
//        }
//
//    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition bd = this.beanDefinitionMap.get(beanName);
        if (bd == null) {
            throw new NoSuchBeanDefinitionException(beanName);
        }
        return bd;
    }

    /**
     * 通过调用getBean()触发实例化bean
     */
    @Override
    public void preInstantiateSingletons() throws Exception {

        List<String> beanNames = new ArrayList<>(this.beanDefinitionNames);

        for (String beanName : beanNames) {
            getBean(beanName);
        }

    }
//    public void preInstantiateSingletons() throws Exception {
//        for (Iterator it = this.beanDefinitionNames.iterator(); it.hasNext(); ) {
//            String beanName = (String) it.next();
//            getBean(beanName);
//        }
//    }

    // ======== ListableBeanFactory interface ========
    @Override
    public boolean containsBeanDefinition(String beanName) {
        Assert.notNull(beanName, "Bean name must not be null");
        return this.beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionNames.toArray(new String[this.beanDefinitionNames.size()]);
    }

//    @Override
//    public String[] getBeanNamesForType(Class<?> type) {
//        return new String[0];
//    }
//    @Override
//    public <T> Map<String, T> getBeansOfType(Class<T> type) {
//        return null;
//    }


    // ======== AutowireCapableBeanFactory interface ========


    // ======== BeanFactory interface ========
//    @Override
//    public Object getBean(String name) throws Exception {
//
//        return null;
//    }

//    @Override
//    public <T> T getBean(Class<T> requiredType) throws Exception {
//        return getBean(requiredType, (Object[]) null);
//    }
//
//    public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
//        NamedBeanHolder<T> namedBean = resolveNamedBean(requiredType, args);
//        if (namedBean != null) {
//            return namedBean.getBeanInstance();
//        }
//        BeanFactory parent = getParentBeanFactory();
//        if (parent != null) {
//            return parent.getBean(requiredType, args);
//        }
//        throw new NoSuchBeanDefinitionException(requiredType);
//    }


}
