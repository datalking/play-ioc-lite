package com.github.datalking.context;

import com.github.datalking.bean.BeanAllStr;
import com.github.datalking.bean.DataAnalyst;
import com.github.datalking.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class ApplicationContextTest {

    @Test
    public void test() throws Exception {

//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-primitive.xml");
        BeanAllStr beanAllStr = (BeanAllStr) applicationContext.getBean("beanAllStr");
        System.out.println(beanAllStr);

        DataAnalyst dataAnalyst = (DataAnalyst) applicationContext.getBean("dataAnalyst");
        System.out.println(dataAnalyst);

//        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
//        helloWorldService.helloWorld();

    }

}
