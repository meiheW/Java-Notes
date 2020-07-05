package com.example.demo;

/**
 * @author meihewang
 * @date 2020/05/23  21:09
 */
public class TestVolatile extends Thread {

    private volatile int INDEX = 0;

    /*private String name;

    public TestVolatile(String name) {
        this.name = name;
    }*/

    public synchronized void incur() {
        INDEX += 1;
    }

    public int getINDEX(){
        return INDEX;
    }


}
