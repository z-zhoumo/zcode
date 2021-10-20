package com.juc.lock;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/*

 */
public class ThreadDemo2 {
    public static void main(String[] args) {
        Share share = new Share();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();//++
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"线程一").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.decr();//--
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"线程二").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    share.incr();//++
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"线程三").start();
    }
}

class Share{
    private int number = 0;

    //创建可重入锁
    private Lock lock = new ReentrantLock();
    private Condition condition =lock.newCondition();

    //++1
    public void incr() throws InterruptedException {
        lock.lock();

        try {
            //判断
            while (number != 0){
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName()
                    + "++" + number);
            condition.signalAll();//通知其他线程
        }finally {
            lock.unlock();
        }

    }


    //--1
    public void decr() throws InterruptedException {
        lock.lock();

        try {
            //判断
            while (number != 1){
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName()
                    + "--" + number);
            condition.signalAll();//通知其他线程
        } finally {
            lock.unlock();
        }

    }

}
