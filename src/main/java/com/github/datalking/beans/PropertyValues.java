package com.github.datalking.beans;

/**
 * 多个属性值键值对接口
 * 用于包装一个对象所有的PropertyValue
 *
 * @author yaoo on 4/3/18
 */
public interface PropertyValues {

    PropertyValue[] getPropertyValues();

    PropertyValue getPropertyValue(String propertyName);

    boolean contains(String propertyName);

    boolean isEmpty();

}
