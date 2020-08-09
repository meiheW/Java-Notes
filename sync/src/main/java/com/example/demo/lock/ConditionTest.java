package com.example.demo.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author meihewang
 * @date 2020/07/26  21:48
 */
public class ConditionTest {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void conditionWait() throws InterruptedException {
        lock.lock();
        try{
            condition.await();
        }finally {
            lock.unlock();
        }
    }

    public void conditionSignal(){
        lock.unlock();
        try{
         condition.signal();
        }finally {
            lock.unlock();
        }
    }

}
