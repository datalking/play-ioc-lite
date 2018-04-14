//package com.github.datalking.bean;
//
//import com.github.datalking.annotation.Bean;
//import com.github.datalking.annotation.ComponentScan;
//import com.github.datalking.annotation.Configuration;
//import com.github.datalking.bean.strbean.BeanStr01;
//import com.github.datalking.context.ApplicationContext;
//import com.github.datalking.context.annotation.AnnotationConfigApplicationContext;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//@Configuration
//@ComponentScan
//public class AnnoComponentScanNoValueTest {
//
//    @Before
//    public void before() throws Exception {
//    }
//
//    @After
//    public void after() throws Exception {
//    }
//
//
//    @Bean
//    public HelloWorld helloWorld() {
//        return new HelloWorld();
//    }
//
//    @Test
//    public void testComponentScanNoValue() throws Exception {
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(AnnoComponentScanNoValueTest.class);
//        BeanStr01 bean = (BeanStr01) ctx.getBean("beanStr01");
//        bean.setName("beanStr01 20180414");
//        System.out.println(bean);
//    }
//
//
//}
