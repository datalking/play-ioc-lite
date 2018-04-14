package com.github.datalking.context.annotation;

import com.github.datalking.annotation.Bean;
import com.github.datalking.annotation.ComponentScan;
import com.github.datalking.annotation.Configuration;
import com.github.datalking.bean.HelloWorld;
import com.github.datalking.bean.strbean.BeanStr01;
import com.github.datalking.context.ApplicationContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Configuration
@ComponentScan(basePackages = "com.github.datalking.bean.intbean,com.github.datalking.bean.strbean")
public class AnnoComponentScanTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Bean
    public HelloWorld helloWorld() {
        return new HelloWorld();
    }

    @Test
    public void testComponentScanBasePackages() throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AnnoComponentScanTest.class);

        BeanStr01 bean = (BeanStr01) ctx.getBean("beanStr01");
        bean.setName("...");
        assertEquals("...", bean.getName());

        HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
        helloWorld.setMessage("Hello World!");
        assertEquals("Hello World!", helloWorld.getMessage());

    }


    @Test
    public void testComponentScanBasePackageClasses() throws Exception {
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(AnnoComponentScanTest.class);
//        BeanStr01 bean = (BeanStr01) ctx.getBean("beanStr01");
//        bean.setName("beanStr01 0414");
//        System.out.println(bean);
    }

}
