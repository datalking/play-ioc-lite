package com.github.datalking.beans;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 包装bean属性 实现类
 *
 * @author yaoo on 4/3/18
 */
public class BeanWrapperImpl implements BeanWrapper {

    private Object wrappedObject;
//    Object rootObject;
//    private String nestedPath = "";
//    private CachedIntrospectionResults cachedIntrospectionResults;


    public BeanWrapperImpl() {
    }

    public BeanWrapperImpl(Object o) {
        this.wrappedObject = o;
    }

    public BeanWrapperImpl(Class<?> clazz) throws IllegalAccessException, InstantiationException {
        this.wrappedObject = clazz.newInstance();
    }

    public void setBeanInstance(Object object) {
        this.wrappedObject = object;
        //this.rootObject = object;
        //this.typeConverterDelegate = new TypeConverterDelegate(this, this.wrappedObject);
        //setIntrospectionClass(object.getClass());
    }

    public final Object getWrappedInstance() {
        return this.wrappedObject;
    }


    public final Class<?> getWrappedClass() {
        return (this.wrappedObject != null ? this.wrappedObject.getClass() : null);
    }

    /**
     * 给bean实例设置属性
     * 调用此方法之前，要先确保属性类型已经经过正确转换
     *
     * @param pvs 类型已转换正确的键值对属性
     */
    @Override
    public void setPropertyValues(PropertyValues pvs) throws NoSuchFieldException, IllegalAccessException {
        List<PropertyValue> propertyValues = ((MutablePropertyValues) pvs).getPropertyValueList();

        for (PropertyValue pv : propertyValues) {

            setPropertyValue(pv);

        }

    }

    public void setPropertyValue(PropertyValue pv) throws NoSuchFieldException, IllegalAccessException {

        Field declaredField = this.wrappedObject.getClass().getDeclaredField(pv.getName());
        declaredField.setAccessible(true);
        Object value = pv.getValue();

        Class clazz = declaredField.getType();
        String typeName = clazz.getName();

        switch (typeName) {


            /// 8种基本类型 + string
            case "byte":
            case "java.lang.Byte":
                declaredField.set(this.wrappedObject, Byte.valueOf(value.toString()));
                break;
            case "short":
            case "java.lang.Short":
                declaredField.set(this.wrappedObject, Short.valueOf(value.toString()));
                break;
            case "int":
            case "java.lang.Integer":
                declaredField.set(this.wrappedObject, Integer.valueOf(value.toString()));
                break;
            case "long":
            case "java.lang.Long":
                declaredField.set(this.wrappedObject, Long.valueOf(value.toString()));
            case "float":
            case "java.lang.Float":
                declaredField.set(this.wrappedObject, Float.valueOf(value.toString()));
                break;
            case "double":
            case "java.lang.Double":
                declaredField.set(this.wrappedObject, Double.valueOf(value.toString()));
                break;
            case "char":
            case "java.lang.Character":
                declaredField.set(this.wrappedObject, value);
                break;
            case "boolean":
            case "java.lang.Boolean":
                declaredField.set(this.wrappedObject, Boolean.valueOf(value.toString()));
                break;
            case "java.lang.String":
                declaredField.set(this.wrappedObject, String.valueOf(value.toString()));
                break;

            ///默认处理引用类型
            default:
                declaredField.set(this.wrappedObject, value);

        }


    }


//    @Override
//    public PropertyDescriptor[] getPropertyDescriptors() {
//
//        return new PropertyDescriptor[0];
//    }
//
//    @Override
//    public PropertyDescriptor getPropertyDescriptor(String propertyName) throws IntrospectionException {
//
//        PropertyDescriptor pd = new PropertyDescriptor(propertyName, this.wrappedObject.getClass());
////        Method readMethod = pd1.getReadMethod(); //获得读取属性值的方法
////        Object retVal = readMethod.invoke(bp);
//        return pd;
//    }


}
