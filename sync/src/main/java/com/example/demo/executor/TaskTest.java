package com.example.demo.executor;

import java.util.concurrent.*;

/**
 * @author meihewang
 * @date 2020/08/22  0:40
 */
public class TaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("...");
                return "2";
            }
        });

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(future);
        for(int i=0;i<=100000;i++){
            int ii=1;
        }
        System.out.println("AAA");
        String s = future.get();
        System.out.println("BBB");
        System.out.println(s);

    }

}
