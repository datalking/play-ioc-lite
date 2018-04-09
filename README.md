# play-ioc-lite   
>简单可控的IoC容器  

## target
- 专注于纯粹的依赖注入功能
- 使用方式与Spring相同，模仿了spring的源码
- lite版不支持aop，[play-ioc](https://github.com/datalking/play-ioc)支持aop

## overview
- 支持从xml中读取bean配置
- 支持从注解中读取bean配置
- ApplicationContext加载bean采用立即初始化，暂不支持懒加载控制
- 仅支持单例bean，不支持多实例
- 目前暂不支持：
    - 不支持将bean的value类型配置为set,list,map，仅支持字符串和ref  
    - 不支持为bean指定别名
    - 不支持xml中指定扫描指定包，仅支持注解扫描指定包
    - 不支持构造注入与方法注入，仅支持属性注入
    - 不支持创建和使用除 `play-ioc支持的xml配置说明` 外的自定义xml标签
    - 不支持将嵌套子元素作为属性，仅支持统一使用扁平方式指定属性  
        - 不支持innerBean的写法，建议扁平化定义bean，使用ref属性引用其他bean
        - `<property name="id" value="helloId"/>`
        - [x] `<property name="id"> <value="helloId"></value> </property>`   
        - [x] `<property name="person"> <bean class="a.b.Person.class"></bean> </property>`   
    - 不支持xml格式校验和属性名校验，请手动检查
    - 不支持FactoryBean
    - 不支持属性编辑器，仅自动转换基本类型对象，需要自行处理Date、File等字段
    - ...

- 参考了 `org.springframework.beans` 包的实现和项目[tiny-spring](https://github.com/code4craft/tiny-spring)

## dev 
```sh
 git clone https://github.com/datalking/play-ioc.git
 cd play-ioc/
 ./start-build-dev.sh
```

## demo
```sh
  todo
```

start from [http://localhost:8999](http://localhost:8999)

## todo


- [ ] getBean By class   
- [ ] 属性默认为字符串，实现基本类型自动转换   
- [ ] 扫描指定包的bean   
- [ ] xml bean元素支持id   
- [ ] xml 支持constructor-args元素   
- [ ] 支持BeanPostProcessor   
- [ ] 支持别名   
- [ ] 处理嵌套bean的问题   
- [ ] xml中同名bean抛出异常   
- [ ] 注解支持 `@Named`, `@Injected`   
- [ ] 解决多重嵌套依赖问题   

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

## Architecture

- GenericBeanDefinition保存beanClass实际对象和属性
- BeanDefinitionReader读取bean配置  
    - 存储到DefaultListableBeanFactory的beanDefinitionMap
    - 此时bean所对应的class未加载，也未实例化
- play-ioc支持的xml配置说明
    - 顶层标签为 `<beans>`
    - `<bean>` 元素可配置 id,name,class属性，class必须，id和name都可选

## License

[MIT](http://opensource.org/licenses/MIT)




