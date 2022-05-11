package com.juc.定制化通信.加一减一demo;


/**
 * 场景 -两个线程,一个线程对当前数值加1,另一个线程对当前数值减1,要求用线程间通信
 *
 *
 * --------线程A加一成功----------,值为:1
 *  --------线程B减一成功----------,值为:0
 * --------线程A加一成功----------,值为:1
 *  --------线程B减一成功----------,值为:0
 * --------线程A加一成功----------,值为:1
 *  --------线程B减一成功----------,值为:0
 * --------线程A加一成功----------,值为:1
 *  --------线程B减一成功----------,值为:0
 * --------线程A加一成功----------,值为:1
 *  --------线程B减一成功----------,值为:0
 *
 */
public class Test {
//交替加减
	public static void main(String[] args){
//		DemoClass demoClass = new DemoClass();
		DemoClass2 demoClass = new DemoClass2();
        new Thread(() ->{
            for (int i = 0; i < 5; i++){
            	demoClass.increment();
            }
        },"线程A").start();
        new Thread(() ->{
            for (int i = 0; i < 5; i++){
                demoClass.decrement();
            }
        },"线程B").start();
    }
}




