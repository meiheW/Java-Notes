package com.example.demo.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author meihewang
 * @date 2020/07/19  0:23
 */
public class ReentrantLockTest {


    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.tryLock();
        Condition condition = reentrantLock.newCondition();
        reentrantLock.lock();
        condition.await();
    }


}
