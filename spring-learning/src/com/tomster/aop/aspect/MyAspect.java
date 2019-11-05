package com.tomster.aop.aspect;

/**
 * @author meihewang
 * @date 2019/11/05  23:52
 */
public class MyAspect {

    public void before(){
        System.out.println("begin...");
    }

    public void after(){
        System.out.println("end...");
    }

}
