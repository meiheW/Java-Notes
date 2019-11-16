package com.tomster.mybatis.test;

import com.tomster.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author meihewang
 * @date 2019/11/17  2:30
 */
public class MyTest {

    @Test
    public void test() throws IOException {

        //1.读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory会话工厂
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
        //3.创建SqlSession
        SqlSession sqlSession = ssf.openSession();
        //4.调用SqlSession的操作数据库方法
        User user = sqlSession.selectOne("findUserById", 10);
        System.out.println(user);
        //5.关闭SqlSession
        sqlSession.commit();
    }

}
