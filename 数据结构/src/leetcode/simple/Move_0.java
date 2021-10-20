package leetcode.simple;


import java.util.Arrays;

/*
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，
同时保持非零元素的相对顺序。

示例:
输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
说明:

必须在原数组上操作，不能拷贝额外的数组。
尽量减少操作次数。

 */
public class Move_0 {
    public static void main(String[] args) {
        int [] nums = {0,1,0,3,12};
//        int[] nums = {0,0,0,0,0,0,1,9,3};
//        moveZeroes(nums);
        method2(nums);

        System.out.println(Arrays.toString(nums));
    }

    public static void moveZeroes(int[] nums) {

       int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 0){
                if(p == 0 ){//第一次进入将 i 的值赋给 p
                    p = i+1;
                }
                while(true){
                    if(p >= nums.length){//当 p 为length时，说明数组遍历完
                        return;
                    }
                   if(nums[p] != 0 ){//找到不为 0 的值，将其赋值给 nums[i]
                      nums[i] = nums[p];
                      nums[p] = 0;
                      break;
                   }
                   p++;
                }

            }
        }

    }


    /*
    统计0的个数，赋值，交换
     */
    public static void method2(int[] nums){
         int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                nums[index++] = nums[i];
            }
        }

        while (index < nums.length){
            nums[index++] = 0;
        }

    }



}
