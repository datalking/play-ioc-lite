package com.github.datalking.exception;

/**
 * BeanDefinition未定义的异常
 *
 * @author yaoo on 4/4/18
 */
public class NoSuchBeanDefinitionException extends RuntimeException {

    public NoSuchBeanDefinitionException(String message) {
        super(message);
    }

}
