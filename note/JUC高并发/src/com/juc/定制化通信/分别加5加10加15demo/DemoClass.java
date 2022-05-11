package com.juc.定制化通信.分别加5加10加15demo;


import java.util.Locale;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DemoClass {

    //通信对象
    // 0--A
    // 1--B
    // 2--C

    private int num = 0;
    //声明锁
    private Lock lock = new ReentrantLock();
    //声明钥匙 a
    private Condition conditionA = lock.newCondition();
    //声明钥匙 b
    private Condition conditionB = lock.newCondition();
    //声明钥匙 c
    private Condition conditionC = lock.newCondition();

    //输出 5 次 A
    public void printA(int j){
        try {
            lock.lock();
            while (num != 0){
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName()+
                    "输出A"+j+"轮开始");
            //输出
            for (int i = 0; i < 5; i++) {
                System.out.println("A");
            }
            //开始打印 B
            num = 1;
            //唤醒
            conditionB.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //输出 10 次 A
    public void printB(int j){
        try {
            lock.lock();
            while (num != 1){
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName()+
                    "输出B"+j+"轮开始");
            //输出
            for (int i = 0; i < 10; i++) {
                System.out.println("B");
            }
            //开始打印 C
            num = 2;
            //唤醒
            conditionC.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //输出 15 次 C
    public void printC(int j){
        try {
            lock.lock();
            while (num != 2){
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName()+
                    "输出C"+j+"轮开始");
            //输出
            for (int i = 0; i < 15; i++) {
                System.out.println("C");
            }
            //开始打印 A
            num = 0;
            //唤醒
            conditionA.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }



}
