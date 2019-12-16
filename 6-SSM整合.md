首先需要在web.xml中配置Spring和SpringMVC的配置文件。  
  
web.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app
        version="3.0"
        xmlns="http://java.sun.com/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd">
  <display-name>Archetype Created Web Application</display-name>


  <!-- 配置spring-->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:applicationContext.xml</param-value>
  </context-param>
  <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>

  <!-- 配置springmvc-->
  <servlet>
    <servlet-name>DispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:springmvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>

  <servlet-mapping>
    <servlet-name>DispatcherServlet</servlet-name>
    <url-pattern>*.do</url-pattern>
  </servlet-mapping>

</web-app>
``` 
SpringMVC.xml
```xml
<!-- 1.注解扫描位置-->
<context:component-scan base-package="com.tomster.edu.web.controller" />

<!-- 2.配置注解驱动 -->
<mvc:annotation-driven/>

<!-- 2.配置映射处理器和适配器-->
<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping"/>
<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter"/>

<!-- 3.视图的解析器-->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/WEB-INF/Modules/" />
    <property name="suffix" value=".jsp" />
</bean>
```
注解驱动的目的是提供Controller请求转发，json自动转换等功能，会自动注册RequestMappingHandlerMapping与RequestMappingHandlerAdapter两个Bean,这是Spring MVC为@Controller分发请求所必需的。配置注解驱动，就不要手动配置映射处理器和适配器。

applicationContext.xml
```xml
<!-- 配置文件 -->
<context:property-placeholder location="classpath:db.properties"/>

<!-- 配置数据源 -->
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <property name="driverClass" value="${jdbc.driver}"/>
    <property name="jdbcUrl" value="${jdbc.url}"/>
    <property name="user" value="${jdbc.username}"/>
    <property name="password" value="${jdbc.password}"/>
    <property name="maxPoolSize" value="30"/>
    <property name="minPoolSize" value="2"/>
</bean>

<!-- 配置sessionFactory -->
<bean id="sqlSessionFactoryBean" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <!-- 指定配置文件位置 -->
    <!--<property name="configLocation" value="classpath:mybatis.xml"/>-->
    <!--配置别名-->
    <property name="typeAliasesPackage" value="com.tomster.edu.model"/>
    <!--配置加载映射文件 UserMapper.xml-->
    <property name="mapperLocations" value="classpath:com/tomster/edu/xml/*.xml"/>
</bean>

<!-- 自动生成dao,mapper-->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="com.tomster.edu.mapper"/>
    <property name="sqlSessionFactoryBeanName" value="sqlSessionFactoryBean"/>
</bean>

<!--自动扫描Service-->
<context:component-scan base-package="com.tomster.edu"/>

<!-- 配置事务-->
<!-- 5.配置事务管理器 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"/>
</bean>
<!-- 6.开启事务注解-->
<tx:annotation-driven/>
```

jdbc.properties
```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/edu
jdbc.username=root
jdbc.password=123456
```

log4j.properties
```properties
# Global logging configuration
log4j.rootLogger=DEBUG, stdout
# Console output...
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n
```
