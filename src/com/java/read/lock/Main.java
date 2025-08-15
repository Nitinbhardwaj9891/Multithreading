package com.java.read.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadLockDemo{
    private ReadWriteLock lock;

    public ReadLockDemo(ReadWriteLock lock){
        this.lock = lock;
    }
    public void readLockDemo(){
        System.out.println("Read lock is acquired by Multiple Threads at same time as it is for reading purpose");
        try{
            lock.readLock().lock();
            for (int i = 0; i < 5; i++) {
                System.out.println(i+ " Lock is acquired by: "+Thread.currentThread().getName());
                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.readLock().unlock();
            System.out.println("Lock is released by: "+Thread.currentThread().getName());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        ReadLockDemo rl1 = new ReadLockDemo(lock);
        ReadLockDemo rl2 = new ReadLockDemo(lock);
        Thread t1 = new Thread(rl1::readLockDemo,"t1");
        Thread t2 = new Thread(rl2::readLockDemo,"t2");
        Thread t3 = new Thread(rl1::readLockDemo,"t3");
        Thread t4 = new Thread(rl2::readLockDemo,"t4");
        t1.start();t2.start();t3.start();t4.start();
    }
}
