package com.juc.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/*

 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //异步调用  没有返回值
        CompletableFuture<Void> completableFuture1 =
                CompletableFuture.runAsync(()->{
                    System.out.println(Thread.currentThread()
                    .getName()+"completableFuture1");
                });
        completableFuture1.get();
        //ForkJoinPool.commonPool-worker-1completableFuture


        //异步调用 有返回值
        CompletableFuture completableFuture2 =
                CompletableFuture.supplyAsync(()->{
                    System.out.println(Thread.currentThread()
                            .getName()+"completableFuture2");

                    //祖传异常
                    int i= 3 / 0;

                    return 1024;
                });
        completableFuture2.whenComplete((t,u)->{
            System.out.println("---t="+t);//1024  null
            System.out.println("---u="+u);//unull
            //java.util.concurrent.CompletionException:
            // java.lang.ArithmeticException: / by zero
        }).get();

    }
}
