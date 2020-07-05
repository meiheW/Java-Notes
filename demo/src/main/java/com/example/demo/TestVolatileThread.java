package com.example.demo;

/**
 * @author meihewang
 * @date 2020/05/23  21:24
 */
public class TestVolatileThread implements Runnable{

    TestVolatile testVolatile;

    TestVolatileThread(TestVolatile testVolatile){
        this.testVolatile = testVolatile;
    }


    @Override
    public void run() {
        for(int i=0; i<10000; i++){
            testVolatile.incur();
        }
        System.out.println(testVolatile.getINDEX());
    }
}
