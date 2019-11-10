# Spring基础  

## 基本概念  

### 1.概念  
&emsp;&emsp;Spring是一个开源框架，Spring是于2003 年兴起的一个轻量级的Java 开发框架，由Rod Johnson 在其著作Expert One-On-One J2EE Development and Design中阐述的部分理念和原型衍生而来。它是为了解决企业应用开发的复杂性而创建的。Spring使用基本的JavaBean来完成以前只可能由EJB完成的事情。然而，Spring的用途不仅限于服务器端的开发。从简单性、可测试性和松耦合的角度而言，任何Java应用都可以从Spring中受益。简单来说，Spring是一个轻量级的控制反转（IoC）和面向切面（AOP）的容器框架。  

### 2.优势  
a. 方便解耦，简化开发：专门负责生成Bean；

b. AOP编程的支持：Spring提供面向切面编程，声明式事务的支持；

c. 方便程序的测试：对Junit4支持，可以通过注解方便的测试Spring程序；

d. 方便集成各种优秀框架：其内部提供了对各种优秀框架，如：Struts、Hibernate、MyBatis等； 

e. 降低JavaEE API的使用难度：对JavaEE开发中一些难用的API都提供了封装，如JDBC、JavaMail、远程调用webservice等。

### 3.架构  
<div align="center">
	<img src="img/spring-structure.jpg" width="150px">
</div>





