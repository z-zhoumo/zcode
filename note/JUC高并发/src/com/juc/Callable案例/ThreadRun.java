package com.juc.Callable案例;

class ThreadRun implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()
                    +"线程进入了run()");

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
