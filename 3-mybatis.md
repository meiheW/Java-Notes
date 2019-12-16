# MyBatis基础  

## 基本概念  

### 1.概念  
&emsp;&emsp;MyBatis是一个持久层框架，它对jdbc的操作数据库的过程进行封装。通过xml或注解的方式将要执行的各种statement（statement、preparedStatemnt、CallableStatement）配置起来，并通过java对象和statement中的sql进行映射生成最终执行的sql语句，最后由mybatis框架执行sql并将结果映射成java对象并返回。


### 2.核心组件  

* SqlSessionFactoryBuilder（构造器）：根据配置或者代码生成SqlSessionFactory；

* SqlSessionFactory（工厂接口）：生成SqlSession；

* SqlSession（会话）：发送Sql执行返回结果，获取map接口；

* Sql Mapper（映射器）；java接口和xml配置或者注解构成，发送sql并执行返回结果。


### 3.生命周期

* SqlSessionFactoryBuilder（构造器）：创建SqlSessionFactory的方法中；

* SqlSessionFactory（工厂接口）：存在于整个MyBatis应用中；

* SqlSession（会话）：一个业务请求中；

* Sql Mapper（映射器）；由SqlSession创建，短于SqlSession的周期。

### 4.映射器 
&emsp;&emsp;映射器是MyBatis中最重要最复杂的组件，由一个接口和对应的xml文件（或注解）组成。  
&emsp;&emsp;映射器可以：1.映射规则；2.Sql语句；3.配置缓存；4.动态Sql。

POJO类
```java
package com.tomster.mybatis.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author meihewang
 * @date 2019/11/17  2:01
 */
public class User implements Serializable{
    private int id;
    private String username;
    private String sex;
    private Date birthday;
    private String address;
    /*getter and setter*/
}
```
#### 4.1 xml实现映射 
mapper接口
```java 
import com.tomster.mybatis.vo.QueryVo;

import java.util.List;

/**
 * @author meihewang
 * @date 2019/11/18  23:20
 */
public interface UserMapper {
    public User findUserById(int id);
}
```

UserMapper.xml
```xml
<mapper namespace="com.tomster.mybatis.mapper.UserMapper">
    <select id="findUserById" parameterType="int" resultType="com.tomster.mybatis.po.User">
        SELECT * FROM user WHERE id = #{id}
    </select>
</mapper>
```
SqlMapConfig.xml
```xml
<!--mybatis加载映射文件-->
<mappers>
    <!--指定xml映射文件-->
    <mapper resource="com/tomster/mybatis/sqlmap/UserMapper.xml"></mapper>
</mappers>
```
开发规范  
* mapper接口的全限定名要和mapper映射文件的namespace的值相同;
* mapper接口的方法名称要和mapper映射文件中的statement的id相同;
* mapper接口的方法参数只能有一个，且类型要和mapper映射文件中statement的parameterType的值保持一致;
* mapper接口的返回值类型要和mapper映射文件中statement的resultType值或resultMap中的type值保持一致。

#### 4.2 注解实现映射  
```java
public interface UserMapper {
    @Select(SELECT * FROM user WHERE id = #{id})
    public User findUserById(int id);
}
```
推荐使用xml配置方法，因为默认都有的话，xml会覆盖掉注解；并且联表查询sql非常长，注解方式可读性较差；如果是动态sql就更复杂了。

### 5.SqlSessionFactory 和 SqlSession
&emsp;&emsp;MyBatis使用依赖于SqlSessionFactory，提供了构造器SqlSessionFactoryBuilder来通过代码或者xml配置来构建。每一个基于MyBatis的应用都是以一个SqlSessionFactory的实例为中心的，其唯一的作用就是生产MyBatis的核心接口对象SqlSession.  
&emsp;&emsp;SqlSession是MyBatis核心接口，有两个实现类，DefaultSqlSession和SqlSessionManager，前者是单线程使用，后者在多线程使用。SqlSession类似于JDBC中的Connection对象，代表数据库连接资源的启用。它的作用是：1.获取Mapper接口；2.发送Sql给数据库；3.控制数据库事务。

#### 5.1 SqlSessionFactory
SqlMapConfig.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="db.properties"/>
    <typeAliases>
        <package name="com.tomster.mybatis.vo"></package>
        <package name="com.tomster.mybatis.po"></package>
    </typeAliases>
    <!-- 配置mybatis的环境信息 -->
    <environments default="development">
        <environment id="development">
            <!-- 配置JDBC事务控制，由mybatis进行管理 -->
            <transactionManager type="JDBC"></transactionManager>
            <!-- 配置数据源，采用dbcp连接池 -->
            <dataSource type="POOLED">
                <property name="driver" value="${driverClass}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${name}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
    <!--mybatis加载映射文件-->
    <mappers>
        <!--指定xml映射文件-->
        <mapper resource="com/tomster/mybatis/sqlmap/UserMapper.xml"></mapper>
    </mappers>
