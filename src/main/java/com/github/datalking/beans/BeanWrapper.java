package com.github.datalking.beans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

/**
 * 包装bean属性的接口
 *
 * @author yaoo on 4/3/18
 */
public interface BeanWrapper {


    Class<?> getWrappedClass();

    Object getWrappedInstance();

    void setPropertyValues(PropertyValues pvs) throws NoSuchFieldException, IllegalAccessException;


//    PropertyDescriptor[] getPropertyDescriptors();
//    PropertyDescriptor getPropertyDescriptor(String propertyName) throws IntrospectionException;

}
