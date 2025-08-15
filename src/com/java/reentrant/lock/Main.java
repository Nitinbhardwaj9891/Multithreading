package com.java.reentrant.lock;

import java.util.concurrent.locks.ReentrantLock;

class ReentrantLockDemo{
    private final ReentrantLock lock;

    public ReentrantLockDemo(ReentrantLock lock){
        this.lock = lock;
    }
    public void demo(){
        System.out.println("Currently Lock is not yet acquired by any Thread");
        try{
            lock.lock();
            Thread.sleep(1000);
            System.out.println("Lock is acquired by: "+Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try{
                lock.unlock();
                System.out.println("Lock is released by: "+Thread.currentThread().getName());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

public class Main {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        ReentrantLockDemo d = new ReentrantLockDemo(lock);
        ReentrantLockDemo d1 = new ReentrantLockDemo(lock);
        Thread t1 = new Thread(d::demo,"t1");
        Thread t2 = new Thread(d1::demo,"t2");
        Thread t3 = new Thread(d::demo,"t3");
        Thread t4 = new Thread(d1::demo,"t4");
        t1.start();t2.start();t3.start();t4.start();
    }
}