</configuration>
```
通过xml构建SqlSessionFactory

```java
//1.读取配置文件
InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
//2.创建SqlSessionFactory会话工厂
SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
```  
加载映射文件的方式  
```xml
<!--mybatis加载映射文件-->
<mappers>
    <!--指定xml映射文件-->
    <mapper resource="com/tomster/mybatis/sqlmap/UserMapper.xml"></mapper>
    <!--指定mapper类文件，须与映射文件位于同一包内-->
    <mapper class="com.tomster.mybatis.mapper.UserMapper"/>
    <!--指定包，mapper与映射文件位于同一包内-->
    <package name="com.tomster.mybatis.mapper"/>
</mappers>
```

#### 5.2 SqlSession
打开session
```java
//1.读取配置文件
InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
//2.创建SqlSessionFactory会话工厂
SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
//3.创建SqlSession
sqlSession = ssf.openSession();
```
sqlSession发送sql
```java
//4.调用SqlSession的操作数据库方法
User user = sqlSession.selectOne("findUserById", 10);
```
mapper接口发送sql
```java
UserMapper mapper = sqlSession.getMapper(UserMapper.class);
User user = mapper.findUserById(10);
```
使用mapper接口编程不仅可以提高可读性，体现业务逻辑，还在编译器检查参数类型等等。

## 配置


## 映射  

### 1.参数传递的方式
参数传递有三种方式：使用map类型；使用@param注解；JAVA BEAN。
xml映射文件
```xml
<!--1.使用map类型-->
<select id="findUserByMap" parameterType="map" resultType="com.tomster.mybatis.po.User">
    SELECT * FROM user WHERE sex = #{sex} AND username like concat('%',#{username},'%')
