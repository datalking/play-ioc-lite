package com.github.datalking.beans;

import java.beans.PropertyDescriptor;

/**
 * 包装bean的接口
 *
 * @author yaoo on 4/3/18
 */
public interface BeanWrapper {


    Class<?> getWrappedClass();

    Object getWrappedInstance();

//    PropertyDescriptor[] getPropertyDescriptors();
//    PropertyDescriptor getPropertyDescriptor(String propertyName)

}
