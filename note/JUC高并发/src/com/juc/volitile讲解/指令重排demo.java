package com.juc.volitile讲解;

import java.awt.*;

public class 指令重排demo {

    int a = 0;
    boolean flag = false;

    public void method01(){
        a = 1;
        flag = true;
    }

    //在多线程环境中线程交替执行，由于编译器优化重排存在
    //可能出现
    // 线程a 首先执行语句 flag = true; 然后切换位其他线程执行
    // 此时，线程b 将判断正确， a = 0;导致结果出现变化。
    public void method02(){
        if(flag){
            a = a+5;
            System.out.println("a:"+a);
        }
    }
}
