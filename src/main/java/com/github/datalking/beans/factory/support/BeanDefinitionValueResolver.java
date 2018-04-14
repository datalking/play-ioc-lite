package com.github.datalking.beans.factory.support;

import com.github.datalking.beans.factory.config.RuntimeBeanReference;

/**
 * bean属性解析及引用类型转换
 *
 * @author yaoo on 4/8/18
 */
public class BeanDefinitionValueResolver {

    private final AbstractBeanFactory beanFactory;


    public BeanDefinitionValueResolver(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object resolveValueIfNecessary(Object argName, Object value) throws Exception {

        if (value instanceof RuntimeBeanReference) {

            RuntimeBeanReference ref = (RuntimeBeanReference) value;
            return resolveReference(argName, ref);

        }
        // ==== 处理字符串类型
        else if (value instanceof String) {
            return value;
        }

        return null;
    }

    private Object resolveReference(Object argName, RuntimeBeanReference ref) throws Exception {

        String refName = ref.getBeanName();

        Object bean = this.beanFactory.getBean(refName);
//        this.beanFactory.registerDependentBean(refName, this.beanName);
        return bean;
    }


}
