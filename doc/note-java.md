# note-java
java笔记

## summary

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

