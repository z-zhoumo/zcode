package com.juc.juc;

import java.util.concurrent.CountDownLatch;

/*

 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //创建CountDownLatch对象，设置初始值
        CountDownLatch countDownLatch = new CountDownLatch(6);


    //6个同学陆续离开教师后，班长锁门
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread()
                .getName()+"号同学离开");
                //计数 -1
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        //等待
        countDownLatch.await();

        System.out.println(Thread.currentThread()
                .getName()+"班长锁门");
    }
    
}
