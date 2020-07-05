package com.example.demo;

/**
 * @author meihewang
 * @date 2020/05/23  21:13
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        TestVolatile testVolatile = new TestVolatile();
        TestVolatileThread tt1 = new TestVolatileThread(testVolatile);
        TestVolatileThread tt2 = new TestVolatileThread(testVolatile);

        new Thread(tt1).start();
        new Thread(tt2).start();

        Thread.sleep(10000);
        System.out.println(testVolatile.getINDEX());

    }

}
