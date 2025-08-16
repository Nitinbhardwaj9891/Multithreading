package com.java.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class CustomLockCondition{
    private boolean isItemAvailable;
    private final ReentrantLock lock;
    Condition condition;

    public CustomLockCondition(ReentrantLock lock){
        this.lock = lock;
        condition = lock.newCondition();
    }

    public void produce(){
        System.out.println("Lock is not yet acquired by any thread");
        try{
            lock.lock();
            System.out.println("Producer Reentrant Lock is acquired by "+Thread.currentThread().getName());
            while(isItemAvailable){
                System.out.println("item is already available");
                condition.await();
            }
            isItemAvailable = true;
            condition.signal();
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        finally {
            lock.unlock();
            System.out.println("Producer lock is released by "+Thread.currentThread().getName());
        }
    }

    public void consume(){
        System.out.println("Lock is not yet acquired by any thread");
        try{
            lock.lock();
            System.out.println("Consumer Reentrant Lock is acquired by "+Thread.currentThread().getName());
            while(!isItemAvailable){
                System.out.println("Item is not available to consume");
                condition.await();
            }
            isItemAvailable = false;
            condition.signal();
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        finally {
            lock.unlock();
            System.out.println("Consumer lock is released by "+Thread.currentThread().getName());
        }
    }


}

public class Main {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        CustomLockCondition buffer = new CustomLockCondition(lock);

        Runnable producerTask = () -> {
            for (int i = 0; i < 5; i++) {
                buffer.produce();
                try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        };

        Runnable consumerTask = () -> {
            for (int i = 0; i < 5; i++) {
                buffer.consume();
                try { Thread.sleep(150); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        };

        Thread producer = new Thread(producerTask, "Producer-Thread");
        Thread consumer = new Thread(consumerTask, "Consumer-Thread");

        producer.start();
        consumer.start();
    }
}
