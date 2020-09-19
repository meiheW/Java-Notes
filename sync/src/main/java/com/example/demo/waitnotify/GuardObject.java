package com.example.demo.waitnotify;

/**
 * @author meihewang
 * @date 2020/09/19  22:45
 */
public class GuardObject {

    private Object response;

    public Object get(){
        synchronized (this){
            while (response == null){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hello?");
            }
            return response;
        }
    }

    public void setResponse(Object o){
        synchronized (this) {
            if (response == null) {
                response = o;
                this.notifyAll();
            }
        }
    }

    /**
     * ok?
     * hello?
     * sss
     */
    public static void main(String[] args) throws InterruptedException {
        GuardObject guardObject = new GuardObject();
        new Thread(()-> System.out.println(guardObject.get())).start();
        Thread.sleep(6000L);
        String s = "sss";
        new Thread(()-> guardObject.setResponse(s)).start();

        System.out.println("ok?");

    }


}
