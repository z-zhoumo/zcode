package leetcode.simple;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
注意：若s 和 t中每个字符出现的次数都相同，则称s 和 t互为字母异位词。


示例1:

输入: s = "anagram", t = "nagaram"
输出: true
示例 2:

输入: s = "rat", t = "car"
输出: false

 */
public class Is_Anagram {
    public static void main(String[] args) {
        String s = "zhangsan";
        String t = "sanzhang";
        boolean anagram = isAnagram(s, t);
        System.out.println(anagram);
    }


    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()){
            return false;
        }
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
            map.put(t.charAt(i),map.getOrDefault(t.charAt(i),0)-1);
        }

        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            if(entry.getValue() != 0){
                return false;
            }
        }

        return true;
    }

    public static boolean method_2(String s,String t){
        if (s.length() != t.length())
            return false;
        int[] letterCount = new int[26];
        //统计字符串s中的每个字符的数量
        for (int i = 0; i < s.length(); i++)
            letterCount[s.charAt(i) - 'a']++;
        //减去字符串t中的每个字符的数量
        for (int i = 0; i < t.length(); i++)
            letterCount[t.charAt(i) - 'a']--;
        //如果数组letterCount的每个值都是0，返回true，否则返回false
        for (int count : letterCount){
            if (count != 0){
                return false;
            }
        }

        return true;


    }

}
