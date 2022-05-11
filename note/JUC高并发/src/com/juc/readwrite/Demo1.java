package com.juc.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/*

 */
public class Demo1 {
    public static void main(String[] args) {
        //可重入读写锁对象
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

        //锁降级   在写操作时能进行读操作，叫锁降级
        //当先2 后1 时，只有--read，只能从写锁降级位读锁，不能读锁升级位写锁

        //获取读锁
        readLock.lock();
        System.out.println("---read");

        //1 获取写锁
        writeLock.lock();
        System.out.println("--write");
        //2 获取读锁
//        readLock.lock();
//        System.out.println("---read");
        //3 释放写锁
        writeLock.unlock();
        //4 释放读锁
        readLock.unlock();
    }
}
