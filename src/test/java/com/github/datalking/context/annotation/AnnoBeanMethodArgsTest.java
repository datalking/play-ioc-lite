package com.github.datalking.context.annotation;

import com.github.datalking.annotation.Bean;
import com.github.datalking.annotation.ComponentScan;
import com.github.datalking.annotation.Configuration;
import com.github.datalking.bean.HelloWorld;
import com.github.datalking.context.ApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Configuration
@ComponentScan(basePackages = "com.github.datalking.bean")
public class AnnoBeanMethodArgsTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Bean
    public HelloWorld helloWorld() {

        // 调用有参数的构造函数
        return new HelloWorld("...");
    }


    @Test
    public void testBeanMethodConstrutorArgs() throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AnnoBeanMethodArgsTest.class);
        HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
        assertEquals("...", helloWorld.getMessage());

    }


}
