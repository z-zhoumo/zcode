package study.zfk.stack;

import java.util.Scanner;

/**
 * @create 09-14 22:59
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(2);
        String key = "";
        boolean loop = true;//是否退出菜单
        Scanner scan = new Scanner(System.in);

        while (loop){
            System.out.println("show   显示栈");
            System.out.println("exit    退出栈");
            System.out.println("push    入栈");
            System.out.println("pop     出栈");
            key = scan.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "exit":
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入~~");
                    int value = scan.nextInt();
                    try {
                        stack.push(value);

                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "pop":
                    try {
                        int pop = stack.pop();
                        System.out.println("出栈的值是"+pop);
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("输入有误");
                    break;
            }
        }


    }
}
