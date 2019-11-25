package com.tomster.mybatis.test;

import com.tomster.mybatis.mapper.OrderMapper;
import com.tomster.mybatis.mapper.UserMapper;
import com.tomster.mybatis.po.OrderExt;
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

/**
 * @author meihewang
 * @date 2019/11/25  23:39
 */
public class OrderTest {
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
    public void after() {
        //5.关闭SqlSession
        sqlSession.close();
    }

    @Test
    public void testJoin() {
        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);
        OrderExt orderExt = mapper.findOrderById(3);
        System.out.println(orderExt);
    }
}
