package com.example.demo.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author meihewang
 * @date 2020/07/26  15:29
 */
public class ReentrantReadWriteLockTest {

    public static void main(String[] args) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        System.out.println(reentrantReadWriteLock.isFair());

    }

}
