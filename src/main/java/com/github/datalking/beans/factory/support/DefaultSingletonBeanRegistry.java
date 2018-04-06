package com.github.datalking.beans.factory.support;

import com.github.datalking.beans.factory.ObjectFactory;
import com.github.datalking.beans.factory.config.SingletonBeanRegistry;
import com.github.datalking.util.Assert;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 生成的单例bean的集合
 * ** 核心类 **
 *
 * @author yaoo on 4/4/18
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    //空对象标志，解决ConcurrentHashMap不支持null的问题
    protected static final Object NULL_OBJECT = new Object();
    /**
     * 所有的单例bean
     */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
//private final Map<String, Object> earlySingletonObjects = new HashMap<String, Object>(16);
//private final Set<String> registeredSingletons = new LinkedHashSet<String>(256);
//private final Map<String, Object> disposableBeans = new LinkedHashMap<String, Object>();


    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        Assert.notNull(beanName, "'beanName' must not be null");
        synchronized (this.singletonObjects) {
            Object oldObject = this.singletonObjects.get(beanName);
            //不能注册同名bean
            if (oldObject != null) {
                throw new IllegalStateException("Could not register object [" + singletonObject +
                        "] under bean name '" + beanName + "': there is already object [" + oldObject + "] bound");
            }
            addSingleton(beanName, singletonObject);
        }
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        synchronized (this.singletonObjects) {
            this.singletonObjects.put(beanName, (singletonObject != null ? singletonObject : NULL_OBJECT));
        }
    }

    // 用于提前注册bean，避免循环依赖
    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) throws Exception {
        Assert.notNull(singletonFactory, "Singleton factory must not be null");
        synchronized (this.singletonObjects) {
            if (!this.singletonObjects.containsKey(beanName)) {
                this.singletonObjects.put(beanName, (singletonFactory.getObject() != null ? singletonFactory.getObject() : NULL_OBJECT));

//                this.singletonFactories.put(beanName, singletonFactory);
//                this.earlySingletonObjects.remove(beanName);
//                this.registeredSingletons.add(beanName);
            }
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return getSingleton(beanName, true);

    }

    /**
     * 通过beanName获取bean实例
     *
     * @param beanName            bean名称
     * @param allowEarlyReference 是否允许提前曝光
     * @return bean实例
     */
    protected Object getSingleton(String beanName, boolean allowEarlyReference) {
        Object singletonObject = this.singletonObjects.get(beanName);
//        if (singletonObject == null) {
//            synchronized (this.singletonObjects) {
//                singletonObject = this.earlySingletonObjects.get(beanName);
//                if (singletonObject == null && allowEarlyReference) {
//                    ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
//                    if (singletonFactory != null) {
//                        singletonObject = singletonFactory.getObject();
//                        this.earlySingletonObjects.put(beanName, singletonObject);
//                        this.singletonFactories.remove(beanName);
//                    }
//                }
//            }
//        }
        return (singletonObject != NULL_OBJECT ? singletonObject : null);
    }

    public Object getSingleton(String beanName, ObjectFactory<?> singletonFactory) {

        Assert.notNull(beanName, "'beanName' must not be null");
        synchronized (this.singletonObjects) {
//            for (Map.Entry entry : this.singletonObjects.entrySet()) {
//                System.out.println(entry.getKey() + ", " + entry.getValue());
//            }
            Object singletonObject = this.singletonObjects.get(beanName);

            /// 若singletonObject为空，则再次获取
            // todo 改为在延迟初始化的集合中获取
            if (singletonObject == null) {
                boolean newSingleton = false;
                try {
                    singletonObject = singletonFactory.getObject();
                    newSingleton = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (newSingleton) {
                    addSingleton(beanName, singletonObject);
                }
            }

            return (singletonObject != NULL_OBJECT ? singletonObject : null);
        }

    }

    protected void removeSingleton(String beanName) {
        synchronized (this.singletonObjects) {
            this.singletonObjects.remove(beanName);
        }
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return this.singletonObjects.containsKey(beanName);
    }

    @Override
    public String[] getSingletonNames() {
        //todo
        return new String[0];
    }

    @Override
    public int getSingletonCount() {
        synchronized (this.singletonObjects) {
            return this.singletonObjects.size();
//            return this.registeredSingletons.size();
        }
    }
}
