package com.github.datalking.util;

/**
 * 字符串工具类
 *
 * @author yaoo on 4/3/18
 */
public interface StringUtils {

    static boolean isEmpty(String str) {
        return (str == null || "".equals(str));
    }

    static boolean isNotEmpty(String str) {
        return (str != null && str.length() > 0);
    }


}
