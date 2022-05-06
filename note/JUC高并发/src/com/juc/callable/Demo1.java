package com.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
    比较两个接口
    实现Runnable接口

    找一个类，既和Runnable有关系，又和Callable也又关系
    Runnable接口有实现类 FutureTask
    FutureTask 构造可以传递 Callable
 */
public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Runnable接口创建线程
        new Thread(new MyThread1(),"线程一").start();

        //Callable接口
//        new Thread(new MyThread2(),"线程二").start();错误

        FutureTask<Integer> futureTask1 = new FutureTask<>(new MyThread2());
        //Lamda
        FutureTask<Integer> futureTask2 = new FutureTask<>(()->{
            System.out.println(Thread.currentThread()
                    .getName() + "   come in callable");
            return 1024;
        });

        new Thread(futureTask2,"luck").start();
        new Thread(futureTask1,"marry").start();

        while (!futureTask2.isDone()){
            System.out.println("wait----");
        }
        System.out.println(Thread.currentThread()
                .getName() + "   come over");

        System.out.println(futureTask2.get());
        System.out.println(futureTask1.get());

        /*
            Future原理  未来任务

            1.老师上课，口渴了，去买票不合适，讲课线程继续。
               单开启一个线程，找班长帮我买水
               把水买回来，需要时候直接 get

            2. 4个同学， 1同学 1+2..5 , 2同学 10+11.。。+50
                       3同学60+61+62， 4同学 100+200
                2同学计算量比较大
                FutureTask 单开启线程给 2 同学计算，先汇总 1 3 4
                最后等 2 同学计算完成，统一汇总


            3. 考试，做会做的，最后看不会的做的题目

            汇总说一次
         */


    }
}

class MyThread1 implements Runnable{

    @Override
    public void run() {

    }
}


//实现Callable接口
class MyThread2 implements Callable{

    @Override
    public Integer call() throws Exception {

        return 200;
    }
}
