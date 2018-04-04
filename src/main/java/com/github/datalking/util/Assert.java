package com.github.datalking.util;

/**
 * 判断型工具类
 *
 * @author yaoo on 4/3/18
 */
public interface Assert {


    static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    static void isNull(Object object) {
        isNull(object, "[Assertion failed] - the object argument must be null");
    }


}
