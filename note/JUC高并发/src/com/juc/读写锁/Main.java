package com.juc.读写锁;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ResourceClass source = new ResourceClass();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                source.put("key:"+i,i);
            }
        },"写线程").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                source.get("key:"+i);
            }
        },"读线程" ).start();


    }
}
