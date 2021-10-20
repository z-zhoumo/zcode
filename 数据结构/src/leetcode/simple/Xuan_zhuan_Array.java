package leetcode.simple;

import java.util.Arrays;

/**
 *给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

 * 进阶：
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为O(1) 的 原地 算法解决这个问题吗？
 *  
 *
 *  input  nums = [1,2,3,4,5,6,7], k = 3
 * output  [5,6,7,1,2,3,4]
 */
public class Xuan_zhuan_Array {
    public static void main(String[] args) {
//        int nums[] ={-1,-100,3,99};
        int nums[] ={1,2,3,4,5,6,7,8};
        rotate2(nums,2);
        System.out.println(Arrays.toString(nums));


    }

    public static void rotate(int[] nums , int k){
        int temp = nums[k];
        int cur = nums[k];
        for (int i = 0; i < nums.length-k; i++) {
            cur = nums[i];
            nums[i] = nums[k];
            nums[k] = nums[i];
            k++;

        }
    }

    //O(n)
    public static void rotate2(int[] nums , int k){
       int temp = 0;
       k = k%nums.length;//防止k超出数组下标
       int arr[] =new int [nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp = (k+i)%arr.length;//得到在数组中的角标
            arr[i] = nums[temp];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = arr[i];
        }

    }
}
