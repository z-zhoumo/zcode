package com.juc.pool;

import java.util.concurrent.*;

/*

 */
public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        //自定义线程池的创建
       ExecutorService threadPool1 = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );


        try {
            for (int i = 1; i <= 10 ; i++) {
                threadPool1.execute(()->{
                    System.out.println(Thread
                            .currentThread().getName()+"办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭线程池
            threadPool1.shutdown();
        }

    }
}
