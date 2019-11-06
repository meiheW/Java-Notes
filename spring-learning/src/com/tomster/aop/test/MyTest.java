package com.tomster.aop.test;

import com.tomster.aop.aspect.MyAspect;
import com.tomster.aop.factory.MyBeanFactory;
import com.tomster.aop.service.IUserService;
import com.tomster.aop.service.StudentService;
import com.tomster.aop.service.UserServiceImpl;
import com.tomster.aop.service.UserServiceProxy;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author meihewang
 * @date 2019/11/06  0:22
 */
public class MyTest {

    @Test
    public void test1(){
        IUserService userService = MyBeanFactory.createUserService();
        userService.addUser();
    }

    @Test
    public void test2(){
        final IUserService userService = new UserServiceImpl();
        final MyAspect aspect = new MyAspect();
        IUserService userServiceP = (IUserService) Proxy.newProxyInstance(MyBeanFactory.class.getClassLoader(),
                UserServiceImpl.class.getInterfaces(), new UserServiceProxy(userService, aspect));
        userServiceP.addUser();
    }

    @Test
    public void test3(){
        StudentService cglibService = MyBeanFactory.createCglibService();
        cglibService.addStudent();
    }

    @Test
    public void test4(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-aop.xml");
        IUserService userService = (IUserService)applicationContext.getBean("userService");
        userService.addUser();
        StudentService studentService = (StudentService)applicationContext.getBean("studentService");
        studentService.addStudent();
    }

    @Test
    public void test5(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-aspectj.xml");
        IUserService userService = (IUserService)applicationContext.getBean("userService");
        userService.addUser();
    }


}
