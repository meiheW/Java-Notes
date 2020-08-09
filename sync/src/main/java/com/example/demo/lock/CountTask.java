package com.example.demo.lock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author meihewang
 * @date 2020/08/09  23:24
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int threshold = 2;

    private int start;

    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= threshold;
        if(canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        }else{
                int middle = (start+end)/2;
                CountTask leftTask = new CountTask(start, middle);
                CountTask rightTask = new CountTask(middle+1, end);
                leftTask.fork();
                rightTask.fork();
                Integer left = leftTask.join();
                Integer right = rightTask.join();
                sum = left+right;
            }
            return sum;
        }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        CountTask countTask = new CountTask(1,10);
        ForkJoinTask<Integer> result = forkJoinPool.submit(countTask);
        Integer integer = result.get();

        System.out.println(integer);

    }
}
