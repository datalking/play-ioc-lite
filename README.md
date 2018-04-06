# play-ioc-lite   
>简单可控的IoC容器  

## target
- 专注于纯粹的依赖注入功能
- 使用方式与Spring相同，模仿了spring的源码
- lite版不支持aop，[play-ioc](https://github.com/datalking/play-ioc)支持aop

## overview
- 支持从xml中读取bean配置
- 支持从注解中读取bean配置
- ApplicationContext加载bean采用立即初始化
- 仅支持单例bean
- 目前暂不支持：
    - 不支持为bean指定别名
    - 不支持xml中指定扫描指定包，仅支持注解扫描指定包
    - 不支持构造注入与方法注入，仅支持属性注入
    - 不支持创建和使用除 `spring常用配置标签` 外的自定义xml标签
    - 不支持将嵌套子元素作为属性，仅支持统一使用扁平方式指定属性  
        - `<property name="id" value="helloId"/>`
        - [x] `<property name="id"> <value="helloId"></value> </property>`   
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

- [ ] 支持ref为 object   
- [ ] 解决循环依赖问题   
- [ ] getBean By class   
- [ ] 属性默认为字符串，实现基本类型自动转换   
- [ ] 扫描指定包的bean   
- [ ] xml bean元素支持id   
- [ ] xml 支持constructor-args元素   
- [ ] 支持别名   
- [ ] 处理嵌套bean的问题   
- [ ] xml中同名bean抛出异常   
- [ ] 注解支持 `@Named`, `@Injected`   

- [x] ApplicationContext默认立即初始化   
- [x] 抽象出 BeanDefinition 作为接口   
- [x] 抽象出 PropertyValues 作为接口   
- [x] 基本IoC

## later
- [ ] BeanAware接口   
- [ ] BeanWrapper用于属性类型转换，暂未使用   
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

- BeanDefinition保存beanClass实际对象和属性
- BeanDefinitionReader读取bean配置  
    - 存储到DefaultListableBeanFactory的beanDefinitionMap
    - 此时bean未实例化

## License

[MIT](http://opensource.org/licenses/MIT)




