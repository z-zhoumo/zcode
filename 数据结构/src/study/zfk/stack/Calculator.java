package study.zfk.stack;

import java.util.Scanner;

/**
 * @create 09-15 0:30
 */
public class Calculator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack charStack = new ArrayStack(10);
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char c = ' ';
        String key ="";
            System.out.println("输入算式");
             key = scan.next();
            char[] arr = key.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                c = arr[i];
                //判断是否是操作符
                if (charStack.isOper(c)) {
                    if (!charStack.isEmpty()) {
                        if (charStack.priority(c) <= charStack.priority(charStack.peek())) {
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            oper = charStack.pop();
                            res = numStack.cal(num2, num1, oper);
                            numStack.push(res);
                            charStack.push(c);
                        } else {
                            charStack.push(c);
                        }
                    } else {
                        charStack.push(c);
                    }
                } else {
                    numStack.push(c - 48);
                }
            }


        //扫描完毕
        while (!charStack.isEmpty()) {
             num1 = numStack.pop();
             num2 = numStack.pop();
             oper = charStack.pop();
             res = charStack.cal(num2, num1, oper);
            numStack.push(res);
        }
        System.out.println(key+" = "+numStack.pop());

    }

}

