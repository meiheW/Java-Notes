package com.tomster.aspectj.test;


import com.tomster.aspectj.service.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author meihewang
 * @date 2019/11/07  0:20
 */
public class Test {

    @org.junit.Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-aspectj-anno.xml");
        UserServiceImpl userService = (UserServiceImpl)applicationContext.getBean("userService");
        userService.addUser();
    }
}
