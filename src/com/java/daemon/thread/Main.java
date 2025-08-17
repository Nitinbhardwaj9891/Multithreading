package com.java.daemon.thread;

class DaemonDemo extends Thread{
    @Override
    public void run(){
        try{
            for(int i=1;i<=5;i++){
                for (int j = 1; j <= i; j++) {
                    System.out.print("*\t");
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
        DaemonDemo thread = new DaemonDemo();
        thread.setDaemon(true); //set Daemon before thread starts
        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        System.out.println(Thread.currentThread().getName()+" is running"); // main here executed and finished hence daemon dead
    }
}
