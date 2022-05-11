package com.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*

 */
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        //return new ThreadPoolExecutor(...)底层都是ThreadPoolExecutor

        //一池 5 线程   5个窗口
        ExecutorService threadPool1 =
                Executors.newFixedThreadPool(5);

        //一池一线程   1个窗口
//        ExecutorService threadPool1 =
//                Executors.newSingleThreadExecutor();

        //一池可扩容线程
//        ExecutorService threadPool1
//                = Executors.newCachedThreadPool();


        //10个线程     10个顾客请求
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
