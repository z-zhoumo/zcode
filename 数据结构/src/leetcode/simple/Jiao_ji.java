package leetcode.simple;

import java.util.*;

/*
给定两个数组，编写一个函数来计算它们的交集。
 */
public class Jiao_ji {
    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        int[] res = intersect(nums1, nums2);
        System.out.println(Arrays.toString(res));

    }

    public int[] sortMethod(int[] nums1, int[] nums2) {

        return null;
    }


    public static int[] intersect(int[] nums1, int[] nums2) {

        if(nums1 == null && nums1.length == 0){
            return nums2;
        }else if(nums2 == null && nums2.length == 0){
            return nums1;
        }

      Map<Integer,Integer> map = new HashMap<>();
        int temp = 0;
        //将 nums1 的值存入map，key为nums对应值，value为值出现的次数
        for (int i = 0; i < nums1.length; i++) {
            if(map.containsKey(nums1[i])){
                temp = map.get(nums1[i])+1;
                map.put(nums1[i],temp);
            }else{
                map.put(nums1[i],1);
            }
        }
        //将 nums2 的值与 map 校验，包含该值，将对应的value-1,把值存入list
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if(map.containsKey(nums2[i])){
                temp = map.get(nums2[i]);
                map.put(nums2[i],temp-1);
                if(temp >= 1){
                    list.add(nums2[i]);
                }

            }
        }

        int [] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }


        return res;


    }
}
