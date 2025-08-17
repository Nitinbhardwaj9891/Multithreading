package com.java.thread.creation.runnable;

class ThreadDemoByRunnable implements Runnable{
    @Override
    public void run(){
        try{
            for (int i=1;i<=5;i++){
                for (int j = 1; j <=i ; j++) {
                    System.out.print(j);
                    Thread.sleep(1000);
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ThreadDemoByRunnable threadDemo = new ThreadDemoByRunnable();
        Thread thread = new Thread(threadDemo,"threadDemo");
        thread.start();
    }
}
