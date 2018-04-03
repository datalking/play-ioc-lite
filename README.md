# play-ioc-lite   
>简单可控的IoC容器  

## target
- 支持IoC容器的基本功能
- 使用方式与Spring相同
- lite版不支持aop，[play-ioc](https://github.com/datalking/play-ioc)支持aop

## overview
- 支持从xml中读取bean配置
- 支持从注解中读取bean配置
- 所有bean都采用延迟实例化，是为了解决循环依赖的问题
- 仅支持单例对象 
- 目前暂不支持：
    - 不支持构造注入与方法注入，仅支持属性注入
    - 不支持...

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

- [ ] 抽象出 PropertyValues 做为接口   
- [ ] 抽象出 BeanDefinition 做为接口   
- [ ] 注解支持 `@Named`, `@Injected`   

- [x] 基本IoC

## later
- [ ] 通过可选懒加载更优雅地解决bean的循环依赖问题   

## License

[MIT](http://opensource.org/licenses/MIT)




