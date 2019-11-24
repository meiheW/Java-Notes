package com.tomster.mybatis.test;

import com.tomster.mybatis.mapper.UserMapper;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author meihewang
 * @date 2019/11/24  21:12
 */
public class ResultMapTest {

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
    public void testResultMap() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findUserResultMap(28);
        System.out.println(user);
    }

}
