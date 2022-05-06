package com.juc.sync;

/*
    1 创建资源类，定义属性和操作方法
    2 判断 干活 通知


    wait
    对于某一个参数的版本，实现中断和虚假唤醒是可能的，
    而且此方法应始终在循环中使用
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
        Share share = new Share();
        int  num =0;

        new Thread(()->{
            for (int i = 1; i <100 ; i++) {
                try {
                    share.decr();//---
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"线程一" ).start();

        new Thread(()->{
            for (int i = 1; i <111 ; i++) {
                try {
                    share.incr();//++
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"线程二" ).start();

        new Thread(()->{
            for (int i = 1; i <34 ; i++) {
                try {
                    share.incr();//++
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"线程三" ).start();

        new Thread(()->{
            for (int i = 1; i <555 ; i++) {
                try {
                    share.decr();//---
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"线程四" ).start();


    }
}

class Share{

    private int number = 0;

    //+1
    public synchronized void incr() throws InterruptedException {
        //第二步 判断 干活 通知
        while (number != 0  ){
            this.wait();//哪里跌倒，哪里站起来
        }

        //number 值是 0 就++
        number++;
        System.out.println(Thread.currentThread()
                .getName() + "：+：" + number);

        //通知其他线程
        this.notify();

    }
    //-1
    public synchronized void decr() throws InterruptedException {
        while(number != 1){
            this.wait();
        }

        number--;
        System.out.println(Thread.currentThread()
                .getName() + "：---：" + number);

        this.notify();
    }
}
