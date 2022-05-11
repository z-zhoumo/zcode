package com.juc.Callable案例;

import java.util.concurrent.Callable;

//实现Callable接口
class ThreadCall implements Callable {

    @Override
    public Long call() throws Exception {
        try {
            System.out.println(Thread.currentThread().getName()
                    + "线程进入了call()方法，准备开始睡觉");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()
                    + "睡觉时间结束");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }
}
