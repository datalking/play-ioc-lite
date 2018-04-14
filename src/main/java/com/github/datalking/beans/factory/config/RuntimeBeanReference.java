package com.github.datalking.beans.factory.config;

/**
 * ref属性表示对象的引用占位符
 *
 * @author yaoo on 4/6/18
 */
public class RuntimeBeanReference {

    private final String beanName;
    private Object source;


    public RuntimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    /**
     * 获取引用对应bean的名称
     *
     * @return beanName
     */
    public String getBeanName() {
        return this.beanName;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getSource() {
        return this.source;
    }


}
