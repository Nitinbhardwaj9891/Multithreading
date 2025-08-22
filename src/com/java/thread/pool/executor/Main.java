package com.java.thread.pool.executor;

import java.util.concurrent.*;

class Main{
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 5,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(3), new CustomThreadFactory(),
                new CustomRejectedExecutionHandler());

        for (int i = 1; i <= 10; i++) {
            final int taskId = i;

            executor.submit(()->{
                System.out.println(Thread.currentThread().getName()+ " is executed by "+"Task Id: "+taskId);
                try{
                    Thread.sleep(5000);
                }
                catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                }
            });

        }
        executor.shutdown();
    }
}

class CustomThreadFactory implements ThreadFactory {
    private int count;
    @Override
    public Thread newThread(Runnable r) {
        count++;
        Thread thread = new Thread(r);
        thread.setPriority(5);
        thread.setDaemon(false);
        thread.setName("Thread: "+count);
        return thread;
    }
}

class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task Rejected "+r.toString());
    }
}
