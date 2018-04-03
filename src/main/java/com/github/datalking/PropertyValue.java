package com.github.datalking;

import java.io.Serializable;

/**
 * bean的单个属性键值对
 */
public class PropertyValue implements Serializable {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public PropertyValue(PropertyValue kv) {
        this.name = kv.getName();
        this.value = kv.getValue();
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
