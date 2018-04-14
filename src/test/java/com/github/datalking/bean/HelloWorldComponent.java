package com.github.datalking.bean;

import com.github.datalking.annotation.Component;

@Component
public class HelloWorldComponent {

    private String message;

    public HelloWorldComponent(String message) {
        this.message = message;
    }

    public HelloWorldComponent() {
        this.message = "default message is ....";

    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return "HelloWorld{" +
                "message='" + message + '\'' +
                '}';
    }


}
