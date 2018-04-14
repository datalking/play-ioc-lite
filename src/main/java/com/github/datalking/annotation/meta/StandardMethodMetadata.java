package com.github.datalking.annotation.meta;

import java.lang.reflect.Method;

/**
 * @author yaoo on 4/13/18
 */
public class StandardMethodMetadata implements MethodMetadata {


    private final Method introspectedMethod;

    public StandardMethodMetadata(Method introspectedMethod) {
        this.introspectedMethod = introspectedMethod;
    }

    @Override
    public String getMethodName() {
        return this.introspectedMethod.getName();
    }

    @Override
    public String getDeclaringClassName() {
        return this.introspectedMethod.getDeclaringClass().getName();
    }

    @Override
    public String getReturnTypeName() {
        return this.introspectedMethod.getReturnType().getName();
    }


}
