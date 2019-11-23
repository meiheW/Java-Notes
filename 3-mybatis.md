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
&emsp;&emsp;映射器是MyBatis中最重要最复杂的组件，由一个接口和对应的xml文件（或注解）组成。映射器可以：1.映射规则；2.Sql语句；3.配置缓存；4.动态Sql。

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
UserMapper.xml中mapper的命名空间为mapper接口的全路径名，id为方法名，参数和返回值类型要与接口中的类型对应；SqlMapConfig.xml中mappers内包含这一个UserMapper.xml映射文件即可。

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

## 基本配置
typeAliases: 别名，代替全限定名，默认为类首字母小写；  
environment: 描述数据库，transactionManager配置事务管理器，dataSource配置数据库  
mapper: 引入的引射器   
### 1.properties

### 2.settings

### 3.typeAliases

### 4.environments


## 映射器


## 动态sql


## 基本使用

SqlMapConfig.xml  
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <properties resource="db.properties"/>
    <!--
        driverClass=com.mysql.jdbc.Driver
        url=jdbc:mysql://localhost:3306/mybatis?useUnicode=true&amp;characterEncoding=utf8
        name=root
        password=123456
    -->
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

    <!--告诉mybatis加载映射文件-->
    <mappers>
        <mapper resource="com/tomster/mybatis/sqlmap/UserMapper.xml"></mapper>
    </mappers>
</configuration>
```

UserMap.xml
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="user">
    <select id="findUserById" parameterType="int" resultType="com.tomster.mybatis.po.User">
        SELECT * FROM user WHERE id = #{id}
    </select>
</mapper>
```

PO类
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

    //getter and setter omitted...
}
```
测试方法以及输出
```java
@Test
public void test() throws IOException {
    //1.读取配置文件
    InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
    //2.创建SqlSessionFactory会话工厂
    SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
    //3.创建SqlSession
    SqlSession sqlSession = ssf.openSession();
    //4.调用SqlSession的操作数据库方法
    User user = sqlSession.selectOne("findUserById", 10);
    System.out.println(user);
    //5.关闭SqlSession
    sqlSession.close();
}

output:
User [id=10, username=张三, sex=1, birthday=Thu Jul 10 00:00:00 CST 2014, address=北京市]
```

## mapper代理方式实现dao

### 1.开发规范  
* mapper接口的全限定名要和mapper映射文件的namespace的值相同;
* mapper接口的方法名称要和mapper映射文件中的statement的id相同;
* mapper接口的方法参数只能有一个，且类型要和mapper映射文件中statement的parameterType的值保持一致;
* mapper接口的返回值类型要和mapper映射文件中statement的resultType值或resultMap中的type值保持一致。


### 2.具体实现

```java
package com.tomster.mybatis.mapper;

import com.tomster.mybatis.po.User;

/**
 * @author meihewang
 * @date 2019/11/18  23:20
 */
public interface UserMapper {

    public int insertUser(User user);

    public User findUserById(int id);

}
```  

UserMapper.xml
```xml
<mapper namespace="com.tomster.mybatis.mapper.UserMapper">
    <select id="findUserById" parameterType="int" resultType="com.tomster.mybatis.po.User">
        SELECT * FROM user WHERE id = #{id}
    </select>
    <insert id="insertUser" parameterType="com.tomster.mybatis.po.User">
        INSERT INTO USER (username,sex,birthday,address)
        VALUE (#{username},#{sex},#{birthday},#{address})
    </insert>
</mapper>
```  

test code
```java
UserMapper mapper = sqlSession.getMapper(UserMapper.class);
User user = mapper.findUserById(10);
System.out.println(user);
```  

### 3.加载映射文件的方式  
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

## 设置

### 别名  
```xml
<typeAliases>
    <package name="com.tomster.mybatis.vo"></package>
    <package name="com.tomster.mybatis.po"></package>
</typeAliases>
``` 

### 动态sql
```xml
<select id="userList" parameterType="com.tomster.mybatis.po.User" resultType="com.tomster.mybatis.po.User">
    SELECT * FROM USER
    <where>
        <if test="username!=null">and username like '%${username}%'</if>
        <if test="sex!=null">and sex=#{sex}</if>
    </where>
</select>
```


### foreach  

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
</select>
``

