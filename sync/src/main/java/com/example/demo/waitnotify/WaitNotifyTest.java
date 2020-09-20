package com.example.demo.waitnotify;

/**
 * @author meihewang
 * @date 2020/09/20  21:55
 */
public class WaitNotifyTest {

    public static void main(String[] args) {
        WaitNotify waitNotify = new WaitNotify(1,5);
        new Thread(()->{waitNotify.print("a", 1, 2);}).start();
        new Thread(()->{waitNotify.print("b", 2, 3);}).start();
        new Thread(()->{waitNotify.print("c", 3, 1);}).start();
    }

}

class WaitNotify{
    int flag;
    int loopNum;

    public WaitNotify(int flag, int loopNum) {
        this.flag = flag;
        this.loopNum = loopNum;
    }

    public void print(String str, int waitFlag, int nextFlag){
        for(int i=0; i<loopNum; i++){
            synchronized (this){
                while(waitFlag!=flag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(str);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }



}
