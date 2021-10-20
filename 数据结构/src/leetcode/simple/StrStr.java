package leetcode.simple;

/*
实现 strStr() 函数。
给你两个字符串 haystack 和 needle ，
请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
如果不存在，则返回  -1 。

说明：

当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。



示例 1：
输入：haystack = "hello", needle = "ll"
输出：2
示例 2：
输入：haystack = "aaaaa", needle = "bba"
输出：-1
示例 3：
输入：haystack = "", needle = ""
输出：0

 */
public class StrStr {
    public static void main(String[] args) {
//        String s1 = "aaagdflg";
//        String s2 = "lg";
        String s1 = "a";
        String s2 ="";
        int i = strStr(s1, s2);
        System.out.println(i);
    }

    public static int strStr(String haystack, String needle) {
        if(haystack.equals(needle) ||
                needle.length() == 0){
            return 0;
        }else if(haystack.length() < needle.length() ){
            return -1;
        }

        int temp = 0;
        int cur;
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            cur = i;
            while (needle.charAt(temp) == haystack.charAt(cur)){
                temp++;
                cur++;
                if(temp == needle.length()){
                    break;
                }
            }
            if(temp == needle.length()){
                return i;
            }
            temp = 0;


        }
        return -1;
    }

    
}
