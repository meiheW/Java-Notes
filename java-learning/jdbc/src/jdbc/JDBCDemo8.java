package jdbc;

import domain.Emp;
import util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * account表 查询表中的数据并封装成对象
 */
public class JDBCDemo8 {
    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        ResultSet resultSet = null;
        try {

            //1.注册驱动
            //2.获取Connection对象
            conn = JDBCUtils.getConnection();
            //3.获取执行sql的对象 Statement
            stmt = conn.createStatement();
            //4. 定义sql
            String sql = "SELECT * FROM emp";
            //5.执行sql
            resultSet = stmt.executeQuery(sql);
            //6.处理结果
            List list = new ArrayList<Emp>();
            Emp emp = null;
            while(resultSet.next()){
                emp = new Emp();
                emp.setId(resultSet.getInt("id"));
                emp.seteName(resultSet.getString("ename"));
                emp.setJobId(resultSet.getInt("job_id"));
                emp.setMgr(resultSet.getInt("mgr"));
                emp.setJoinDate(resultSet.getDate("joindate"));
                emp.setSalary(resultSet.getDouble("salary"));
                emp.setBonus(resultSet.getDouble("bonus"));
                emp.setDeptId(resultSet.getInt("dept_id"));

                list.add(emp);
            }

            System.out.println(list);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //7. 释放资源
            JDBCUtils.close(resultSet, stmt, conn);
        }


    }
}
