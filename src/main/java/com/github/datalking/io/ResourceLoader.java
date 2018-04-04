package com.github.datalking.io;

import java.net.URL;

/**
 * 资源加载类
 */
public class ResourceLoader {

    public Resource getResource(String location){
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
