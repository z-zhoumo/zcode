package com.juc.sync;

import javax.management.ObjectName;
import java.util.concurrent.TimeUnit;

/*
    死锁演示
    验证死锁
    1 jps	类似 linux的 ps -ef
    2 jstack	jvm自带跟踪堆栈的工具

    具体使用:
    1 命令行 Terminal 输入
      jps -l 找到当前进程
      得到5840 com.juc.sync.DeadLock
    2 根据对应端口号，检查
      jstack 5840
      得到Found 1 deadlock.


 */
public class DeadLock {

    //创建两个对象
    static Object a = new Object();
    static Object b = new Object();

    public static void main(String[] args) {

        new Thread(()->{
            synchronized(a){
                System.out.println(Thread
                .currentThread().getName()+"持有锁a，想获取b");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized  (b){
                    System.out.println(Thread.currentThread()
                    .getName()+"获取b");
                }
            }
        },"A").start();

        new Thread(()->{
            synchronized(b){
                System.out.println(Thread
                        .currentThread().getName()+"持有锁b，想获取a");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a){
                    System.out.println(Thread.currentThread()
                            .getName()+"获取a");
                }
            }
        },"B").start();
    }

}
