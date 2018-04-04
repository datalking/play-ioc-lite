# faq for play-ioc

- 同时使用xml和注解注入bean的属性，谁的优先级更高？  
    - 当出现两个相同名称实例，spring会覆盖其中一个，xml优先级高于注解  
    - xml中同时配置两个相同id的bean，直接校验不通过报错
    -  多次注入同一个bean的，如果beanName不一样的话，那么会产生两个Bean；如果beanName一样的话，后面注入的会覆盖前面的
- spring对于没有无参构造器的bean就利用CGLIB生成实例，否则就直接反射成实例
  



