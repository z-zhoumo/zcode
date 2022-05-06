package com.juc.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*

 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        //创建线程 放数据
        for (int i = 1; i <= 5 ; i++) {
            final int num = i;
            new Thread(()->{
                myCache.put(num+"",num+"");
            },String.valueOf(i)).start();
        }
        //创建线程 取数据
        for (int i = 1; i <= 5 ; i++) {
            final int num = 1;
            new Thread(()->{
                myCache.get(num+"");
            },String.valueOf(i)).start();
        }
    }
}

//资源类
class MyCache{

    //创建读写锁的对象
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();


    //创建 map 集合
    private volatile Map<String,Object> map
           = new HashMap();
    //放数据
    public void put(String key,Object value){
        //添加写锁
        rwLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread()
                    .getName()+"写ing_______"+key);
            //暂停
                TimeUnit.MILLISECONDS.sleep(300);
        }catch (Exception e){
            e.printStackTrace();
        } finally{
            //释放写锁
            rwLock.writeLock().unlock();
        }

        //放数据
        map.put(key,value);

        System.out.println(Thread.currentThread()
        .getName()+"写完了>>>"+key);
    }


    //取数据
    public Object get(String key){
        //添加读锁
        rwLock.readLock().lock();
            Object result = null;
        try {
            System.out.println(Thread.currentThread()
                    .getName()+"读取ing ——————————"+key);
            //暂停
                TimeUnit.MILLISECONDS.sleep(300);
            result = map.get(key);
            System.out.println(Thread.currentThread()
                    .getName()+"读完了>>>>>>>>>>>"+key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放读锁
            rwLock.readLock().unlock();
        }
        return result;

    }
}
