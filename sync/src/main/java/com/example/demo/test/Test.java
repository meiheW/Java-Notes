package com.example.demo.test;

/**
 * @author meihewang
 * @date 2020/08/30  0:52
 */
public class Test {


    public static void main(String[] args) {

        new Thread(()->{
            for(int i=0; i<=5; i++){
                System.out.println("...");
            }
        }).start();

        System.out.println(";;;");
    }


}
