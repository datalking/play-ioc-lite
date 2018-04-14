package com.github.datalking.context;

import com.github.datalking.bean.BeanAllStr;
import com.github.datalking.bean.DataAnalyst;
import com.github.datalking.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApplicationContextTest {


    @Test
    public void testBeanAllStr() throws Exception {

//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-primitive.xml");
        BeanAllStr beanAllStr = (BeanAllStr) applicationContext.getBean("beanAllStr");
        //System.out.println(beanAllStr);
        assertEquals("helloName", beanAllStr.getName());


        DataAnalyst dataAnalyst = (DataAnalyst) applicationContext.getBean("dataAnalyst");
        assertEquals("22", dataAnalyst.getAge().toString());

//        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
//        helloWorldService.helloWorld();

    }


}
