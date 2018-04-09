# note-java
java笔记

## summary

- java 反射
    - getDeclaredField()：是可以获取一个类的所有字段  
    - getField()：只能获取类的 `public` 字段 
    - getDeclaredAnnotations()：获取元素上的所有注解，该方法将忽略继承的注解，如果没有注释直接存在于此元素上，则返回长度为零的一个数组
    - getAnnotations()：返回该程序元素上存在的所有注解
- java 字符串 替换
    - replace函数只实现简单的替换功能，默认替换所有
    - replaceAll函数实现了正则表达式替换功能。
- java 安全管理器
    - AccessController.doPrivileged意思是这个是特别的,不用做权限检查，使用场景：
        - 假如1.jar中有类可以读取一个文件，但是我们的类本生是没有权限去读取那个文件的，在1.jar中如果读取文件的方法是通过doPrivileged来实现的.就不会有后面的检查了
- java 类加载
    - java中class.forName()和classLoader都可用来对类进行加载
    - class.forName()前者除了将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块。
    - classLoader只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容,只有在newInstance才会去执行static块。
    
- 方法的参数为lambda时，会先进入方法内，再计算lambda参数

- java读取xml `DocumentBuilderFactory.newInstance().newDocumentBuilder().parse('file.xml')`
    - Node接口是代表了文档树中的抽象节点，多使用Node子对象Element,Attr,Text
    - xml元素类型判断
    ```xml
    <beans top="beansAttr">
        <bean name="beanAllStr" class="com.github.datalking.bean.BeanAllStr">
            <property name="id" value="helloId"/>
        </bean>
        <bean name="dataAnalyst" class="com.github.datalking.bean.DataAnalyst">
            <property name="name" value="helloName"/>
        </bean>
    </beans>
    ```  
    根节点有5个childNode，3个Text，2个Element

