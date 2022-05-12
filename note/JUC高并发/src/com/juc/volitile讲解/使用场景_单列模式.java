package com.juc.volitile讲解;


/*
单线程单列模式可以实现
main	 单列模式 constructMethod
true
true
true

多线程情况下（构造方法执行多次）
线程0	 单列模式 constructMethod
线程3	 单列模式 constructMethod
线程2	 单列模式 constructMethod
线程1	 单列模式 constructMethod

 */
class SingletonDemo {

    public static void main(String[] args) {
        //单线程
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());


        //多线程情况下
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            },"线程"+i).start();
        }
    }


    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName()
                + "\t 单列模式 constructMethod");
    }

    //DCL模式（double check lock 双端检查模式）
    // 有极小的概率不安全，因为有指令重排存在，需要加入 volatile 禁止指令重排
//    public static synchronized SingletonDemo getInstance() {
    public static SingletonDemo getInstance() {

        if (instance == null) {
            //使用同步代码快
            synchronized (SingletonDemo.class){
                if(instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

}
