package com.github.datalking.beans;

/**
 * 包装bean属性 实现类
 *
 * @author yaoo on 4/3/18
 */
public class BeanWrapperImpl implements BeanWrapper {

    private Object wrappedObject;
//    Object rootObject;
//    private String nestedPath = "";


    public final Object getWrappedInstance() {
        return this.wrappedObject;
    }

    public final Class<?> getWrappedClass() {
        return (this.wrappedObject != null ? this.wrappedObject.getClass() : null);
    }

}
