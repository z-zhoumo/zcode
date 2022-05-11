package com.juc.Callable案例;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
    比较两个接口
    实现Runnable接口

    找一个类，既和Runnable有关系，又和Callable也又关系
    Runnable接口有实现类 FutureTask
    FutureTask 构造可以传递 Callable
 */

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
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //声明 runable
        ThreadRun threadRun = new ThreadRun();
        //声明 callable
        ThreadCall threadCall = new ThreadCall();
        //声明 future-callable
        FutureTask<Long> futureTask = new FutureTask<>(threadCall);

        //线程一
        new Thread(futureTask,"线程一").start();
        for (int i = 0; i < 2; i++) {
            Long result = futureTask.get();
            System.out.println(result);
        }

        //线程二
        new Thread(threadRun,"线程二").start();

        //线程一线程进入了call()方法，准备开始睡觉
        //线程一睡觉时间结束
        //1652198874741
        //1652198874741
        //线程二线程进入了run()


    }
}



