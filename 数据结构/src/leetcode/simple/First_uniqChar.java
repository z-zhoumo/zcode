package leetcode.simple;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/*
给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。
如果不存在，则返回 -1。

示例：
s = "leetcode"
返回 0

s = "loveleetcode"
返回 2

提示：你可以假定该字符串只包含小写字母。
 */
public class First_uniqChar {
    public static void main(String[] args) {
        String s = "dddccdbba";
        int i = firstUniqChar(s);

        System.out.println(i);
    }


    public static int firstUniqChar(String s) {
       if(s == null){
           return -1;
       }else if(s.length() == 1){
           return 0;
       }
        //暴力
        char temp ;
        for (int i = 0; i < s.length(); i++) {
           temp = s.charAt(i);
            for (int j = 0; j < s.length(); j++) {
                if(i != j){
                    if(temp == s.charAt(j)){
                        break;
                    }
                }
                if(j == s.length()-1){//判断是否到最后一位数
                    return i;
                }

            }
        }
        return -1;
    }


    public static int ZMethod(String s){
        //通过map，统计次数
        Map<Character,Integer> map = new HashMap<>();
        char temp;
        for (int i = 0; i < s.length(); i++) {
            temp = s.charAt(i);
            if(map.containsKey(temp)){
                map.put(temp,map.get(temp)+1);
            }else {
                map.put(temp,1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if(map.get(s.charAt(i)) == 1){
                return i;
            }
        }
        return -1;

    }



    public static int ZMethod2(String s){
        //通过map，统计次数
        Map<Character,Integer> map = new HashMap<>();
        char temp;
        for (int i = 0; i < s.length(); i++) {
            temp = s.charAt(i);
            if(map.containsKey(temp)){
                map.put(temp,map.get(temp)+1);
            }else {
                map.put(temp,1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if(map.get(s.charAt(i)) == 1){
                return i;
            }
        }
        return -1;

    }



}
