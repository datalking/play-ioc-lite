package com.github.datalking.bean;

/**
 * bean 所有字段为string
 * @author yaoo on 4/5/18
 */
public class BeanAllStr {

    String id;
    String name;
    String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BeanAllStr{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }


}
