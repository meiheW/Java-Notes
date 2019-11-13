package com.tomster.aspectj.test;

import com.tomster.aspectj.service.IUserService;
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
        IUserService userService = (IUserService)applicationContext.getBean("userService");
        userService.addUser();
    }
}
