package leet.leetcode.editor.cn;
//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
//
// 算法的时间复杂度应该为 O(log (m+n)) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 
//输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
// Related Topics 数组 二分查找 分治 👍 4849 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution4 {
    public static void main(String[] args) {
        int nums1[] = {1, 2};
        int nums2[] = {3, 4};
        double medianSortedArrays = findMedianSortedArrays(nums1, nums2);


    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //分别指向 nums1和nums2的最后一位
        int m = nums1.length;
        int n = nums2.length ;
        //求出需要的元素
        int index = (m + n ) / 2;
        int nums[] = new int[index + 1];

        //得到需要的数组，感觉可以不用这么多空间
        int i,j,k;
        i=j=k=0;
        while (k!= index){
            if(nums1[i]<= nums2[j]){
                nums[k] = nums1[i];
                i++;
            }else {
                nums[k] = nums2[j];
                j++;
            }
            k++;
        }

        if ((m + n) % 2 == 0) {
            return (nums[index] + nums[index - 1]) / 2.0;
        }
        return nums[index];


    }
}
//leetcode submit region end(Prohibit modification and deletion)
