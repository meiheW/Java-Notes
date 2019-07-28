package dao.impl;

import dao.UserDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import po.User;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    private final String userInfoTable =  "userinfo";

    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库...
        //1.定义sql
        String sql = "select * from " + userInfoTable;
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    @Override
    public User findUserByUserInfo(String username, String password) {
        String sql = "select * from "+ userInfoTable + " where username = ? and password = ?";
        try {
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public boolean addUser(User user) {
        Connection conn = null;
        PreparedStatement pstmt =null;
        try {
            conn = JDBCUtils.getConnection();

            String sql = "insert into " + userInfoTable + " (name,gender,age,address,qq,email) values (?,?,?,?,?,?)";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getGender());
            pstmt.setInt(3, user.getAge());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, user.getQq());
            pstmt.setString(6, user.getEmail());

            pstmt.executeUpdate();

            return true;
        }catch (Exception e){

            return false;
        }finally {
            JDBCUtils.close(pstmt, conn);
        }


    }

    @Override
    public boolean deleteUser(String id) {
        Connection conn = null;
        PreparedStatement pstmt =null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "delete from "+userInfoTable+" where id="+id;
            pstmt = conn.prepareStatement(sql);
            int count = pstmt.executeUpdate();
            if(count==1){
                return true;
            }else{
                return false;
            }

        } catch (SQLException e) {
            return false;
        }finally {
            JDBCUtils.close(pstmt, conn);
        }
    }

    @Override
    public User findUserById(String id) {
        Connection conn = null;
        PreparedStatement pstmt =null;
        User user = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from " + userInfoTable + " where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setGender(resultSet.getString("gender"));
                user.setAge(resultSet.getInt("age"));
                user.setAddress(resultSet.getString("address"));
                user.setQq(resultSet.getString("qq"));
                user.setEmail(resultSet.getString("email"));
            }

            return user;
        }catch (Exception e){
            return user;
        }finally {
            JDBCUtils.close(pstmt, conn);
        }
    }

    @Override
    public void updateUser(User user) {
        Connection conn = null;
        PreparedStatement pstmt =null;
        try {
            //获取id
            int id = user.getId();
            //获取连接
            conn = JDBCUtils.getConnection();
            //sql
            String sql = "update " + userInfoTable + " " +
                    "set name=?,gender=?,age=?,address=?,qq=?,email=? " +
                    "where id="+id;
            //创建预编译sql
            pstmt = conn.prepareStatement(sql);
            //参数设置
            pstmt.setString(1,user.getName());
            pstmt.setString(2,user.getGender());
            pstmt.setInt(3,user.getAge());
            pstmt.setString(4,user.getAddress());
            pstmt.setString(5,user.getQq());
            pstmt.setString(6,user.getEmail());
            //执行
            pstmt.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.close(pstmt, conn);
        }


    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select * from "+userInfoTable+" where 1=1";
        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);

        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user  where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }

        //添加分页查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        System.out.println(sql);
        System.out.println(params);

        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }


}
