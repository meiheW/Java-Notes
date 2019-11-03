package com.tomster.spring.test;

import com.tomster.spring.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

/**
 * @author meihewang
 * @date 2019/10/31  0:19
 */
public class TestSpring {


    @Test
    public void test1(){
        UserService userService = new UserService();
        userService.addUser();
    }


    @Test
    public void test2(){
        //IOC
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService = (UserService)applicationContext.getBean("userService");
        userService.addUser();
    }

    @Test
    public void test3(){
        //DI
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService = (UserService)applicationContext.getBean("userService");
        userService.addUser();
    }


    @Test
    public void test4(){
        /**Spring容器加载的3种方式*/
        //1.通过ClassPathXmlApplicationContext类路径加载[最常用]
        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        //2.通过文件系统路径获得配置文件[绝对路径]
        //ApplicationContext applicationContext = new FileSystemXmlApplicationContext("E:\\projects\\Java-Notes\\spring-learning\\src\\beans.xml");

        //3.BeanFactory[了解]
        String path = "E:\\projects\\Java-Notes\\spring-learning\\src\\beans.xml";
        BeanFactory beanFactory = new XmlBeanFactory(new FileSystemResource(path));
        UserService userService = (UserService)beanFactory.getBean("userService");
        userService.addUser();
    }
}
