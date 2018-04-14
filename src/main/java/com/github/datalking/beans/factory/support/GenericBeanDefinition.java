package com.github.datalking.beans.factory.support;

import com.github.datalking.beans.factory.config.BeanDefinition;

/**
 * BeanDefinition默认的简单实现类
 * 代替spring的ChildBeanDefinition
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

    @Override
    public AbstractBeanDefinition cloneBeanDefinition() {
        return new GenericBeanDefinition(this);
    }

    @Override
    public boolean equals(Object o) {
        return (this == o || (o instanceof GenericBeanDefinition && super.equals(o)));
    }

    @Override
    public String toString() {
        return "GenericBDef: " + super.toString();
    }


}
