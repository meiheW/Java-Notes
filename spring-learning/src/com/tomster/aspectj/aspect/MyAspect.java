package com.tomster.aspectj.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author meihewang
 * @date 2019/11/05  23:52
 */
@Component
@Aspect
public class MyAspect {

    @Around("execution(* com.tomster.aspectj.service.*.*(..))")
    public Object around(ProceedingJoinPoint processJoinPoint){
        System.out.println("begin...");
        Object response = null;
        try {
            response = processJoinPoint.proceed(processJoinPoint.getArgs());
            System.out.println("finish...");

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return response;
    }

}
