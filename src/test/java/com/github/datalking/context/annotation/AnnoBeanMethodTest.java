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
public class AnnoBeanMethodTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Bean
    public HelloWorld helloWorld() {

        // 会自动调用无参构造函数
        return new HelloWorld();
    }

    /**
     * 结论是 工厂方法会自动调用
     */
    @Test
    public void testBeanMethod() throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AnnoBeanMethodTest.class);
        HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
        helloWorld.setMessage("Hello World!");
        assertEquals("Hello World!", helloWorld.getMessage());

    }

    @Test
    public void testBeanMethodDefaultConstrutor() throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AnnoBeanMethodTest.class);
        HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
        System.out.println(helloWorld.getMessage());
        assertEquals("default message is ....", helloWorld.getMessage());

    }



}
