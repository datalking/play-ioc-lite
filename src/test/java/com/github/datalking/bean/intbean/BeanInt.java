package com.github.datalking.bean.intbean;

import com.github.datalking.annotation.Component;

/**
 * @author yaoo on 4/9/18
 */
@Component
public class BeanInt {

    private int f1;
    private Integer f2;

    public int getF1() {
        return f1;
    }

    public void setF1(int f1) {
        this.f1 = f1;
    }

    public Integer getF2() {
        return f2;
    }

    public void setF2(Integer f2) {
        this.f2 = f2;
    }

    @Override
    public String toString() {
        return "BeanInt{" +
                "f1=" + f1 +
                ", f2=" + f2 +
                '}';
    }
}
