package study.zfk.stack;

/**
 * @create 09-14 22:59
 */
public class ArrayStack {

    private int maxSize;
    private int[] stack;
    private int top = -1;
    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }
    //入栈
    public void push(int value){
        if(isFull()){
            throw new RuntimeException("栈满，无法添加");
        }
        stack[++top] = value;
    }
    //出栈
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空，无法出栈");
        }
        return stack[top--];
    }
    //遍历栈
    public void list(){
        if(isEmpty()){
            System.out.println("栈空");
        }
        for (int i = top; i >=0; i--) {
            System.out.println("*******"+stack[i]);
        }
    }

    //返回栈顶的值
    public int peek(){
        return stack[top];
    }

    //返回运算符的优先级，自定义的，使用数字表示
    public int priority(int oper){
        if(oper == '+'||oper == '-'){
            return 0;
        }else  if(oper == '*'||oper == '/'){
            return 1;
        }else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val){
        return val=='+'||val=='-'||val=='*'||val=='/';
    }

    //计算方法
    public int cal(int num1 ,int num2 ,int oper){
        int res = 0;
        switch (oper){
            case '+':
              res = num1+num2;
              break;
            case '-':
                res = num1-num2;
                break;
            case '*':
                res = num1*num2;
                break;
            case '/':
                if(num2 == 0){
                    System.out.println("被除数不能为0");
                    res = 0;
                }
                res = num1/num2;
                break;
        }
        return res;

    }



}
