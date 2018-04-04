package com.github.datalking.beans;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || this.getClass() != o.getClass()) return false;

        PropertyValue that = (PropertyValue) o;
        return Objects.equals(name, that.name) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, value);
    }

}
