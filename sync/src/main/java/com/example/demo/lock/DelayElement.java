package com.example.demo.lock;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author meihewang
 * @date 2020/08/09  22:17
 */
public class DelayElement implements Delayed {

    private int num;

    public DelayElement(int num) {
        this.num = num;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
