package com.juc.定制化通信.分别加5加10加15demo;

public class Main {
    public static void main(String[] args) {

        DemoClass demoClass = new DemoClass();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                demoClass.printA(i);
            }
        },"A线程").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                demoClass.printB(i);
            }
        },"B线程").start();

        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                demoClass.printC(i);
            }
        },"C线程").start();

    }
}
