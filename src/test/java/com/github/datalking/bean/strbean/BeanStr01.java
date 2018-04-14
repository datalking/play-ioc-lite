package com.github.datalking.bean.strbean;

import com.github.datalking.annotation.Component;

/**
 * bean 所有字段为string
 *
 * @author yaoo on 4/5/18
 */
@Component
public class BeanStr01 {

    String id;
    String name;

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


    @Override
    public String toString() {
        return "BeanAllStr{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


}
