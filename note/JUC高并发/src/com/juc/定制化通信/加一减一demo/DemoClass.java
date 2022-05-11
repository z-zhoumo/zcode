package com.juc.定制化通信.加一减一demo;

// synchronized 方案
class DemoClass{
    //加减对象
    private int number = 0;
    /**
    *加1
    */
    public synchronized void increment() {
        try {
            while (number != 0){
            this.wait();
        }
        number++;
        System.out.println("--------"+Thread.currentThread().getName()+"加一成功----------,值为:"+ number);
        notifyAll();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
    *减一
    */
    public synchronized void decrement(){
        try {
            while (number == 0){
            	this.wait();
            }
        	number--;
        	System.out.println(" --------"+Thread.currentThread().getName()+"减一成功----------,值为:"+number);
        notifyAll();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
