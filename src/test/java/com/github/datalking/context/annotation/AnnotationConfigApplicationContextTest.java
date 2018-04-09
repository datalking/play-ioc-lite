package test.com.github.datalking.context.annotation;

import com.github.datalking.annotation.Bean;
import com.github.datalking.annotation.Configuration;
import com.github.datalking.bean.HelloWorld;
import com.github.datalking.context.ApplicationContext;
import com.github.datalking.context.annotation.AnnotationConfigApplicationContext;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

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
    public void testAnnoConfigurationBeans() throws Exception {
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotationConfigApplicationContextTest.class);
//        HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
//        helloWorld.setMessage("Hello World!");
//        helloWorld.getMessage();
    }

    @Test
    public void testAnnoScanBeans() throws Exception {
        String basePackage = "com.github.datalking.bean";
        ApplicationContext ctx = new AnnotationConfigApplicationContext(basePackage);
        HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
        helloWorld.setMessage("Hello World2!");
        helloWorld.getMessage();
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
