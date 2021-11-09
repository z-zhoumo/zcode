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

        int[] next = kmpNext("abddabd");
        /*
        abddabd
        算法
            前缀                          后缀                      共有元素长
a                                                                      0
ab           a                          b                               0
abd         a,ab                        bd,d                            0
abdd        a,ab,abd                    bdd,dd,d                        0
abdda       a,ab,abd,abdd               bdda,dda,da,a                   1
abddab    a,ab,abd,abdd,abdda           bddab,ddab,dab,ab,b              2
abddabd   a,ab,abd,abdd,abdda,abdda    bddabd,ddabd,dabd,abd,bd,d       3
        最后：
            0000123



         */
        System.out.println(Arrays.toString(next));
    }

    //写出我们的kmp搜索算法

    /**
     *
     * @param str1  源字符串
     * @param str2  str2子串
     * @param next  部分匹配表，子串对应的部分匹配表
     * @return  如果是 -1 就是没有匹配到，否者返回第一个匹配的位置
     */
    public static int kmpSearch(String str1,String str2,int[] next){
        //遍历
        for (int i = 0,j = 0; i < str1.length(); i++) {
            //需要处理str1.charAt(i) ！= str2.charAt(j)
            //KMP算法核心点*******************公式
            while( j > 0&& str1.charAt(i) != str2.charAt(j)){
                 j = next[j-1];
            }
            if(str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if(j == str2.length()){
                return i-j+1;
            }
        }
        return -1;
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
