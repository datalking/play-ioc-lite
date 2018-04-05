package com.github.datalking.io;

import java.net.URL;

/**
 * 资源加载类
 */
public class ResourceLoader {

    /**
     * 加载资源
     *
     * @param location 资源路径
     * @return 加载完资源的Resource对象
     */
    public Resource getResource(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }

}
