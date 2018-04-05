package com.github.datalking.beans.factory;

/**
 * 创建bean的简单工厂接口
 * 用于AbstractBeanFactory的doGetBean()
 *
 * @author yaoo on 4/4/18
 */
public interface ObjectFactory<T> {

    T getObject() throws Exception;


}
