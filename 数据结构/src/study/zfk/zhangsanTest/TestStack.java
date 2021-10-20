package study.zfk.zhangsanTest;

import java.util.Stack;

/**
 * @create 09-10 16:54
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.add("d");
        while (!stack.isEmpty()){
            System.out.println("*****"+stack.pop());
        }
    }

}
