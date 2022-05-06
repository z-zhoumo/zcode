package com.juc.sync;


public class SaleTicket {
    public static void main(String[] args) {
        //创建多个线程，调用资源类的方法
        Ticket ticket = new Ticket();
        //线程一
        new Thread(new Runnable() {
            @Override
            //实现卖票
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"线程一").start();

        //线程二
        new Thread(new Runnable() {
            @Override
            //实现卖票
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"线程二").start();

        //线程三
        new Thread(new Runnable() {
            @Override
            //实现卖票
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"线程三").start();
    }

}

//创建资源类
class Ticket{

    private int number = 30;//票

    //操作方法：买票
    public synchronized void sale(){
        //判断：是否有票
        if(number > 0){
            System.out.println(Thread.currentThread()
            .getName()+"卖出："+(number--)+" 剩下："+number);
        }
    }
}
