# Spring基础  

## <B>基本概念</B>  

### 1.概念  

&emsp;&emsp;Spring是一个开源框架，Spring是于2003 年兴起的一个轻量级的Java 开发框架，由Rod Johnson 在其著作Expert One-On-One J2EE Development and Design中阐述的部分理念和原型衍生而来。它是为了解决企业应用开发的复杂性而创建的。Spring使用基本的JavaBean来完成以前只可能由EJB完成的事情。然而，Spring的用途不仅限于服务器端的开发。从简单性、可测试性和松耦合的角度而言，任何Java应用都可以从Spring中受益。简单来说，Spring是一个轻量级的控制反转（IoC）和面向切面（AOP）的容器框架。  

### 2.优势  

* 方便解耦，简化开发：专门负责生成Bean；

* AOP编程的支持：Spring提供面向切面编程，声明式事务的支持；

* 方便程序的测试：对Junit4支持，可以通过注解方便的测试Spring程序；

* 方便集成各种优秀框架：其内部提供了对各种优秀框架，如：Struts、Hibernate、MyBatis等；

* 降低JavaEE API的使用难度：对JavaEE开发中一些难用的API都提供了封装，如JDBC、JavaMail、远程调用webservice等。

### 3.体系结构  
<div align="center">
	<img src="img/spring-structure.jpg" width="66%">
</div>

### 4.核心jar包  
* spring-core-xxx.jar：包含Spring框架基本的核心工具类； 

* spring-beans-xxx.jar：访问配置文件、创建和管理bean，IoC、DI相关操作； 

* spring-context-xxx.jar：在基础IoC功能上的扩展服务，此外还提供许多企业级服务的支持,如邮件服务、任务调度、JNDI定位、EJB集成、远程访问、缓存以及各种视图层框架的封装等； 

* spring-expression-xxx.jar：spring表达式语言；  


## IoC  

在配置文件中指定类文件的路径，使用property进行属性或者对象的注入。

### 1.xml配置方式  

类文件  
```java
package com.tomster.spring.service;

import org.springframework.stereotype.Service;

/**
 * @author meihewang
 * @date 2019/10/31  0:17
 */
public class UserService {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addUser() {
        System.out.println("add user: " + name);
    }
}
```

XML配置文件  
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">


    <!-- 配置一个bean对象-->
    <bean id="userService" class="com.tomster.spring.service.UserService">
        <property name="name" value="tomster"></property>
    </bean>

</beans>
```

调用方式  
```java
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
UserService userService = (UserService)applicationContext.getBean("userService");
userService.addUser();
```    

加载spring容器的三种方法：  
```java
//1.通过ClassPathXmlApplicationContext类路径加载[最常用]
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

//2.通过文件系统路径获得配置文件[绝对路径]
ApplicationContext applicationContext = new FileSystemXmlApplicationContext("E:\\projects\\Java-Notes\\spring-learning\\src\\beans.xml");

//3.BeanFactory[了解]
String path = "E:\\projects\\Java-Notes\\spring-learning\\src\\beans.xml";
BeanFactory beanFactory = new XmlBeanFactory(new FileSystemResource(path));
UserService userService = (UserService)beanFactory.getBean("userService");
userService.addUser();
```   
BeanFactory和ApplicationContext区别：

* BeanFactory：BeanFacotry是spring中比较原始的Factory。如XMLBeanFactory就是一种典型的BeanFactory，目前已经弃用了。采用延迟加载，第一次调用getBean时才会加载Bean。

* ApplicationContext：继承自BeanFactory，对其进行功能拓展，支持国际化，事件机制，载入多个context，事务和aop等等。获取实现该接口容器的实例时，就将容器内所有的Bean全部实例化。


## DI  

依赖注入可以通过XML配置方式和注解方式完成

### 1.xml配置方式  

#### 1.1 一般形式  

类文件
```java
package com.tomster.di.model;

/**
 * @author meihewang
 * @date 2019/11/03  21:55
 */
public class Student{

    private String username;
    private String password;
    private Integer age;

    //setter and getter Omitted...

