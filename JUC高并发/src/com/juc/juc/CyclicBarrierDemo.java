package com.juc.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
    集齐 7 颗龙珠
 */
public class CyclicBarrierDemo {

    //创建固定值
    public static final int NUMBER = 7;
    public static void main(String[] args) {
        //创建CyclicBarrier
        CyclicBarrier cyclicBarrier =
                new CyclicBarrier(NUMBER,()->{
                    System.out.println("集齐 7 颗龙珠,召唤");
                });
        //集齐 7 颗龙珠
        for (int i = 1; i <= 7; i++) {
            new Thread(()->{


                try {
                    System.out.println(Thread
                            .currentThread().getName()
                            +"星龙珠ok");
                    //等待
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            },String.valueOf(i)).start();
        }
    }
}
