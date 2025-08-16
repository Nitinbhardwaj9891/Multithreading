package com.java.semaphore.lock;

import java.util.concurrent.Semaphore;

class SemaphoreDemo{
    private boolean isItemAvailable;
    private final Semaphore lock;

    public SemaphoreDemo(Semaphore lock){
        this.lock = lock;
    }

    public void demo(){
        System.out.println("Lock is yet to be acquired "+Thread.currentThread().getName());
        try{

            lock.acquire();
            Thread.sleep(100);
            System.out.println("Lock is acquired by "+Thread.currentThread().getName());
            isItemAvailable = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.release();
            System.out.println("Lock is released by "+Thread.currentThread().getName());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        SemaphoreDemo semaphoreDemo = new SemaphoreDemo(semaphore);

        Thread t1 = new Thread(semaphoreDemo::demo,"t1");
        Thread t2 = new Thread(semaphoreDemo::demo,"t2");
        Thread t3 = new Thread(semaphoreDemo::demo,"t3");
        Thread t4 = new Thread(semaphoreDemo::demo,"t4");

        t1.start();t2.start();t3.start();t4.start();

    }
}
