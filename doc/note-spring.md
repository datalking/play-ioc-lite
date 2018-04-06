# note-spring
Spring笔记

## summary

- PropertyEditor  
- 由于Bean属性通过配置文档以字符串了方式为属性赋值，属性编辑器负责将这个字符串转换为属性的直接对象，如属性的类型为int，那编辑器的工作就是 `int i = Integer.parseInt("1");` 
- Spring为一般的属性类型提供了默认的编辑器，BeanWrapperImpl负责对注入的Bean进行包装化的管理，常见属性类型对应的编辑器即在该类中定义

- spring xml中id和name区别  
    - name可配置多个别名  
    - The id attribute allows you to specify exactly one id. Conventionally these names are alphanumeric ('myBean', 'fooService', etc), but may special characters as well. If you want to introduce other aliases to the bean, you can also specify them in the name attribute, separated by a comma (,), semicolon (;), or white space. 
- 重要实现模块
    - BeanDefinition
    - BeanDefinitionReader
    - Resource
    - BeanFactory
    - ApplicationContext
- BeanDefinitionReader
BeanDefinitionRegistry接口一次只能注册一个BeanDefinition，而且只能自己构造BeanDefinition类来注册。   
BeanDefinitionReader解决了这些问题，它一般可以使用一个BeanDefinitionRegistry构造，然后通过#loadBeanDefinitions（..）等方法，把“配置源”转化为多个BeanDefinition并注册到BeanDefinitionRegistry中  
可以说BeanDefinitionReader帮助BeanDefinitionRegistry实现了高效、方便的注册BeanDefinition。
- spring3个核心组件
    - core：工具包
    - context：运行环境，读取bean配置、管理bean关系
    - beans：bean定义、解析、创建
- BeanFactory
    - BeanFactory 有三个子类：ListableBeanFactory、HierarchicalBeanFactory 和 AutowireCapableBeanFactory。      
    - 最终的默认实现类是 DefaultListableBeanFactory，实现了所有的接口  
