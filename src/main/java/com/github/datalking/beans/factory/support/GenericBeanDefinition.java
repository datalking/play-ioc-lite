package com.github.datalking.beans.factory.support;

import com.github.datalking.beans.factory.config.BeanDefinition;

/**
 * BeanDefinition 默认实现类
 * 代替spring的ChildBeanDefinition和RootBeanDefinition
 *
 * @author yaoo on 4/3/18
 */
public class GenericBeanDefinition extends AbstractBeanDefinition {

//    private String parentName;

    public GenericBeanDefinition(BeanDefinition original) {
        super(original);
    }

    public GenericBeanDefinition() {
        super();
    }


}
