package com.wmh.test;

import com.wmh.service.IUserSerivce;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    @Test
    public void test1(){
        //以前用UserService方式,自己创建对象
       /* IUserSerivce userSerivce = new UserServiceImpl();
        userSerivce.add();*/

       //现在使用UserService方式从spring容器获取
        //1.加载beans.xml 这个spring的配置文件,内部就会创建对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        //2.从spring容器获取 userSerivce对象
        IUserSerivce userSerivce1 = (IUserSerivce) context.getBean("userService");
        userSerivce1.add();

        IUserSerivce userSerivce2 = (IUserSerivce) context.getBean("userService");
        userSerivce2.add();

        System.out.println(userSerivce1);
        System.out.println(userSerivce2);
    }
}
