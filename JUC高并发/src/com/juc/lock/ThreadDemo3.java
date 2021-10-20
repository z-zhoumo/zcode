package com.juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
    定制化通信
 */
public class ThreadDemo3 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 0; i <= 10; i++) {
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"线程一").start();
        new Thread(()->{
            for (int i = 0; i <= 10; i++) {
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"线程二").start();
        new Thread(()->{
            for (int i = 0; i <= 10; i++) {
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"线程三").start();
    }
}

//第一步   创建资源类
class ShareResource {
    //定制标志位
    private int flag = 1;// 1 aa   2 bb    3 cc
    //创建Lock锁
    private Lock lock = new ReentrantLock();

    //创建三个 condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //打印 5 次
    public void print5(int loop) throws InterruptedException {
        lock.lock();
        try {
             //判断
            while (flag != 1){
                //等待
                c1.await();
            }
            //干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()
                +"::"+i+" 轮数"+loop);
            }
            //通知
            flag = 2;//修改标志位的值
            c2.signal();//通知
        }finally {
            lock.unlock();
        }
    }

    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (flag != 2){
                //等待
                c2.await();
            }
            //干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()
                        +"::"+i+" 轮数"+loop);
            }
            //通知
            flag = 3;//修改标志位的值
            c3.signal();//通知
        }finally {
            lock.unlock();
        }
    }


    public void print15(int loop) throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (flag != 3){
                //等待
                c3.await();
            }
            //干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()
                        +"::"+i+" 轮数"+loop);
            }
            //通知
            flag = 1;//修改标志位的值
            c1.signal();//通知
        }finally {
            lock.unlock();
        }
    }
}
