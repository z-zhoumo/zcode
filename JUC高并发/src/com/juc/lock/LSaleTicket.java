package com.juc.lock;
/*

 */

import java.util.concurrent.locks.ReentrantLock;

public class LSaleTicket {
    public static void main(String[] args) {
        LTicket lTicket = new LTicket();

        new Thread(()->{
            for (int i = 0; i < 99; i++) {
            lTicket.sale();
            }
        },"线程 一").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                lTicket.sale();
            }
        },"线程 二").start();
        new Thread(()->{
            for (int i = 0; i < 60; i++) {
                lTicket.sale();
            }
        },"线程 三").start();
    }
}

//创建资源类
class LTicket {

    private int number = 66;//票

    //创建可重入锁
    private final ReentrantLock lock = new ReentrantLock(true);

    //操作方法：卖票
    public void sale() {
        //上锁
        lock.lock();

        try {
            //判断是否有票
            if (number > 0) {
                System.out.println(Thread.currentThread().getName()
                        + " 卖出" + (number--) + "剩余" + number);
            }
        }finally {
            //解锁
            lock.unlock();
        }




    }
}