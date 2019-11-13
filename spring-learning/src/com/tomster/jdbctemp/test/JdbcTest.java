package com.tomster.jdbctemp.test;

import com.tomster.jdbctemp.dao.UserDao;
import com.tomster.jdbctemp.dao.UserSupportDao;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author meihewang
 * @date 2019/11/13  0:04
 */
public class JdbcTest {

    @Test
    public void test(){
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUrl("jdbc:mysql://localhost:3306/spring");
        bds.setUsername("root");
        bds.setPassword("123456");

        JdbcTemplate jt = new JdbcTemplate(bds);

        String sql = "insert into t_user(username,password) values('tom','123');";
        jt.update(sql);

    }

    @Test
    public void test2(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans-jdbctemp.xml");
        UserDao userDao = (UserDao)ac.getBean("userDao");
        userDao.addUser();
    }

    @Test
    public void test3(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans-jdbcdaospt.xml");
        UserSupportDao userDao = (UserSupportDao)ac.getBean("userSupportDao");
        userDao.addUser();
    }


}