</select>
<!--2.使用注解传递多个参数-->
<select id="findUserByParam" resultType="com.tomster.mybatis.po.User">
    SELECT * FROM user
    WHERE sex = #{sex}
    AND username like concat('%',#{username},'%')
    AND address = #{address}
</select>
<!--3.通过Java Bean传递多个参数-->
<select id="countUser" parameterType="com.tomster.mybatis.po.User" resultType="int">
    SELECT COUNT(*) FROM user WHERE username like '%${username}%' and sex = #{sex};
</select>
```
mapper接口
```java
public List<User> findUserByMap(Map<String, Object> parameterMap);
public List<User> findUserByParam(@Param("username") String username,
                                  @Param("sex") String sex,
                                  @Param("address") String address);
public int countUser(User user);
```
sqlSsseion调用mapper接口
```java
//map
Map<String, Object> map = new HashMap<>();
map.put("sex", 1);
map.put("username", "张");
List<User> userList = mapper.findUserByMap(map);
//注解
List<User> userByParam = mapper.findUserByParam("wmh", "1", "江苏扬州");
//pojo
User user = new User();
user.setUsername("小");
user.setSex("1");
int count = mapper.countUser(user);
```


### 2.结果集映射 
resultType支持基本数据类型,map，pojo等

#### 2.1使用map存储结果集
映射文件
```xml
<select id="findMapUserByMap" parameterType="map" resultType="map">
    SELECT * FROM user WHERE sex = #{sex} AND username like concat('%',#{username},'%')
</select>
```
mapper接口
```java
public List<Map<String, Object>> findMapUserByMap(Map<String, Object> parameterMap);
```

#### 2.2使用pojo存储结果集 
映射文件
```xml
<!--定义resultMap-->
<resultMap id="userResultMap" type="com.tomster.mybatis.po.User">
    <id property="id" column="id"></id>
    <result property="username" column="username"></result>
    <result property="birthday" column="birthday"></result>
    <result property="sex" column="sex"></result>
    <result property="address" column="address"></result>
</resultMap>
<!--使用resultMap作为输出类型-->
<select id="findUserResultMap" parameterType="int" resultMap="userResultMap">
    SELECT * FROM user WHERE id = #{id}
</select>
```
mapper接口
```
public User findUserResultMap(int id);
```
resultType指定pojo类型时只映射名称与属性相同的列名，可以通过resultMap来进行pojo属性与表列名的关系对应。


### 3.级联
pojo
```java
package com.tomster.mybatis.po;

import java.util.Date;

/**
 * @author meihewang
 * @date 2019/11/25  23:31
 */
public class Order {
    private int id;
    private String number;
    private String userId;
    private Date createtime;
    private String note;
    //getter and setter
}
```

```java
package com.tomster.mybatis.po;

/**
 * @author meihewang
 * @date 2019/11/25  23:33
 */
public class OrderExt extends Order{

    private String username;
    private String address;
}
```

映射文件
```xml
<select id="findOrderById" parameterType="int" resultType="orderExt">
    SELECT o.*,u.username,u.address from orders o
    left JOIN `user` u  ON u.id=o.user_id
    where o.id=#{id};
</select>
```
mapper接口
```java
public OrderExt findOrderById(@Param("id") int id);
```

## 动态sql

### if - 判断语句，与test属性联合使用
```xml
<select id="userList" parameterType="com.tomster.mybatis.po.User" resultType="com.tomster.mybatis.po.User">
    SELECT * FROM USER where 1=1
    <if test="username!=null">and username like '%${username}%'</if>
    <if test="sex!=null">and sex=#{sex}</if>
</select>
```

### where - 方便组装查询条件
```xml
<select id="userList" parameterType="com.tomster.mybatis.po.User" resultType="com.tomster.mybatis.po.User">
    SELECT * FROM USER
    <where>
        <if test="username!=null">and username like '%${username}%'</if>
        <if test="sex!=null">and sex=#{sex}</if>
    </where>
</select>
```

### foreach - 遍历集合，用于SQL的in关键字
```xml
<select id="userListByIds" parameterType="com.tomster.mybatis.vo.QueryVo" resultType="com.tomster.mybatis.po.User">
    SELECT * FROM USER
    <where>
        <if test="ids!=null and ids.size>0">
            <foreach collection="ids" item="id" open="id in (" close=")" separator=",">
                ${id}
            </foreach>
        </if>
    </where>

    <!--与上面的查询相同，缺少了判断条件-->
    <!--SELECT * FROM USER where id in
    <foreach collection="ids" item="id" open="(" close=")" separator=",">
        ${id}
    </foreach>-->
</select>
```
### bind - 动态sql不必使用数据库语言（concat-||）
```xml
<select id="userList" parameterType="com.tomster.mybatis.po.User" resultType="com.tomster.mybatis.po.User">
    SELECT * FROM USER
    <bind name="UserPattern" value="'%'+username+'%'"></bind>
    <where>
        <if test="username!=null">and username like #{UserPattern}</if>
        <if test="sex!=null">and sex=#{sex}</if>
    </where>
</select>
```

## Mybatis与Spring的整合
mybatis的配置文件sqlMapConfig.xml需要配置数据源以及映射文件的路径，与spring整合将数据源的配置放到applicationContext.xml里面，由spring来设置数据源、会话工厂和mapper。需要spring包mybatis包以及mybatis-spring包。

applicationContext.xml  
```xml


<!-- 1.配置数据库，dbcp数据库连接池-->
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useUnicode=true&amp;characterEncoding=utf8"/>
    <property name="username" value="root"/>
    <property name="password" value="123456"/>
    <!-- 最大连接  -->
    <property name="maxActive" value="10"/>
    <!--最大空闲数  -->
    <property name="maxIdle" value="5"/>
</bean>

<!-- 2.配置会话工厂-->
<bean id="sessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <property name="configLocation" value="classpath:SqlMapConfig.xml"/>
</bean>

<!-- ===============配置dao的几种方式=================-->
<!--由spring创建一个userMapper对象,使用工厂来创建-->
<bean id="userMapper" class="org.mybatis.spring.mapper.MapperFactoryBean">
<property name="sqlSessionFactory" ref="sessionFactory"/>
<property name="mapperInterface" value="com.tomster.backoffice.mapper.UserMapper"/>
</bean>


```

sqlmapConfig.xml
```xml
<!--配置别名-->
<typeAliases>
    <package name="com.tomster.backoffice.po"></package>
</typeAliases>

<!--加载映射文件-->
<mappers>
    <mapper resource="com/tomster/backoffice/sqlmap/UserMapper.xml"></mapper>
</mappers>
```

调用方法
```java
//1.加载spring的配置文件
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//2.获取userMapper
UserMapper userMapper = (UserMapper) context.getBean("userMapper");
//3.调用dao方法
User user = userMapper.findUserById(1);
```
整合后由spring来创建mapper的实例，以前是通过mybatis的sqlSession来获取mapper的，现在是在applicationContext.xml通过配置由工厂来创建的。不过每一个mapper都需要配置工厂来生成，可以使用指定包扫描来创建。
```xml
<!--由spring创建一个userMapper对象,使用工厂来创建-->
<bean id="userMapper" class="org.mybatis.spring.mapper.MapperFactoryBean">
<property name="sqlSessionFactory" ref="sessionFactory"/>
<property name="mapperInterface" value="com.tomster.backoffice.mapper.UserMapper"/>
</bean>
<!-- 批量创建mapper的bean对象
    内部会扫描指定包下的mapper,创建代理对象,名字就是类名，头字母改小写
-->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="com.tomster.backoffice.mapper"/>
    <property name="sqlSessionFactoryBeanName" value="sessionFactory"/>
</bean>
```
