package com.example.demo.threadsafe;

/**
 * @author meihewang
 * @date 2020/09/12  15:55
 */
public class ThreadSafe {

    static int num = 0;
    static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            for(int i=0; i<100000; i++){
                synchronized (obj){
                    num++;
                }
            }
        });
        t1.start();

        Thread t2 = new Thread(()->{
            for(int i=0; i<100000; i++){
                synchronized (obj) {
                    num--;
                }
            }
        });
        t2.start();

        t1.join();
        t2.join();
        System.out.println(num);

    }

}
