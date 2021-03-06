# JavaWeb基础知识
JDBC/TOMCAT/Servlet/Cookie/Session/Listener/Filter

## JDBC：

1. **概念**：  
	Java DataBase Connectivity  Java 数据库连接， Java语言操作数据库  
	JDBC本质：其实是官方（sun公司）定义的一套操作所有关系型数据库的规则，即接口。  
	各个数据库厂商去实现这套接口，提供数据库驱动jar包。我们可以使用这套接口（JDBC）编程，真正执行的代码是驱动jar包中的实现类。  
2. **快速入门**：

	1.导入驱动jar包 mysql-connector-java-5.1.37-bin.jar  
	2.注册驱动  
	3.获取数据库连接对象 Connection  
	4.定义sql  
	5.获取执行sql语句的对象 Statement  
	6.执行sql，接受返回结果  
	7.处理结果  
	8.释放资源  

3.	**代码实现**：
	```java 
  	//1.导入驱动jar包
    //2.注册驱动
    Class.forName("com.mysql.jdbc.Driver");
    //3.获取数据库连接对象
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "root");
    //Connection conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "root");
    //4.定义sql语句
    String sql = "update account set balance = 800 where id = 1";
    //5.获取执行sql的对象 Statement
    Statement stmt = conn.createStatement();
    //6.执行sql
    int count = stmt.executeUpdate(sql);
    //7.处理结果
    System.out.println(count);
    //8.释放资源
    stmt.close();
    conn.close();
	```


4. **详解各个对象**：  

 	**DriverManager**：驱动管理对象  
	功能：注册驱动，告诉程序该使用哪一个数据库驱动jar  
	```java 
	static void registerDriver(Driver driver) :注册与给定的驱动程序 DriverManager 。 
	写代码使用：  Class.forName("com.mysql.jdbc.Driver");
	源码：com.mysql.jdbc.Driver类中存在静态代码块
	 static {
	        try {
	            java.sql.DriverManager.registerDriver(new Driver());
	        } catch (SQLException E) {
	            throw new RuntimeException("Can't register driver!");
	        }
		}

	注意：mysql5之后的驱动jar包可以省略注册驱动的步骤。
	```
	
    **Connection**：数据库连接对象  

		获取执行sql 的对象  
			Statement createStatement()  
			PreparedStatement prepareStatement(String sql)  
		管理事务：  
			开启事务：setAutoCommit(boolean autoCommit) ：调用该方法设置参数为false，即开启事务  
			提交事务：commit()  
			回滚事务：rollback()  
	
	**Statement**：执行sql的对象    

		1. boolean execute(String sql) ：可以执行任意的sql 了解  
		int executeUpdate(String sql) ：执行DML（insert、update、delete）语句、DDL(create，alter、drop)语句  
		2. 返回值：影响的行数，可以通过这个影响的行数判断DML语句是否执行成功 返回值>0的则执行成功，反之，则失败。  
		ResultSet executeQuery(String sql)  ：执行DQL（select)语句  
	
	**ResultSet**：结果集对象,封装查询结果   

		1. boolean next(): 游标向下移动一行，判断当前行是否是最后一行末尾(是否有数据)，如果是，则返回false，如果不是则返回true  
		2. getXxx(参数):获取数据  
			Xxx：代表数据类型   如： int getInt() ,	String getString()  
			参数：  
				1. int：代表列的编号,从1开始   如： getString(1)  
				2. String：代表列名称。 如： getDouble("balance")  
					
    **PreparedStatement**：执行sql的对象   

		解决sql注入问题：使用PreparedStatement对象来解决  
		预编译的SQL：参数使用?作为占位符  
		步骤：  
			1.导入驱动jar包 mysql-connector-java-5.1.37-bin.jar  
			2.注册驱动  
			3.获取数据库连接对象 Connection  
			4.定义sql  
			* 注意：sql的参数使用？作为占位符。 如：select * from user where username = ? and password = ?;  
			5.获取执行sql语句的对象 PreparedStatement   
			Connection.prepareStatement(String sql)  
			6.给?赋值：  
			7.执行sql，接受返回结果，不需要传递sql语句  
			8.处理结果  
			9.释放资源   

5. **JDBC控制事务**：  

	1.事务：一个包含多个步骤的业务操作。如果这个业务操作被事务管理，则这多个步骤要么同时成功，要么同时失败。  

	2.操作:  
	
		1.开启事务  
		2.提交事务  
		3.回滚事务  

	3.使用Connection对象来管理事务  

		开启事务：setAutoCommit(boolean autoCommit) ：调用该方法设置参数为false，即开启事务  
			在执行sql之前开启事务  
		提交事务：commit()  
			当所有sql都执行完提交事务  
		回滚事务：rollback()  
			在catch中回滚事务  

	4.代码：
	```java
	public class JDBCDemo {

	    public static void main(String[] args) {
	        Connection conn = null;
	        PreparedStatement pstmt1 = null;
	
	        try {
	            //1.获取连接
	            conn = JDBCUtils.getConnection();
	            //开启事务
	            conn.setAutoCommit(false);
	            //2.定义sql
	            String sql1 = "update account set balance = balance - ? where id = ?";
	            //3.获取执行sql对象
	            pstmt1 = conn.prepareStatement(sql1);
	            //4. 设置参数
	            pstmt1.setDouble(1,500);
	            pstmt1.setInt(2,1);
	            //5.执行sql
	            pstmt1.executeUpdate();
	            // 异常
	            int i = 3/0;
	            //提交事务
	            conn.commit();
	        } catch (Exception e) {
	            //事务回滚
	            try {
	                if(conn != null) {
	                    conn.rollback();
	                }
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	            }
	            e.printStackTrace();
	        }finally {
	            JDBCUtils.close(pstmt1,conn);
	        }
	    }
	}
	```