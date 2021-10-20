package study.zfk.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @create 09-15 15:38
 */
@SuppressWarnings("all")
public class PolandNotation {
    public static void main(String[] args) {

        String expression = "1+(2+3)*4-5";
        List<String> list = toInfixExpressionList(expression);
        List<String> suffixExpression = parseSuffixExpressionList(list);
        System.out.println("middle" + list);
        System.out.println("suffix" + suffixExpression);
        System.out.println("result = " + calculate(suffixExpression));

//        //定义逆波兰表达式
//        //4*5-8+60+8/2  76
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
//        List<String> listString = getListString(suffixExpression);
//        int calculate = calculate(listString);
//        System.out.println(suffixExpression+" = "+calculate);

    }

    /*
     * 将一个逆波兰表达式，依次放入到ArrayList中
     * */
    public static List<String> getListString(String suffixExpression) {
        //分割suffixExpression
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }


    //完成对逆波兰表达式的运算
    /*
     * 1）从左至又扫描，将3和4压入堆栈
     * 2）遇到+运算符，弹出 3 4 （栈顶元素，3为次顶元素），计算3+4的值，得7，将7入栈
     * 3）将 5 入栈；
     * 4）* 运算符，弹出5 7 ，计算 7*5=35 ，将35入栈
     * 5）将 6 入栈
     * 最后是 - 运算符，计算 35 - 6 的值，即29，由此得出最终结果
     * */
    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        //遍历
        for (String item : list) {
            //使用正则表达式取出数据
            if (item.matches("\\d+")) {
                stack.push(item); //匹配的是多位数
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }


    /**
     * 将字符串转换为中缀表达式数组
     * 48 ~ 57 为计算符号
     * '0'[48]  ---   '9'[57]
     *
     * @param s
     * @return
     */
    public static List<String> toInfixExpressionList(String s) {
        //创建list，存放string
        List<String> ls = new ArrayList<>();
        int i = 0;
        String str;
        char c;
        do {
            if ((c = s.charAt(i)) < 48 ||
                    (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {
                str = "";
                while (i < s.length() &&
                        (c = s.charAt(i)) >= 48 &&
                        (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    /*
   中缀表达转后缀表达
   1) 初始化两个栈：运算符栈s1和储存中间结果的栈s2；
   2) 从左至右扫描中缀表达式；
   3) 遇到操作数时，将其压s2；
   4) 遇到运算符时，比较其与s1栈顶运算符的优先级：
       1.如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
       2.否则，若优先级比栈顶运算符的高，也将运算符压入s1；
       3.否则，将s1栈顶的运算符弹出并压入到s2中，
         再次转到(4.1)与s1中新的栈顶运算符相比较；
   5) 遇到括号时：
     (1) 如果是左括号“(”，则直接压入s1
     (2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，
       直到遇到左括号为止，此时将这一对括号丢弃
   6) 重复步骤2至5，直到表达式的最右边
   7) 将s1中剩余的运算符依次弹出并压入s2
   8) 依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达
   * */
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for (String ss : ls) {
            //如果是一个数，加入栈
            if (ss.matches("\\d+")) {
                s2.add(ss);
            } else if (ss.equals("(")) {
                s1.push(ss);
            } else if (ss.equals(")")) {
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，
                //        直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                //将（ 弹出栈，消除小括号
                s1.pop();
            } else {
                //当ss的优先级小于等于栈顶运算符
                while (s1.size() != 0 &&
                        Operation.getValue(s1.peek()) >= Operation.getValue(ss)){
                        s2.add(s1.pop());
                }
                //将ss压入栈
                s1.push(ss);
            }
        }
        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }


}

//编写一个Operation 可以返回一个运算符 对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在运算符");
                break;
        }
        return result;
    }


}