package com.juc.定制化通信.加一减一demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Lock方案
class DemoClass2 {
    //加减对象
    private int number = 0;
    
    //声明锁
    private Lock lock = new ReentrantLock();
    //声明钥匙
    private Condition condition = lock.newCondition();
    
    //加一
    public void increment(){
        try{
            lock.lock();
            while(number != 0){
                condition.await();
            }
            number ++ ;
            System.out.println("--------" +Thread.currentThread().getName() +"加一成功----------,值为:"+number);
            condition.signalAll();
		}catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    
    //减一
    public void decrement(){
        try{
            lock.lock();
            while(number == 0){
                condition.await();
            }
            number -- ;
            System.out.println("--------" +Thread.currentThread().getName() +"减一成功----------,值为:"+number);
            condition.signalAll();
		}catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    
}