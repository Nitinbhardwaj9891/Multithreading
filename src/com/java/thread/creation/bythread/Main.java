package com.java.thread.creation.bythread;

class ThreadDemoByThread extends Thread{
    @Override
    public void run(){
        try{
            for (int i=1;i<=5;i++){
                for (int j = 5; j >=i ; j--) {
                    System.out.print(j);
                    Thread.sleep(1000);
                }
                System.out.println();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ThreadDemoByThread thread = new ThreadDemoByThread();
        thread.start();
    }
}
