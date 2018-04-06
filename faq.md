# faq for play-ioc


## BeanDefinition
- GenericBeanDefinition暂不支持父子结构，短期内不会实现
- RootBeanDefinition定义表明它是一个可合并的bean definition，不会单独实现，使用GenericBeanDefinition也可实现  


## 说明
- BeanFactory默认懒加载，只有调用getBean()时才创建实例 
- ApplicationContext默认立即加载，通过主动调用getBean()来实现，只有ApplicationContext才能选择是否开启懒加载
- 同时使用xml和注解注入bean的属性，谁的优先级更高？  
    - 当出现两个相同名称实例，spring会覆盖其中一个，xml优先级高于注解  
    - xml中同时配置两个相同id的bean，直接校验不通过报错
    -  多次注入同一个bean的，如果beanName不一样的话，那么会产生两个Bean；如果beanName一样的话，后面注入的会覆盖前面的
- spring对于没有无参构造器的bean就利用CGLIB生成实例，否则就直接反射成实例
  



