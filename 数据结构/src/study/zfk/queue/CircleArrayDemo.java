package study.zfk.queue;

import java.util.Scanner;

/**
 * @author zfk
 * @create 2021-08-15 22:13
 */
public class CircleArrayDemo {
    public static void main(String[] args) {
        System.out.println("环形数组");
        CircleArray aq = new CircleArray(4);
        char key = ' ';//接收用户输出
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s--show:显示队列");
            System.out.println("e--exit:退出");
            System.out.println("a--add:添加数据到队列");
            System.out.println("h--head:查看队列头的数据");
            System.out.println("g--getQueue:取出数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    aq.showQueue();
                    break;
                case 'e':
                    loop = false;
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
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }

        }
        System.out.println("退出");


    }


}

class CircleArray {

    private int maxSize;//数组最大容量
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;// 该数组用于存放数据，模拟队列

    //创建队列的构造器
    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    //判断是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("数组已满，无法添加");
            return;
        }
        //直接将数据加入就可以
        arr[rear] = n;
        //将rear后移，这里必须考虑取模，（取余）
        rear = (rear + 1) % maxSize;
    }


    //获取数据队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        //分析出front是指向队列的第一个元素
        //保存front---》front后移(取余）-----》返回临时保存变量
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所以数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空");
            return;
        }
        for (int i = front; i <front+size(); i++) {
            System.out.printf("arr[%d]===>%d\n",
                    i % maxSize,
                    arr[i % maxSize]);

        }
    }

    //求出当前队列的有效数据
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }

}
