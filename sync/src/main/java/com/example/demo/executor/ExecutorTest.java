package com.example.demo.executor;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author meihewang
 * @date 2020/08/20  22:31
 */
public class ExecutorTest {


    public static void main(String[] args) {
        //ExecutorService executorService = Executors.newFixedThreadPool(3);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                for(int i=1; i<=5; i++){
                    Thread.sleep(1000L);
                    System.out.println("````");

                }
            }
        });
        executorService.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true){
                    Thread.sleep(1000L);
                    System.out.println("AAAA");
                }
            }
        });
        System.out.println("---A---");
        executorService.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true){
                    Thread.sleep(1000L);
                    System.out.println("BBBB");
                }
            }
        });
        System.out.println("---B---");
        executorService.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true){
                    Thread.sleep(1000L);
                    System.out.println("CCCC");
                }
            }
        });
        System.out.println("---C---");
    }


}
