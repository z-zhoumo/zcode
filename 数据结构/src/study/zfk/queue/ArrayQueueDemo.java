package study.zfk.queue;

import java.util.Scanner;

/**
 * @author zfk
 * @create 2021-08-15 20:27
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue aq = new ArrayQueue(3);
        char key=' ';//接收用户输出
        Scanner scanner=new Scanner(System.in);
        boolean loop=true;
        while (loop){
            System.out.println("s--show:显示队列");
            System.out.println("e--exit:退出");
            System.out.println("a--add:添加数据到队列");
            System.out.println("h--head:查看队列头的数据");
            System.out.println("g--getQueue:取出数据");
            key=scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    aq.showQueue();
                    break;
                case 'e':
                    loop=false;
                    break;
                case 'a':
                    System.out.println("输出一个数字");
                    aq.addQueue(scanner.nextInt());
                    break;
                case 'h':
                    System.out.println(aq.headQueue());
                    break;
                case 'g':
                   try {
                       aq.getQueue();
                   }catch (RuntimeException e){
                       System.out.println(e.getMessage());
                   }
                    break;
            }
        }



    }
}

//使用数组模拟队列编写一个Array Queue类
class ArrayQueue {

    private int maxSize;//数组最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;// 该数组用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    //判断是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //判断是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("数组已满，无法添加");
            return;
        }
        arr[++rear] = n;
    }

    //获取数据队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;
        return arr[front];
    }

    //显示队列的所以数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //显示队列头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front + 1];
    }

}
