package leetcode.simple;

import study.zfk.recursion.RecursionTest;

public class Reverse_String {
    public static void main(String[] args) {

        char[] s = new char[]{'1','2','3','n','9'};
        reverseString(s);

    }


    public static void reverseString(char[] s) {
        char p;
        int temp = s.length-1;
        for (int i = 0; i < s.length/2; i++) {
            p = s[i];
            s[i] = s[temp];
            s[temp] = p;
            temp--;
        }
    }
}
