package algorithm.kmp;

import java.util.Arrays;

/*
    1 先得到子串的部分匹配表
    2 使用部分匹配表完成 KMP 匹配



 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "bbc abcdab abcdabcdabde";
        String str2 = "abcdabd";

        int[] next = kmpNext("aaab");
        /*
        算法 a aa aaa


         */
        System.out.println(Arrays.toString(next));
    }

    //获取到一个字符串（子串）的部分匹配值表
    public static int[] kmpNext(String dest){
        //创建一个next 数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0; //如果字符串是长度为 1 部分匹配值就是 0
        for (int i = 1,j = 0; i < dest.length(); i++) {
            //当条件不匹配，我们需要从 next[j-1]获取新的 j
            //直到我们发现有 dest.charAt(i) == dest.charAt(j)成立退出
            while( j > 0 && dest.charAt(i) != dest.charAt(j) ){
                j = next[j-1];
            }
            //当此条件满足时，部分匹配值+1
            if(dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;

    }
}
