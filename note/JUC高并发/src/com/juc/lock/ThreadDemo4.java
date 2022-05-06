package com.juc.lock;



import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/*

 */
public class ThreadDemo4 {
    public static void main(String[] args) {
        //创建 ArrayList 集合
//        List<String> list = new ArrayList<>();
        //通过 Vector
//        List<String> list = new Vector<>();
        //Collections 解决
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

        //写实复制技术  每次写时，复制一份，独立写，在合并
//        List<String> list = new CopyOnWriteArrayList<>();
//        for (int i = 0; i < 10 ; i++) {
//            new Thread(()->{
//                //向集合添加内容  ConcurrentModificationException
//              list.add(UUID.randomUUID().toString().substring(0,8));
//              // 从集合获取内容
//                System.out.println(list);
//            },"线程"+String.valueOf(i)).start();
//
//        }


        //HashSet 演示
//        Set<String> set = new HashSet<>();
        //
//        Set<String> set = new CopyOnWriteArraySet<>();
//        for (int i = 0; i < 100; i++) {
//            new Thread(() -> {
//                //向集合添加内容  ConcurrentModificationException
//                set.add(UUID.randomUUID().toString().substring(0, 8));
//                // 从集合获取内容
//                System.out.println(set);
//            }, "线程" + String.valueOf(i)).start();
//        }


        //演示HashMap
//        Map<String, String> map = new HashMap<>();
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            String key = String.valueOf(i);
            new Thread(() -> {
                //向集合添加内容  ConcurrentModificationException
                map.put(key, UUID.randomUUID().toString().substring(0, 8));
                // 从集合获取内容
                System.out.println(map);
            }, "线程" + String.valueOf(i)).start();
        }


    }


}