    public Student() {
    }

    public Student(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Student(String username, Integer age) {
        this.username = username;
        this.age = age;
    }
}
```

XML配置文件  
```xml
<!-- 1.构造方法 -->
<!-- 通过属性名称 -->
<bean id="student1" class="com.tomster.di.model.Student">
    <constructor-arg name="username" value="wmh"></constructor-arg>
    <constructor-arg name="password" value="123456"></constructor-arg>
</bean>
<!-- 通过索引和类型 -->
<bean id="student2" class="com.tomster.di.model.Student">
    <constructor-arg index="0" value="wmh" type="java.lang.String"></constructor-arg>
    <constructor-arg index="1" value="26" type="java.lang.Integer"></constructor-arg>
</bean>


<!-- 2. setter方法 -->
<bean id="student3" class="com.tomster.di.model.Student">
    <property name="username" value="wmh"></property>
    <property name="password" value="123456"></property>
</bean>
```

构造方法可以用指定属性名称， 或者索引和类型来确定调用哪个构造函数创建Bean；  
property标签方式是直接给属性赋值，调用属性的setter方法，setter方法去掉会异常。  


#### 1.2 对象注入  

类文件  

Address.java  

```java
package com.tomster.di.model;

/**
 * @author meihewang
 * @date 2019/11/03  22:48
 */
public class Address {

    private String name;

    //setter and getter Omitted...
}
```


Customer.java  
```java
package com.tomster.di.model;

/**
 * @author meihewang
 * @date 2019/11/03  22:44
 */
public class Customer {

    private String name;
    private String sex;
    private double pi;
    private Address address;

    //setter and getter Omitted...
}
```

xml配置文件

```xml
<!--
    SpEL:spring表达式
    <property name="" value="#{表达式}">
    #{123}、#{'jack'} ： 数字、字符串
    #{T(类).字段|方法}	：静态方法或字段
    #{beanId}	：另一个bean引用
    #{beanId.propName}	：操作数据
    #{beanId.toString()}	：执行方法
-->
<bean id="address" class="com.tomster.di.model.Address">
    <property name="name" value="shanghai"></property>
</bean>

<bean id="customer" class="com.tomster.di.model.Customer">
    <property name="name" value="wmh"></property>
    <property name="sex" value="#{'male'}"></property>
    <property name="pi" value="#{T(java.lang.Math).PI}"></property>
    <!-- 一个对象注入另一个的方式 -->
    <!--<property name="address" value="#{address}"></property>-->
    <property name="address" ref="address"></property>
</bean>

```  

xml配置方式将一个对象注入另一个对象，使用ref指定注入对象的id

#### 1.3 集合注入  


```java
package com.tomster.di.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author meihewang
 * @date 2019/11/03  23:19
 */
public class Programmer {

    List<String> books;
    Map<String, String> infos;
    String[] schools;

    //setter and getter Omitted...

}

```  

xml配置文件  

```xml
<bean id="programmer" class="com.tomster.di.model.Programmer">
    <!--列表-->
    <property name="books">
        <list>
            <value>jvm</value>
            <value>java</value>
            <value>spring</value>
        </list>
    </property>
    <!--map-->
    <property name="infos">
        <map>
            <entry key="name" value="wmh"></entry>
            <entry key="age" value="26"></entry>
            <entry key="position" value="t11"></entry>
        </map>
    </property>
    <!--数组-->
    <property name="schools">
        <array>
            <value>JIT</value>
            <value>SHU</value>
        </array>
    </property>

</bean>

```
列表，map和数组注入元素分别用list,map和array，另还有set和value标签。  



### 2.注解方式  

常用注解  
* @component：取代了xml配置文件中的bean标签，默认参数表示id
* @Controller-Service-Repository：mvc三层
* @Autowired：自动根据类型注入
* @Qualifier(“名称”)：指定自动注入的id名称

xml开启注解，指定包扫描位置
```xml
    <!-- 开启注解-->
    <context:annotation-config/>
    <!-- 注解的位置-->
    <context:component-scan base-package="com.tomster"/>
```



















