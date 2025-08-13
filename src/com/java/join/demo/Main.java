package com.java.join.demo;

public class Main {
    public static void main(String[] args) {
        ThreadJoinDemo d = new ThreadJoinDemo();
        Thread t1 = new Thread(()->d.printElements());
        Thread t2 = new Thread(()->d.printElements());
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+" is executed successfully");
    }
}
