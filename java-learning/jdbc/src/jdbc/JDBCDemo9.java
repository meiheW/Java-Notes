package jdbc;


import util.JDBCUtils;

import java.sql.*;
import java.util.Scanner;

/**
 * user表 登录案例
 */
public class JDBCDemo9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userName = sc.next();
        String password = sc.next();
        System.out.println(new JDBCDemo9().login2(userName, password));
    }

    //用户登录验证
    public boolean login(String userName, String password){
        Statement stmt = null;
        Connection conn = null;
        ResultSet resultSet = null;

        if(userName==null||password==null){
            return false;
        }

        try {
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            String sql = "select username from user where username = '" + userName + "' and password = '"+ password+"'";
            resultSet = stmt.executeQuery(sql);

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(resultSet, stmt, conn);
        }

        return false;

    }

    /**
     * 登录方法,使用PreparedStatement实现
     * 效率更高，能够防止sql注入
     */
    public boolean login2(String userName, String password){
        PreparedStatement pstmt = null;
        Connection conn = null;
        ResultSet resultSet = null;

        if(userName==null||password==null){
            return false;
        }

        try {
            conn = JDBCUtils.getConnection();
            String sql = "select username from user where username = ? and password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            resultSet = pstmt.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(resultSet, pstmt, conn);
        }

        return false;

    }

}
