package com.tomster.aop.aspect;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author meihewang
 * @date 2019/11/06  23:15
 */
public class MyAspect3 {
    public void myBefore(){
        System.out.println("前置通知...");
    }

    public void myAfterReturning(){
        System.out.println("后置通知...");
    }

    public Object myAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知...");
        System.out.println(pjp.getSignature().getName());//切入点就方法名
        System.out.println("开启事务...");
        //放行
        Object retObj = pjp.proceed();
        System.out.println("提交事务...");
        return retObj;
    }
}


