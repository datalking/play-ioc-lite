package com.github.datalking.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Resource是统一定位资源的接口
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
