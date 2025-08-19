package com.java.atomic.variable;

import java.util.concurrent.atomic.AtomicInteger;

class AtomicVariableDemo{
    AtomicInteger num = new AtomicInteger(0);

    public void updateNum(){
        num.incrementAndGet();
    }

    public int getUpdatedNum(){
        return num.get();
    }

}

public class Main {
    public static void main(String[] args) {
        AtomicVariableDemo demo = new AtomicVariableDemo();
        Thread t1 = new Thread(()->{
            for (int i = 1; i <= 50; i++) {
                demo.updateNum();
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 1; i <= 50; i++) {
                demo.updateNum();
            }
        });

        t1.start();t2.start();
        try{
            t1.join();t2.join();
            System.out.println(demo.getUpdatedNum());
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
