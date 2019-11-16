# MyBatis基础  

## 基本概念  

&emsp;&emsp;MyBatis是一个持久层框架，它对jdbc的操作数据库的过程进行封装。通过xml或注解的方式将要执行的各种statement（statement、preparedStatemnt、CallableStatement）配置起来，并通过java对象和statement中的sql进行映射生成最终执行的sql语句，最后由mybatis框架执行sql并将结果映射成java对象并返回。