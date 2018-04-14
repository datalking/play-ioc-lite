package com.github.datalking.bean;

public class HelloService {

    private String text;

    private WorldService worldService;

    public HelloService() {
        this.text = "default text";
        this.worldService = null;
    }

    public HelloService(String text, WorldService worldService) {
        this.text = text;
        this.worldService = worldService;
    }

    public void print() {
        System.out.println(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public WorldService getWorldService() {
        return worldService;
    }

    public void setWorldService(WorldService worldService) {
        this.worldService = worldService;
    }
}
