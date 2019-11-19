package com.tomster.mybatis.test;

import com.tomster.mybatis.mapper.UserMapper;
import com.tomster.mybatis.po.User;
import com.tomster.mybatis.vo.QueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author meihewang
 * @date 2019/11/18  23:28
 */
public class MapperTest {

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
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findUserById(10);
        System.out.println(user);

    }

    @Test
    public void testMapper() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("小");
        user.setSex("1");
        int count = mapper.countUser(user);
        System.out.println(count);

    }

    @Test
    public void testWhere() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("小");
        user.setSex("1");
        List<User> list = mapper.userList(user);
        System.out.println(list);
    }

    @Test
    public void testForeach() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        QueryVo queryVo = new QueryVo();
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(5);
        ids.add(10);
        queryVo.setIds(ids);
        List<User> list = mapper.userListByIds(queryVo);
        System.out.println(list);
    }
}
