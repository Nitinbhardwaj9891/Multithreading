package com.java.inter.thread.communication;

class ThreadCommunication {
    private boolean isItemAvailable;

    public synchronized void producer() {
        System.out.println(Thread.currentThread().getName() + " acquired the lock for producer");
        if(!isItemAvailable){
            System.out.println("Producer is waiting for consumer to consume");
        }
        while (isItemAvailable) {
            try {

                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        isItemAvailable = true;
        System.out.println("Item is added");
        notify(); // Wake up consumer
    }

    public synchronized void consumer() {
        System.out.println(Thread.currentThread().getName() + " acquired the lock for consumer");

        while (!isItemAvailable) {
            try {
                System.out.println("Consumer is waiting for the producer to produce");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isItemAvailable = false;
        System.out.println("Item is consumed");
        notify(); // Wake up producer
    }
}

public class Main {
    public static void main(String[] args) {
        ThreadCommunication comm = new ThreadCommunication();
        Thread t1 = new Thread(()->comm.producer());
        Thread t2 = new Thread(()->comm.consumer());

        t2.start();
        try{
            Thread.sleep(100l);
            t1.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
