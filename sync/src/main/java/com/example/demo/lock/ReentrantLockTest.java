package com.example.demo.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author meihewang
 * @date 2020/07/19  0:23
 */
public class ReentrantLockTest {


    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.tryLock();

    }


}
