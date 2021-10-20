package leetcode.simple;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

/*

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。
找出那个只出现了一次的元素。
说明：
你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

 */
public class Only_one_int {

    public static void main(String[] args) {

        int nums[] = {4,1,2,2,1,33,33,4,5,6,5};
//        System.out.println(singleNumber(nums));
        System.out.println(SetMethod(nums));
    }


    public static int SetMethod(int [] nums){
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(!set.add(nums[i])){
                set.remove(nums[i]);
            }
        }
        return (int) set.toArray()[0];

    }


    public static int singleNumber(int[] nums) {
       Map<Integer,Boolean> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if(map.get(nums[i]) == null){
                map.put(nums[i],true);
            }else {
                map.put(nums[i],false);
            }
        }
        for(Map.Entry<Integer,Boolean> entry: map.entrySet()){
            if(entry.getValue() == true){
                return entry.getKey();
            }
        }
        return 0;
    }


    /*  使用异或运算，将所有值进行异或
        异或运算，相异为真，相同为假，所以 a^a = 0 ;
        0^a = a
        因为异或运算 满足交换律 a^b^a = a^a^b = b
        所以数组经过异或运算，单独的值就剩下了
*/
    public int singleNumber1(int[] nums) {
        int reduce = 0;
        for (int num : nums) {
            reduce =  reduce ^ num;
        }
        return reduce;
    }


}
