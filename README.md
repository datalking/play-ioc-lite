# play-ioc-lite   
>简单可控的IoC容器 Lite版  

## target
- 专注于实现纯粹的依赖注入
- 使用方式与spring相同，精简了spring的源码
- lite版不支持aop，推荐使用支持aop的[play-ioc](https://github.com/datalking/play-ioc)

## overview
- 支持从xml中读取bean配置
- 支持从注解中读取bean配置，目前支持的注解：`@Configuration`、`@Bean`、`@ComponentScan`、`@Component`
- ApplicationContext加载bean默认采用立即初始化，DefaultListableBeanFactory默认采用懒加载
- 仅支持单例bean，不支持多实例
- 目前暂不支持：
    - 不支持将bean的value类型配置为set,list,map，仅支持字符串和ref  
    - 不支持为bean指定别名
    - 不支持xml中指定扫描指定包，仅支持注解扫描指定包
    - 不支持构造注入与方法注入，仅支持属性注入
    - 不支持创建和使用除 `play-ioc支持的xml配置说明` 外的自定义xml标签
    - 不支持将嵌套子元素作为属性，仅支持统一使用扁平方式指定属性  
        - 不支持innerBean的写法，建议扁平化定义bean，使用ref属性引用其他bean
        - `<property name="fieldName" value="valueHere"/>`
        - ~~`<property name="fieldName"> <value="valueHere"></value> </property>`~~   
        - ~~`<property name="person"> <bean class="a.b.Person.class"></bean> </property>`~~   
    - 不支持xml格式校验和属性名校验，请手动检查
    - 不支持属性编辑器，默认自动转换基本类型对象，需要在源码上定制处理Date、File等字段
    - ...

- 参考了 `org.springframework.beans` 包的实现和项目[tiny-spring](https://github.com/code4craft/tiny-spring)

## dev 
```sh
 git clone https://github.com/datalking/play-ioc-lite.git
 cd play-ioc-lite/
 ./start-build-dev.sh
```

## demo
#### 使用 [AnnotationConfigApplicationContext](https://github.com/datalking/play-ioc-lite/blob/master/src/test/java/com/github/datalking/context/annotation/AnnotationConfigApplicationContextTest.java)
```
// 指定要扫描的包名
ApplicationContext ctx = new AnnotationConfigApplicationContext("com.github.datalking.bean");
BeanAllStr beanAllStr = (BeanAllStr) applicationContext.getBean("beanAllStr");
System.out.println(beanAllStr);
```
#### 使用 [ClassPathXmlApplicationContext](https://github.com/datalking/play-ioc-lite/blob/master/src/test/java/com/github/datalking/context/ApplicationContextTest.java)
```
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-primitive.xml");
BeanAllStr beanAllStr = (BeanAllStr) applicationContext.getBean("beanAllStr");
System.out.println(beanAllStr);
```

#### 使用 [DefaultListableBeanFactory](https://github.com/datalking/play-ioc-lite/blob/master/src/test/java/com/github/datalking/beans/BeanFactoryTest.java)
```
// 初始化BeanFactory
AbstractBeanFactory beanFactory = new DefaultListableBeanFactory();
// 读取配置
XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) beanFactory);
xmlBeanDefinitionReader.loadBeanDefinitions("beans-primitive.xml");
BeanAllStr beanAllStr = (BeanAllStr) beanFactory.getBean("beanAllStr");
System.out.println(beanAllStr);
```

## todo

- [ ] getBean By class   
- [ ] xml 支持constructor-args元素   
- [ ] 支持BeanPostProcessor   
- [ ] 支持别名   
- [ ] 注解支持 `@Named`, `@Injected`   
- [ ] 处理嵌套bean的问题   
- [ ] 解决多重嵌套依赖问题   
- [ ] xml中同名bean抛出异常   
- [ ] 扫描指定包时利用asm实现所有子包.class文件的不加载，最初是预加载指定包获取bean信息   

- [x] 支持@ComponentScan 
- [x] 手动注册bean生成BeanDefinition: @Configuration  @Bean 
- [x] 扫描指定包带有@Component注解的bean   
- [x] xml bean元素支持id   
- [x] 属性默认为字符串，实现基本类型自动转换   
- [x] 支持ref为 object   
- [x] 解决二重循环依赖问题   
- [x] ApplicationContext默认立即初始化   
- [x] 抽象出 BeanDefinition 作为接口   
- [x] 抽象出 PropertyValues 作为接口   
- [x] 基本IoC

## later
- [ ] bean销毁的声明周期   
- [ ] BeanAware接口   
- [ ] BeanWrapper用于属性类型转换，暂未使用   
- [ ] 支持将bean的value类型配置为set,list   
- [ ] PropertyValues不支持合并   
- [ ] 通过可选懒加载更优雅地解决bean的循环依赖问题   
- [ ] MutablePropertyValues processedProperties  
- [ ] 在xml中使用anno：loadBeanDefinition时遇到component-scan元素时会以ComponentScanBeanDefinitionParser进行解析    
```xml
<context:annotation-config/>
<context:component-scan base-package="com.wtf.demo.spring.beans"/>
<bean id="ctxConfig" class="cn.javass.spring.chapter12.configuration.ApplicationContextConfig"/>
```

- [ ] 在anno中使用xml：使用注解@ImportResource
```xml
<bean id="message3" class="java.lang.String">
    <constructor-arg index="0" value="test"></constructor-arg>
</bean>
```
```java
@Configuration  
@ImportResource("classpath:/bookstore/config/spring-beans.xml")
  public class ApplicationContextConfig { }
```   
- [ ] 迁移到以注解为主的使用方式

## user guide
- play-ioc支持的xml配置说明
    - 顶层标签为 `<beans>`
    - `<bean>` 元素可配置 id,name,class属性，class必须，id和name都可选
    - `<property>` 元素可配置 name,value,ref属性，name必须，value和ref二选一
- BeanDefinition保存bean属性元数据，包括beanClass和propertyValues
    - beanClass在xml读取阶段是字符串，在实例创建阶段是class对象
    - propertyValues存储属性键值对，在xml读取阶段是都是字符串，特殊的是ref属性会处理成RuntimeBeanReference
- BeanDefinitionReader读取bean配置  
    - 最终存储到DefaultListableBeanFactory的 `beanDefinitionMap`
    - 此时bean所对应的class可能未加载，一定未实例化，实例化一定发生在调用getBean()方法时
- AbstractAutowireCapableBeanFactory的doCreateBean()方法会创建bean实例
    - bean实例最终保存到DefaultSingletonBeanRegistry的 `singletonObjects` 
- 注解相关
    - 使用注解时，要用 `@Configuration`，表示一个配置类，相当于配置文件的 `<beans>`，同一个类上还可以配置 `@ComponentScan` 
    - @ComponentScan不设置值时，默认扫描该类所在的包及所有子包

## License
[MIT](http://opensource.org/licenses/MIT)




