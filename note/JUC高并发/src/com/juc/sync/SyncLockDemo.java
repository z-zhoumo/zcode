package com.juc.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*

 */
public class SyncLockDemo {

    public synchronized void add(){
        add();
    }


    public static void main(String[] args) {

        //Lock 演示可重入锁 也叫递归锁 synchronized和lock 都是可重入锁
        Lock lock = new ReentrantLock();
        new Thread(()->{
            try {
                lock.lock();
                System.out.println(Thread.currentThread()
                        .getName()+"外层");
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread()
                            .getName()+"内层");
                }finally {
                    lock.unlock();
                }
            }finally {
                lock.unlock();
            }
        }).start();

        //创建一个新线程
        new  Thread(()->{
            lock.lock();
            System.out.println("aaa");
            lock.unlock();
        }).start();

//        new SyncLockDemo().add();
        // synchroized   可重入锁
//        Object o = new Object();
//        new Thread(()->{
//            System.out.println(Thread.currentThread().getName() + "外层");
//
//            synchronized (o){
//                System.out.println(Thread.currentThread().getName() + "中层");
//                synchronized (o){
//                    System.out.println(Thread.currentThread().getName() + "内层");
//                }
//            }
//
//        },"线程一   ").start();
    }
}
