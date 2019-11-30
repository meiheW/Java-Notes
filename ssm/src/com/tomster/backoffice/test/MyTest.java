package com.tomster.backoffice.test;


import com.tomster.backoffice.mapper.UserMapper;
import com.tomster.backoffice.po.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author meihewang
 * @date 2019/11/30  23:25
 */
public class MyTest {

    @Test
    public void test() {
        //1.加载spring的配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //2.获取userMapper
        UserMapper userMapper = (UserMapper) context.getBean("userMapper");

        //3.调用dao方法
        User user = userMapper.findUserById(1);

        System.out.println(user);
    }



}
