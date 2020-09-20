package com.example.demo.waitnotify;

import java.nio.channels.spi.SelectorProvider;

/**
 * @author meihewang
 * @date 2020/09/20  14:51
 */
public class DeadLock {

    static Object o = new Object();
    static Object obj = new Object();

    public static void main(String[] args) {

        new Thread(()->{
            synchronized(o){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" lock o");
                synchronized (obj){
                    System.out.println("A");
                }
            }
        }, "A").start();


        new Thread(()->{
            synchronized(obj){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" lock obj");
                synchronized (o){
                    System.out.println("B");
                }
            }
        }, "B").start();
    }

}
