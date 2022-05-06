package demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo01 {
    public static void main(String[] args) {
        int[] nums = {1, 9, 8, 7, 4, 2, 5, 6, 7, 8, 9};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
        int[] ints = Arrays.stream(nums).filter(item -> item > 6).toArray();
        System.out.println(Arrays.toString(ints));


    }

    public static void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public static void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }


    public static void quickSort(int[] nums, int left, int right) {
      int p1 = left;
      int p2 = right;
      int midVal = nums[(left+right)/2];

    }
}
