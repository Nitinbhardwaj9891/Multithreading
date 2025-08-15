package com.java.write.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class WriteLockDemo{
    private ReadWriteLock lock;

    public WriteLockDemo(ReadWriteLock lock){
        this.lock = lock;
    }

    public void writeDemo(){
        System.out.println("Lock is not yet acquired by any Thread");
        try{
            Thread.sleep(2000);
            lock.writeLock().lock();
            System.out.println("Lock is acquired by "+Thread.currentThread().getName());
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.writeLock().unlock();
            System.out.println("Lock is released by "+Thread.currentThread().getName());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        WriteLockDemo d = new WriteLockDemo(lock);
        WriteLockDemo d1 = new WriteLockDemo(lock);
        Thread t1 = new Thread(d::writeDemo,"t1");
        Thread t2 = new Thread(d1::writeDemo,"t2");
        Thread t3 = new Thread(d::writeDemo,"t3");
        Thread t4 = new Thread(d1::writeDemo,"t4");
        t1.start();t2.start();t3.start();t4.start();

    }
}
