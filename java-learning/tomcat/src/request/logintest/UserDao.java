package request.logintest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author meihewang
 * @date 2019/07/21  12:59
 */
public class UserDao {

    public List<User> findUserByName(User user){
        Connection conn = null;
        PreparedStatement pstmt = null;
        List<User> list = null;
        try {
            //1.获取连接
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "root");
            //2.定义sql
            String sql = "select * from user where username= ? and password= ?";
            //3.获取pstmt对象
            pstmt = conn.prepareStatement(sql);
            //4.给参数赋值
            pstmt.setString(1,user.getUsername());
            pstmt.setString(2,user.getPassword());
            //5.执行sql
            ResultSet resultSet = pstmt.executeQuery();
            //6.返回用户列表
            list = new ArrayList<>();
            while(resultSet.next()){
                User u = new User();
                u.setUsername(resultSet.getString("username"));
                u.setPassword(resultSet.getString("password"));
                list.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //6. 释放资源
            if(pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

}
