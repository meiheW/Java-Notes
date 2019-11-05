package com.tomster.aop.factory;

import com.tomster.aop.aspect.MyAspect;
import com.tomster.aop.service.IUserService;
import com.tomster.aop.service.StudentService;
import com.tomster.aop.service.UserServiceImpl;
import com.tomster.spring.service.UserService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author meihewang
 * @date 2019/11/06  0:11
 */
public class MyBeanFactory {

    public static IUserService createUserService() {
        final IUserService userService = new UserServiceImpl();
        final MyAspect aspect = new MyAspect();

        IUserService userServiceP = (IUserService) Proxy.newProxyInstance(MyBeanFactory.class.getClassLoader(),
                UserServiceImpl.class.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        aspect.before();
                        Object invoke = method.invoke(userService, args);
                        aspect.before();
                        return invoke;
                    }
                });

        return userServiceP;
    }


    public static StudentService createCglibService() {
        final StudentService studentService = new StudentService();
        final MyAspect aspect = new MyAspect();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(studentService.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                aspect.before();
                Object retObj = methodProxy.invokeSuper(proxy,args);
                aspect.after();
                return retObj;
            }
        });

        return  (StudentService)enhancer.create();
    }
}
