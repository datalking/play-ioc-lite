package com.github.datalking.context.annotation;

import com.github.datalking.annotation.Bean;
import com.github.datalking.annotation.Component;
import com.github.datalking.annotation.ComponentScan;
import com.github.datalking.annotation.Configuration;
import com.github.datalking.bean.HelloWorld;
import com.github.datalking.bean.HelloWorldComponent;
import com.github.datalking.context.ApplicationContext;
import com.github.datalking.context.annotation.AnnotationConfigApplicationContext;
import com.github.datalking.util.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;

/**
 * AnnotationConfigApplicationContext Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 9, 2018</pre>
 */
@Configuration
public class AnnotationConfigApplicationContextTest {

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
    public void testAnnoConfigurationBean() throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotationConfigApplicationContextTest.class);
        HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
        helloWorld.setMessage("Hello World!");
        assertEquals("Hello World!", helloWorld.getMessage());
    }

    /**
     * 指定扫描的包时，只能扫描@Component
     */
    @Test
    public void testScanBasePackageInput() throws Exception {
        String basePackage = "com.github.datalking.bean";
        ApplicationContext ctx = new AnnotationConfigApplicationContext(basePackage);
        HelloWorldComponent helloWorld = (HelloWorldComponent) ctx.getBean("helloWorldComponent");
        helloWorld.setMessage("...");
        assertEquals("...", helloWorld.getMessage());

    }

    /**
     * Method: scan(String... basePackages)
     */
    @Test
    public void testScan() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: register(Class<?>... annotatedClasses)
     */
    @Test
    public void testRegister() throws Exception {
//TODO: Test goes here...
    }


}
