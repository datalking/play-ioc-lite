package com.github.datalking.bean.strbean;

/**
 * bean 所有字段为string
 *
 * @author yaoo on 4/5/18
 */
public class BeanStr02 {

    String id2;
    String name2;

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    @Override
    public String toString() {
        return "BeanStr02{" +
                "id2='" + id2 + '\'' +
                ", name2='" + name2 + '\'' +
                '}';
    }
}
