package com.example.demo.waitnotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author meihewang
 * @date 2020/09/20  17:33
 */
public class SynExecute {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    public static boolean printOne = false;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            while(true){
                lock.lock();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while(!printOne){

                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(1);
                printOne = !printOne;
                condition.signalAll();
            }

        }
        );

        Thread t2 = new Thread(() -> {
            while(true){
                lock.lock();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while(printOne){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(2);
                printOne = !printOne;
                condition.signalAll();
            }
        }
        );
        t1.start();
        t2.start();
    }

}
