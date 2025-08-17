package com.java.multitasking.multithreading;
//Yield not guaranteed
class Demo1 extends Thread{

    public Demo1(String name){
        super(name);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500); // Simulate work
            System.out.println("Thread "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Demo2 extends  Thread{

    public Demo2(String name){
        super(name);
    }
    @Override
    public void run() {
        try {
            Thread.sleep(500); // Simulate work
            System.out.println("Thread "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Demo1 demo1 = new Demo1("demo1");
        Demo2 demo2  = new Demo2("demo2");
        Demo1 demo3 = new Demo1("demo3");
        Demo2 demo4  = new Demo2("demo4");

        demo1.start();
        demo2.start();
        demo3.start();
        demo4.start();

       try{
           Thread.yield();

           int n =100;
           int count =0;
           while(n>0){
               count++;
               n/=10;
           }
           System.out.println("count is " +count);
       } catch (Exception e) {
           e.printStackTrace();
       }

    }
}
