package com.java.join.demo;

class ThreadJoinDemo {
    public void printElements() {
        try {
            System.out.println(Thread.currentThread().getName() + " is executing");
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                Thread.sleep(1000l);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
