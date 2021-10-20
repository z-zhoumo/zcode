package study.zfk.stack;
import java.util.Scanner;

/**
 * @create 09-15 0:30
 *
 *
 * 老师用的 expression.substring(index+1,index+2),charAt(0)
 * 个人理解，对于大量的数来说，更加方便，肯定写的比我好
 */
public class CalculatePlus {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack charStack = new ArrayStack(10);
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char c = ' ';
        String temp = "";
        String key = "";
        while (true){
        System.out.println("输入算式");
         key = scan.next();
        char[] arr = key.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            c = arr[i];
            //判断是否是操作符
            if(charStack.isOper(c)){
                if(!charStack.isEmpty()){
                    if(charStack.priority(c) < charStack.priority(charStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = charStack.pop();
                        res = numStack.cal(num2,num1,oper);//如果是除法，必须是num2 / num1
                        numStack.push(res);
                        charStack.push(c);
                    }else{
                        charStack.push(c);
                    }
                }else {
                    charStack.push(c);
                }
            }else{
                //如果下一个字符是数字，拼接
                //判断是否是最后一个
               if(1+i < arr.length){//不是最后一个字符
                   //判断下一个字符是否为运算符
                   if(!charStack.isOper(arr[i+1])){
                       temp =String.valueOf(c)+String.valueOf(arr[i+1]);
                       numStack.push(Integer.parseInt(temp));
                       i++;
                   }else {//为运算符
                       numStack.push(c-48);
                   }
               }else {//是最后一个，直接入栈
                   numStack.push(c-48);
               }
            }
        }

        //扫描完毕
        while (!charStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = charStack.pop();
            res = charStack.cal(num1, num2, oper);
            numStack.push(res);
        }
            int result = numStack.pop();
            System.out.println(key+" = "+result);
        }
    }
}


