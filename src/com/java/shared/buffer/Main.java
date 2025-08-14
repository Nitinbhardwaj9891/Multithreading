package com.java.shared.buffer;

import java.util.LinkedList;
import java.util.Queue;

class SharedResource {
    private Queue<Integer> sharedBuffer;
    private final Integer size;

    public SharedResource(Integer size){
        this.size =size;
        sharedBuffer = new LinkedList<>();
    }

    public synchronized void produce(int n){
        while(sharedBuffer.size()==size){
            try{
                wait();
                System.out.println("Buffer is full, Waiting for consumer to consume");
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        sharedBuffer.add(n);
        System.out.println(n+ " Items added in Shared Buffer by Producer");
        notifyAll();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void consume(){
        while (sharedBuffer.isEmpty()){
            try{
                wait();
                System.out.println("Buffer is empty, Waiting for producer to produce");
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

        }
        int i = sharedBuffer.poll();
        System.out.println(i+ " Item is consumed by Consumer");
        notifyAll();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SharedResource s = new SharedResource(3);
        Thread producer = new Thread(()->
        {
            for (int i = 1; i < 6; i++) {
                s.produce(i);
            }
        });
        Thread consumer = new Thread(()->
        {
            for (int i = 1; i < 6; i++) {
                s.consume();
            }
        });
        producer.start();
        consumer.start();
    }
}
