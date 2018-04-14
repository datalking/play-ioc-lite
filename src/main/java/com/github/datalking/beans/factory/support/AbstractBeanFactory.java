package com.github.datalking.beans.factory.support;

import com.github.datalking.beans.factory.ObjectFactory;
import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.beans.factory.config.ConfigurableBeanFactory;
import com.github.datalking.exception.NoSuchBeanDefinitionException;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * BeanFactory抽象类
 * getBean(String name)的入口
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    //存放已经创建或正在创建的bean名称，用于失败回滚或销毁清理
    private final Set<String> alreadyCreated = Collections.newSetFromMap(new ConcurrentHashMap<>(256));
    /**
     * 各类BeanDefinition合并属性后的RootBeanDefinition ConcurrentHashMap
     */
    private final Map<String, RootBeanDefinition> mergedBeanDefinitions = new ConcurrentHashMap<>(256);

//    private boolean cacheBeanMetadata = true;
//    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    protected abstract Object createBean(String beanName, RootBeanDefinition bd, Object[] args) throws Exception;

    protected abstract BeanDefinition getBeanDefinition(String beanName);


    @Override
    public Object getBean(String name) throws Exception {

        return doGetBean(name, null, null, false);
    }

    protected <T> T doGetBean(final String name, final Class<T> requiredType, final Object[] args, boolean typeCheckOnly) throws Exception {

        //将别名解析为bean唯一名称
        //final String name = transformedBeanName(name);

        /// 从3级缓存中找bean，如果name对应的bean实例已缓存，则直接返回bean
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null && args == null) {
            return (T) sharedInstance;
        }

        ///如果name对应的bean实例不存在，则新建bean

        /// 标记为已经创建
        if (!this.alreadyCreated.contains(name)) {
            this.alreadyCreated.add(name);
        }

        final RootBeanDefinition bd = getMergedLocalBeanDefinition(name);
//        RootBeanDefinition bd = (RootBeanDefinition) getBeanDefinition(name);

        if (bd == null) {
            throw new NoSuchBeanDefinitionException(name + " 对应的BeanDefinition不存在");
        }
        //合并beanDefinition
        // bd = getMergedLocalBeanDefinition(beanName);

        ///判断scope为单例，创建单例bean
        Object targetBean;
        targetBean = getSingleton(name, (ObjectFactory) () -> createBean(name, bd, args));

        return (T) targetBean;
    }


    protected RootBeanDefinition getMergedLocalBeanDefinition(String beanName) {
        RootBeanDefinition mbd = this.mergedBeanDefinitions.get(beanName);
        if (mbd != null) {
            return mbd;
        }

        // 从beanDefinitionMap里面取
        BeanDefinition bd = getBeanDefinition(beanName);
        return getMergedBeanDefinition(beanName, bd);

    }

    protected RootBeanDefinition getMergedBeanDefinition(String beanName, BeanDefinition bd) {

        return getMergedBeanDefinition(beanName, bd, null);
    }

    protected RootBeanDefinition getMergedBeanDefinition(String beanName, BeanDefinition bd, BeanDefinition containingBd) {

        synchronized (this.mergedBeanDefinitions) {
            RootBeanDefinition mbd = null;

            if (bd instanceof RootBeanDefinition) {
                mbd = ((RootBeanDefinition) bd).cloneBeanDefinition();
            } else {
                mbd = new RootBeanDefinition(bd);
            }
            this.mergedBeanDefinitions.put(beanName, mbd);

            return mbd;
        }

    }


//    public void destroyBean(String beanName, Object beanInstance) {
//    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
//    public List<BeanPostProcessor> getBeanPostProcessors() {
//    protected void initBeanWrapper(BeanWrapper bw) {


}
