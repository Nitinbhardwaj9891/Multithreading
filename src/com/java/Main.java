package com.java;

class ThreadJoinDemo{
    public void printElements(){
        try {
            System.out.println(Thread.currentThread().getName()+" is executing");
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                Thread.sleep(1000l);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

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
