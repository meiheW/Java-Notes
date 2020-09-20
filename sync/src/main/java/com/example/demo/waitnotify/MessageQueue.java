package com.example.demo.waitnotify;

import java.util.LinkedList;

/**
 * @author meihewang
 * @date 2020/09/20  12:46
 */
public class MessageQueue {

    LinkedList<Message> list = new LinkedList<>();
    int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    public Message take(){
        synchronized (list){
            while(list.isEmpty()){
                try {
                    System.out.println(Thread.currentThread().getName() + " list empty, wait");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message message = list.removeFirst();
            System.out.println(Thread.currentThread().getName() + " remove "+message.getValue() +" , notify");
            list.notifyAll();
            return message;
        }
    }

    public void put(Message message){
        synchronized (list){
            while(list.size() >= capacity){
                try {
                    System.out.println(Thread.currentThread().getName() + " list full, wait");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.addLast(message);
            System.out.println(Thread.currentThread().getName() + " add "+message.getValue() + " , notify");
            list.notifyAll();
        }
    }




}


final class Message{

    int id;

    Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
