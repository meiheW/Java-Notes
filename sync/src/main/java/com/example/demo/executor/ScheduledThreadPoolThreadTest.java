package com.example.demo.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author meihewang
 * @date 2020/08/21  23:57
 */
public class ScheduledThreadPoolThreadTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("...");
                }
            }
        }, 10, TimeUnit.SECONDS);
        System.out.println("AAA");


    }

}
