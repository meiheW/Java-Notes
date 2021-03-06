package com.tomster.mybatis.test;

import com.tomster.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author meihewang
 * @date 2019/11/17  2:30
 */
public class MyTest {

    SqlSession sqlSession;

    @Before
    public void before() throws IOException {
        //1.读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory会话工厂
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        //3.创建SqlSession
        sqlSession = ssf.openSession();
    }

    @After
    public void after(){
        //5.关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void test1() {
        //4.调用SqlSession的操作数据库方法
        User user = sqlSession.selectOne("findUserById", 10);
        System.out.println(user);
    }

    @Test
    public void test2() {
        User user = new User();
        user.setUsername("wmh");
        user.setAddress("sh");
        user.setSex("m");
        user.setBirthday(new Date(93,2,1));
        sqlSession.insert("insertUser", user);
    }

}
