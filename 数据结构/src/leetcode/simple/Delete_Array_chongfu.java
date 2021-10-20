package leetcode.simple;

/**
 * 删除排序数组中的重复项
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，
 * 使每个元素 只出现一次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组
 * 并在使用 O(1) 额外空间的条件下完成。
 *
 *
 * 112223
 *
 */
public class Delete_Array_chongfu {
    public static void main(String[] args) {
        int nums[] = {1,1,1,1,1,1,2};
        int i = removeDuplicates(nums);
        System.out.println(i);
    }

   public static int removeDuplicates(int[] nums){
        if(nums == null) return 0;
        if(nums.length == 1) return 1;

        int temp = 0;
       for (int i = 1; i < nums.length; i++) {
           if(nums[temp] != nums[i]){
               nums[++temp] = nums[i];
           }
       }
       return temp+1;
   }



}

