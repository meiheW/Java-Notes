package com.example.demo.waitnotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author meihewang
 * @date 2020/09/20  21:25
 */
public class WaitNotiffyTest1 {

    public static boolean printA = false;
    public static boolean printB = false;
    public static boolean printC = false;
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    private static int i = 0;

    public static void main(String[] args) {

        Thread a = new Thread(()->{
            lock.lock();
            try {
                while(i<5){
                    if(!printA && !printB && !printC){
                        System.out.println("A");
                        printA = true;
                        condition.signalAll();
                    }else
                        condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "ta");

        Thread b = new Thread(()->{
            lock.lock();
            try {
                while(i<5){
                    if(printA && !printB && !printC){
                        System.out.println("B");
                        printB = true;
                        condition.signalAll();
                    }else
                        condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "tb");

        Thread c = new Thread(()->{
            lock.lock();
            try {
                while(i<5){
                    if(printA && printB && !printC){
                        System.out.println("C");
                        printA = printB = false;
                        i++;
                        condition.signalAll();
                    }else
                        condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "tc");


        a.start();
        b.start();
        c.start();



    }


}
