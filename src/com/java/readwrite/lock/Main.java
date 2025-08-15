package com.java.readwrite.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadWriteLockDemo {
    private final ReadWriteLock lock;

    public ReadWriteLockDemo(ReadWriteLock lock) {
        this.lock = lock;
    }

    public void writeLockDemo() {
        System.out.println(Thread.currentThread().getName() + " attempting to acquire Write Lock...");
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired Write Lock.");
            // Simulate write operation
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " completed Write operation.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
            System.err.println("Write operation interrupted for " + Thread.currentThread().getName());
        } finally {
            lock.writeLock().unlock();
            System.out.println(Thread.currentThread().getName() + " released Write Lock.");
        }
    }

    public void readLockDemo() {
        System.out.println(Thread.currentThread().getName() + " attempting to acquire Read Lock...");
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired Read Lock.");
            // Simulate read operation
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + " completed Read operation.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
            System.err.println("Read operation interrupted for " + Thread.currentThread().getName());
        } finally {
            lock.readLock().unlock();
            System.out.println(Thread.currentThread().getName() + " released Read Lock.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        ReadWriteLockDemo demo = new ReadWriteLockDemo(lock);

        // Multiple readers and one writer
        Thread writer = new Thread(demo::writeLockDemo, "Writer");
        Thread reader1 = new Thread(demo::readLockDemo, "Reader-1");
        Thread reader2 = new Thread(demo::readLockDemo, "Reader-2");

        reader1.start();
        reader2.start();
        writer.start();
    }
}