package com.example.demo.waitnotify;

import java.util.concurrent.locks.LockSupport;

import static java.lang.Thread.sleep;

/**
 * @author meihewang
 * @date 2020/09/20  13:12
 */
public class MessageQueueTest {


    public static void main(String[] args) {
        MessageQueue mq = new MessageQueue(2);

        //produce
        for(int i=1; i<=10; i++){
            int id = i;
            new Thread(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mq.put( new Message(id, "msg"+id));

            }, "thread"+id
            ).start();
        };

        //consume
        new Thread(()->{
            while (true){
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = mq.take();
            }
        }, "thread-consumer").start();

        LockSupport.park();
        LockSupport.unpark(Thread.currentThread());


    }


}
