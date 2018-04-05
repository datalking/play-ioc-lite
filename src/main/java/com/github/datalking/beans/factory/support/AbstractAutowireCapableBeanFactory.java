package com.github.datalking.beans.factory.support;

import com.github.datalking.beans.PropertyValue;
import com.github.datalking.beans.factory.ObjectFactory;
import com.github.datalking.beans.factory.config.AutowireCapableBeanFactory;
import com.github.datalking.beans.factory.config.BeanDefinition;
import com.github.datalking.beans.factory.config.BeanReference;

import java.lang.reflect.Field;

/**
 * BeanFactory抽象类
 * 实际创建bean、装配属性
 *
 * @author yaoo on 4/3/18
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
        implements AutowireCapableBeanFactory {

    //private boolean allowCircularReferences = true;

    public AbstractAutowireCapableBeanFactory() {
        super();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T createBean(Class<T> beanClass) throws Exception {
        BeanDefinition bd = new GenericBeanDefinition();
        return (T) createBean(beanClass.getName(), (GenericBeanDefinition) bd, null);
    }

    @Override
    protected Object createBean(String beanName, GenericBeanDefinition bd, Object[] args) throws Exception {

        return doCreateBean(beanName, bd, args);
    }


    protected Object doCreateBean(final String beanName, final GenericBeanDefinition bd, final Object[] args) throws Exception {

        // 调用无参构造函数新建bean实例
        Object bean = createBeanInstance(beanName, bd, args);
//        beanDefinition.setBean(bean);

        // 先注册bean对象引用
        addSingletonFactory(beanName, () -> bean);

        //注入属性
        populateBean(beanName, bd, bean);

        return bean;
    }

    //    protected BeanWrapper createBeanInstance(String beanName, RootBeanDefinition mbd, Object[] args) {
    protected Object createBeanInstance(String beanName, GenericBeanDefinition bd, Object[] args) throws Exception {

        // 加载类
        Class beanClass = doResolveBeanClass(bd);
        bd.setBeanClass(beanClass);

        //todo 选择构造器

        //直接使用无参构造函数创建对象
        return instantiateBean(beanName, bd);

    }

    protected Object instantiateBean(final String beanName, final GenericBeanDefinition bd) throws IllegalAccessException, InstantiationException {

        return bd.getBeanClass().newInstance();

    }

    protected void populateBean(String beanName, GenericBeanDefinition bd, Object bean) throws Exception {

        applyPropertyValues(bean, bd);

    }

    private Class<?> doResolveBeanClass(GenericBeanDefinition bd) throws ClassNotFoundException {
        String className = bd.getBeanClassName();
        if (className != null) {
            return Class.forName(className);
        }
        return null;
    }


    /**
     * 将BeanDefinition的属性添加到bean对象
     *
     * @param bean 待添加属性的对象
     * @param bd   要添加的属性定义
     */
    protected void applyPropertyValues(Object bean, BeanDefinition bd) throws Exception {

        for (PropertyValue propertyValue : bd.getPropertyValues().getPropertyValues()) {

            Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());

            declaredField.setAccessible(true);
            Object value = propertyValue.getValue();

            //如果value为引用类型
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                //初始化依赖的对象
                value = getBean(beanReference.getName());
            }

            // 特殊处理整型字段
            Class clazz=declaredField.getType();
            if (clazz.getName().equals("java.lang.Integer")) {
                declaredField.set(bean, Integer.valueOf(value.toString()));
                continue;
            }

            //将bean对象的declaredField字段设置为value
            declaredField.set(bean, value);


        }
    }


//public Object createBean(Class<?> beanClass) throws BeansException {
//protected Object createBean(String beanName, RootBeanDefinition mbd, Object[] args) throws BeanCreationException {
//protected Object doCreateBean(final String beanName, final RootBeanDefinition mbd, final Object[] args)
//protected BeanWrapper createBeanInstance(String beanName, RootBeanDefinition mbd, Object[] args) {
//protected void populateBean(String beanName, RootBeanDefinition mbd, BeanWrapper bw) {


//public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
//public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
//public void applyBeanPropertyValues(Object existingBean, String beanName) throws BeansException {
//protected void applyPropertyValues(String beanName, BeanDefinition mbd, BeanWrapper bw, PropertyValues pvs) {
//public Object autowire(Class<?> beanClass, int autowireMode, boolean dependencyCheck) throws BeansException {
//public void autowireBean(Object existingBean) {


}
