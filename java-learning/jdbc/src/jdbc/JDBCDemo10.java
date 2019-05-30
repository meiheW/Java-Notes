package jdbc;

/**
 * @author meihewang
 * @date 2019/5/31 00310:00
 */


import util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 事务操作
 */
public class JDBCDemo10 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try{
            conn = JDBCUtils.getConnection();
            //开启事务
            conn.setAutoCommit(false);
            String sql1 = "update account set balance = balance - ? where name = ?";
            String sql2 = "update account set balance = balance + ? where name = ?";

            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setInt(1, 500);
            pstmt1.setString(2, "zhangsan");

            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setInt(1, 500);
            pstmt2.setString(2, "lisi");

            pstmt1.executeUpdate();
            int i = 3/0;
            pstmt2.executeUpdate();


            //提交事务
            conn.commit();

        }catch (Exception e){
            e.printStackTrace();
            //事务回滚
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally{
            JDBCUtils.close(pstmt1, conn);
            JDBCUtils.close(pstmt2, null);
        }

    }
}
