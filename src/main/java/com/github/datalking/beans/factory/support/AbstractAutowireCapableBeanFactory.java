package com.github.datalking.beans.factory.support;

import com.github.datalking.beans.BeanWrapper;
import com.github.datalking.beans.BeanWrapperImpl;
import com.github.datalking.beans.MutablePropertyValues;
import com.github.datalking.beans.PropertyValue;
import com.github.datalking.beans.factory.config.AutowireCapableBeanFactory;
import com.github.datalking.beans.factory.config.BeanDefinition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * BeanFactory抽象类
 * 实际创建bean、装配属性
 *
 * @author yaoo on 4/3/18
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
        implements AutowireCapableBeanFactory {

    private boolean allowCircularReferences = true;

    public AbstractAutowireCapableBeanFactory() {
        super();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T createBean(Class<T> beanClass) throws Exception {
        BeanDefinition bd = new RootBeanDefinition();
        return (T) createBean(beanClass.getName(), (RootBeanDefinition) bd, null);
    }

    @Override
    protected Object createBean(String beanName, RootBeanDefinition bd, Object[] args) throws Exception {

        Class beanClass = doResolveBeanClass(bd);
        bd.setBeanClass(beanClass);

        return doCreateBean(beanName, bd, args);
    }


    protected Object doCreateBean(final String beanName, final RootBeanDefinition bd, final Object[] args) throws Exception {

        BeanWrapper instanceWrapper = null;

        // 调用无参构造函数新建bean实例，尚未注入属性
        instanceWrapper = createBeanInstance(beanName, bd, args);
        final Object bean = (instanceWrapper != null ? instanceWrapper.getWrappedInstance() : null);


        boolean earlySingletonExposure = (bd.isSingleton() && this.allowCircularReferences && isSingletonCurrentlyInCreation(beanName));
        if (earlySingletonExposure) {
            // 先添加到singletonFactories和registeredSingletons中，提前曝光引用
            addSingletonFactory(beanName, () -> bean);
        }


        //注入属性
        populateBean(beanName, bd, instanceWrapper);

//        Object exposedObject = bean;
//        if(exposedObject!=null){
//            //调用配置的init方法
//            exposedObject = initializeBean(beanName, exposedObject, mbd);
//
//        }

        //注册bean销毁的方法
//      registerDisposableBeanIfNecessary(beanName, bean, mbd);


        return bean;
    }

    protected BeanWrapper createBeanInstance(String beanName, RootBeanDefinition bd, Object[] args) throws Exception {

//        Class beanClass = doResolveBeanClass(bd);
//        bd.setBeanClass(beanClass);


        // 根据ConfigurationClassBeanDefinition指定的FactoryMethod创建bean实例
        if (bd.getFactoryMethodName() != null) {
            return instantiateUsingFactoryMethod(beanName, bd, args);
        }

        //todo 选择构造器

        //直接使用无参构造函数创建对象
        return instantiateBean(beanName, bd);

    }

    /**
     * 通过jdk反射生成bean实例
     * spring对调用无参构造函数生成实例使用的是cglib
     */
    private BeanWrapper instantiateBean(final String beanName, final RootBeanDefinition bd) throws IllegalAccessException, InstantiationException {

        Object beanInstance = bd.getBeanClass().newInstance();
        BeanWrapper bw = new BeanWrapperImpl(beanInstance);

        return bw;

    }

    private void populateBean(String beanName, RootBeanDefinition bd, BeanWrapper bw) throws Exception {

        applyPropertyValues(beanName, bd, bw);

    }


    /**
     * 使用工厂方法创建bean实例
     *
     * @param beanName     要创建的bean的名称
     * @param bd           该bean的BeanDefiniiton
     * @param explicitArgs 参数
     * @return bean实例包装类
     */
    private BeanWrapper instantiateUsingFactoryMethod(final String beanName, final RootBeanDefinition bd, final Object[] explicitArgs) {

        BeanWrapperImpl bw = new BeanWrapperImpl();

        Object factoryBean = null;
        Class<?> factoryClass;
        //boolean isStatic;

        try {

            factoryBean = getBean(bd.getFactoryBeanName());
            if (factoryBean == null) {
                throw new Exception(beanName + "找不到FactoryBeanName");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        factoryClass = factoryBean.getClass();

        Method factoryMethodToUse = null;
        //ArgumentsHolder argsHolderToUse = null;
        //Object[] argsToUse = null;

        Method[] maybeFactoryMethods = factoryClass.getDeclaredMethods();
        for (Method m : maybeFactoryMethods) {
            if (m.getName().equals(beanName)) {
                factoryMethodToUse = m;
            }
        }

        if (factoryMethodToUse == null) {
            try {
                throw new Exception(beanName + "找不到factoryMethodToUse");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        Object beanInstance = null;

        try {
            //beanInstance = this.beanFactory.getInstantiationStrategy().instantiate(mbd, beanName, this.beanFactory, factoryBean, factoryMethodToUse, argsToUse);

            beanInstance = factoryMethodToUse.invoke(factoryBean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        if (beanInstance == null) {
            return null;
        }

        bw.setBeanInstance(beanInstance);
        return bw;
    }

    /**
     * 将BeanDefinition的属性注入到bean实例
     *
     * @param beanName 待添加属性的beanName
     * @param bd       要添加的属性定义
     * @param bw       beanWrapper实例
     */
    protected void applyPropertyValues(String beanName, BeanDefinition bd, BeanWrapper bw) throws Exception {

        List<PropertyValue> pvList = bd.getPropertyValues().getPropertyValueList();
        List<PropertyValue> deepCopy = new ArrayList<>(pvList.size());

        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this);


        for (PropertyValue pv : pvList) {

            String pvName = pv.getName();
            Object pvValue = pv.getValue();

            // ==== 属性值类型转换，使用默认转换器，ref转bean实例，string直接返回值
            Object resolvedValue = valueResolver.resolveValueIfNecessary(pv, pvValue);

            deepCopy.add(new PropertyValue(pvName, resolvedValue));

        }

        // ==== 批量设置值
        bw.setPropertyValues(new MutablePropertyValues(deepCopy));


    }

    public Class<?> doResolveBeanClass(RootBeanDefinition bd) throws ClassNotFoundException {
        return doResolveBeanClass((AbstractBeanDefinition) bd);
    }

    public Class<?> doResolveBeanClass(AbstractBeanDefinition bd) {
        String className = bd.getBeanClassName();
        if (className != null) {

            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        return null;
    }


//public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
//public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
//public void applyBeanPropertyValues(Object existingBean, String beanName) throws BeansException {
//protected void applyPropertyValues(String beanName, BeanDefinition mbd, BeanWrapper bw, PropertyValues pvs) {
//public Object autowire(Class<?> beanClass, int autowireMode, boolean dependencyCheck) throws BeansException {
//public void autowireBean(Object existingBean) {


}
