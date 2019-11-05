package com.tomster.aop.service;

import com.tomster.aop.aspect.MyAspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author meihewang
 * @date 2019/11/06  0:42
 */
public class UserServiceProxy implements InvocationHandler {

    IUserService uservice;

    MyAspect aspect;

    public UserServiceProxy(IUserService uservice, MyAspect aspect) {
        this.uservice = uservice;
        this.aspect = aspect;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        aspect.before();
        Object invoke = method.invoke(uservice, args);
        aspect.after();
        return invoke;
    }
}
