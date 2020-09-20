package com.example.demo.waitnotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author meihewang
 * @date 2020/09/20  22:18
 */
public class WaitNotifyLock {
    public static void main(String[] args) {
        AwaitSignal as = new AwaitSignal(5);

        Condition a = as.newCondition();
        Condition b = as.newCondition();
        Condition c = as.newCondition();

        new Thread(()->{as.print("a", a, b);}).start();
        new Thread(()->{as.print("b", b, c);}).start();
        new Thread(()->{as.print("c", c, a);}).start();

        as.lock();
        try{
            a.signal();
        }finally{
            as.unlock();
        }
    }
}


class AwaitSignal extends ReentrantLock{

    int loopNum;

    public AwaitSignal(int loopNum) {
        this.loopNum = loopNum;
    }


    public void print(String str, Condition con, Condition nextCon){
        for(int i=1; i<loopNum; i++){
            lock();
            try{
                con.await();
                System.out.print(str);
                nextCon.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                unlock();
            }

        }


    }



}