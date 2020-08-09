package com.example.demo.lock;

import java.util.Comparator;
import java.util.concurrent.*;

/**
 * @author meihewang
 * @date 2020/08/09  17:39
 */
public class BlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Integer> abq = new ArrayBlockingQueue<>(2);
        abq.add(1);
        abq.add(2);
        abq.put(4);

        boolean offer = abq.offer(3);
        System.out.println(offer);
        abq.remove(1);

        //
        LinkedBlockingQueue<Integer> lbq = new LinkedBlockingQueue<>(2);
        lbq.add(12);
        lbq.remove(12);

        PriorityBlockingQueue<String> pbq = new PriorityBlockingQueue<>(11, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String str1 = (String)o1;
                String str2 = (String)o2;

                if(o1==null || o2==null){
                    if(o1==null && o2==null){
                        return 0;
                    }else if(o1==null){
                        return -1;
                    }else{
                        return 1;
                    }
                }
                return str1.length()-str2.length();
            }
        });
        pbq.offer("123");
        pbq.offer("1234");
        pbq.offer("55");
        pbq.offer("0");

        System.out.println(pbq.peek());

        System.out.println(pbq.toString());
        //
        PriorityScoreStudent pss = new PriorityScoreStudent("a", 1256);
        PriorityScoreStudent psss = new PriorityScoreStudent("aa", 123);
        PriorityScoreStudent pssss = new PriorityScoreStudent("aaa", 13);
        PriorityBlockingQueue<PriorityScoreStudent> xxx = new PriorityBlockingQueue<>();
        xxx.offer(pss);
        xxx.offer(psss);
        xxx.offer(pssss);

        System.out.println(xxx);
        PriorityScoreStudent poll = xxx.poll();
        System.out.println(poll);

        //Delay
        DelayQueue<DelayElement> dq = new DelayQueue<>();
        dq.offer(new DelayElement(1));
        dq.poll();

        //
        SynchronousQueue sq = new SynchronousQueue();
        sq.offer(1);
        sq.poll();


        //
        LinkedBlockingDeque<Integer> lbd = new LinkedBlockingDeque();
        lbd.add(1);

    }
}
