package leetcode.simple;


import com.sun.org.apache.xerces.internal.xni.XNIException;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

/*
给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。

如果反转后整数超过 32 位的有符号整数的范围[−2^31, 2^31− 1] ，就返回 0。

假设环境不允许存储 64 位整数（有符号或无符号）。


示例 1：
输入：x = 123
输出：321

示例 2：
输入：x = -123
输出：-321

示例 3：
输入：x = 120
输出：21

示例 4：
输入：x = 0
输出：0


 */
public class Reverse_Int {
    public static void main(String[] args) {
//        int num = 1534236469;
        int num = 123464;
//        int reverse = reverse(num);
        int i = method2(num);

    }

    public static int reverse(int x) {
        if(x == 0){
            return 0;
        }

       int[] nums=new int[10];
        int index = 0;
        int temp ;
        boolean flag = x > 0 ? true:false;
        if(!flag){
            x=-x;//确保 x > 0
        }
        //将 x 放入数组 nums
      while (x != 0){
          temp = x%10;
         nums[index++] = temp;
          x /= 10;
      }
      //数组转字符串
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < index; i++) {
            str.append(nums[i]);
        }
        int res = 0 ;

        try {
             res = Integer.valueOf(str.toString());
        }catch (Exception e){
            return 0;
        }

        if(!flag){
            return -res;
        }
        return res;

    }


    public static int method2(int x) {
        int res = 0;
        while (x != 0){
            int t = x%10;
            int newRes = res*10+t;
            //数值溢出，被转换，判断
            if((newRes-t)/10 != res){
                return 0;
            }
            res = newRes;
            x /=10;
        }
        return res;
    }


}
