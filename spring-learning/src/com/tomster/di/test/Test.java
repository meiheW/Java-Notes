package com.tomster.di.test;

import com.tomster.di.model.Customer;
import com.tomster.di.model.Programmer;
import com.tomster.di.model.Student;
import com.tomster.di.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author meihewang
 * @date 2019/11/03  22:01
 */
public class Test {

    @org.junit.Test
    public void test(){
        //DI
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-di.xml");
        Student student = (Student)applicationContext.getBean("student2");
        System.out.println(student);
    }


    @org.junit.Test
    public void test1(){
        //spring-el
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-spel.xml");
        Customer customer = (Customer)applicationContext.getBean("customer");
        System.out.println(customer);
    }

    @org.junit.Test
    public void test2(){
        //collection-di
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-collection.xml");
        Programmer programmer = (Programmer)applicationContext.getBean("programmer");
        System.out.println(programmer);
    }

    @org.junit.Test
    public void test3(){
        //collection-di
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-anno.xml");
        //根据bean-id
        UserServiceImpl userService = (UserServiceImpl)applicationContext.getBean("userService");
        //根据bean类型
        //UserServiceImpl userService = applicationContext.getBean(UserServiceImpl.class);
        userService.add();

    }


}
