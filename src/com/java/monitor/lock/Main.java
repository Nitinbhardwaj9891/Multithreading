package com.java.monitor.lock;

class MonitorLockDemo{
    public synchronized void demo1(){
        System.out.println("This is synchronized method. Only one thread for this object can access at a time");
        try{
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000l);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void demo2(){
        System.out.println("Before synchorization. All threads can parallelly access for this object");
        synchronized (this){
            System.out.println("This is synchronized method. Only one thread for this object can access at a time");
            try{
                for(int i=1;i<=5;i++){
                    for(int j=1;j<=i;j++){
                        System.out.print(j);
                        Thread.sleep(1000l);
                    }
                    System.out.println();
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MonitorLockDemo d = new MonitorLockDemo();
        Thread t1 = new Thread(()->d.demo1());
        Thread t2 = new Thread(()->d.demo1());
        Thread t3 = new Thread(()->d.demo2());
        Thread t4 = new Thread(()->d.demo2());
        t1.start();t2.start();t3.start();t4.start();
    }
}
