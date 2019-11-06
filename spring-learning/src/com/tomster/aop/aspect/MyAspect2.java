package com.tomster.aop.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author meihewang
 * @date 2019/11/06  23:15
 */
public class MyAspect2 implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        //拦截方法

        System.out.println("开启事务...");
        //放行
        Object retObj = methodInvocation.proceed();

        System.out.println("拦截.....");

        System.out.println("提交事务...");

        return retObj;
    }
}


