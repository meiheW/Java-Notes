package com.example.demo.waitnotify;

/**
 * @author meihewang
 * @date 2020/09/15  22:03
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {
        String s = "hello";
        //会报异常，必须和synchronized共用
        s.wait();
    }



}
