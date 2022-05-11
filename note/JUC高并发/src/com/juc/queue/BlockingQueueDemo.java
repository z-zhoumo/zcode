package com.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*

 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        //创建阻塞队列  长度为 3
        BlockingQueue<String> blockingQueue =
                new ArrayBlockingQueue<>(3);

        //第一组
        System.out.println(blockingQueue.add("a"));//true
        System.out.println(blockingQueue.add("b"));//true
        System.out.println(blockingQueue.add("c"));//true

        System.out.println(blockingQueue.element());//a
//        System.out.println(blockingQueue.add("d"));//IllegalStateException

        System.out.println(blockingQueue.remove());//a
        System.out.println(blockingQueue.remove());//b
        System.out.println(blockingQueue.remove());//c
//        System.out.println(blockingQueue.remove());//NoSuchElementException

//        //第二组   特殊值
        System.out.println("**************** 二 *******");
        System.out.println(blockingQueue.offer("a"));//true
        System.out.println(blockingQueue.offer("b"));//true
        System.out.println(blockingQueue.offer("c"));//true
        System.out.println(blockingQueue.offer("ddd"));//false

        System.out.println(blockingQueue.poll());//a
        System.out.println(blockingQueue.poll());//b
        System.out.println(blockingQueue.poll());//c
        System.out.println(blockingQueue.poll());//null


    }
}
