package com.juc.volitile讲解;

import javafx.beans.binding.When;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/*
1 验证 volatile 的可见性
1.1 假如 int number = 0；
aaa	 come in
aaa	  now value:60
没有可见性，值被线程 a 修改后，
主线程中的值依旧是 number 的变量副本，值0
1.2 添加 volatile 可以解决可见性问题
aaa	 come in
aaa	  now value:60
main	 mission is over60
主线程中获得了 number 的最新值

2 验证 volatile 不保证原子性
2.1 不可分割，同时成功，同时失败
2.2 可以在方法上加上 synchronized 保证同步( 过于重了，降低效率）
2.3 解决原子性，并且高效率 使用 juc 的 atomicInteger
为什么 atomic 解决了？。。。


3 禁止指令重排
3.1
*/
public class Main {
    public static void main(String[] args) {
        MyData myData = new MyData();

        // 20 个线程，操作
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.addPlus();
                    myData.addAtomicNumber();
                }
            },"线程"+i).start();
        }

        while (Thread.activeCount() > 2){
            //后台两个线程， main 和 gc
            //只有在 20 个线程都结束后，才可以继续运行主线程
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()
        +" number value："+myData.number);
        //mainvalue：19180
        System.out.println(Thread.currentThread().getName()
                +" atomic number value："+myData.atomicNumber);
        //main atomic number value：20000

    }



    public static void isLook(){

        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(
                    Thread.currentThread().getName()
                            +"\t come in");
            //暂停
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()
                    +"\t  now value:"+myData.number);
        },"aaa" ).start();

        //主线程操作
        while (myData.number == 0){

        }
        System.out.println(Thread.currentThread().getName()
                +"\t mission is over"+myData.number);
    }
}



class MyData {

//    int number = 0;
    volatile int number = 0;
   AtomicInteger atomicNumber = new AtomicInteger();

   public void addAtomicNumber(){
       atomicNumber.getAndIncrement();
   }

    public void addTo60(){
        this.number = 60;
    }

    //使用了 volatile 修饰
//    public synchronized void addPlus(){
    public  void addPlus(){
        number++;
    }
}
