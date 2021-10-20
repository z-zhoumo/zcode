package com.juc.juc;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*

 */
public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        // 6部汽车，3个车位

        //定义 3 个停车位  创建Semaphore，设置许可数量
        Semaphore semaphore = new Semaphore(3);

        //模拟 6 两汽车
        for (int i = 1; i <= 6; i++) {
            //停车
            new Thread(()->{
                try {
                    //抢占车位  获取许可
                    semaphore.acquire();
                    System.out.println(Thread
                            .currentThread().getName()+"抢到了");
                    //设置随机停车时间
                    TimeUnit.SECONDS.sleep(new Random()
                            .nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放  释放
                    semaphore.release();
                    System.out.println(Thread.currentThread()
                    .getName()+"----------溜溜球");
                }
            },"汽车"+i).start();

        }
    }
}
