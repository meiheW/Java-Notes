package jdbc;

import java.sql.*;

/**
 * account表 查询表中的数据
 */
public class JDBCDemo6 {
    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        ResultSet resultSet = null;
        try {
            //1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2. 定义sql
            String sql = "SELECT * FROM account";
            //3.获取Connection对象
            conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "root");
            //4.获取执行sql的对象 Statement
            stmt = conn.createStatement();
            //5.执行sql
            resultSet = stmt.executeQuery(sql);
            //6.处理结果
            while(resultSet.next()){
                System.out.println(resultSet.getString(2)+ ": " +resultSet.getInt(3));

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //7. 释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //避免空指针异常
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
