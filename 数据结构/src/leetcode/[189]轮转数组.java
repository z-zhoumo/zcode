package leetcode;//给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,2,3,4,5,6,7], k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右轮转 1 步: [7,1,2,3,4,5,6]
//向右轮转 2 步: [6,7,1,2,3,4,5]
//向右轮转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 
//输入：nums = [-1,-100,3,99], k = 2
//输出：[3,99,-1,-100]
//解释: 
//向右轮转 1 步: [99,-1,-100,3]
//向右轮转 2 步: [3,99,-1,-100] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 0 <= k <= 10⁵ 
// 
//
// 
//
// 进阶： 
//
// 
// 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。 
// 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？ 
// 
//
// 
// 
//
// 
// 
// Related Topics 数组 数学 双指针 👍 1272 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution189 {
    public static void main(String[] args) {
        int[] nums = {0,1,2,3,4,5,6,7,8};
//        int[] nums = {0,1,2,3,4,5,6,7,8};
        //            6 7 8 0 1 2 3 4 5
        new Solution189().rotate(nums,1);
    }
//    public void rotate(int[] nums, int k) {
//        int len = nums.length;
//        k = k%len;
//        int val = len - k;
//        int index;
//        int temp = 0;
//        //6 7 8 0 1 2 3 4 5
//        for(int i = 0 ;i < len ;i ++){
//            if(i < k){
//                index = (i+ val)%len;
//                temp = nums[i];
//                nums [i] = nums[ index];
//                nums[index]  = nums[i+k];
//                nums[i+k] = temp;
//            }else {
//
//            }
//
//        }
//
//    }
public void rotate(int[] nums, int k) {
    int n = nums.length;
    k = k % n;
    int count = gcd(k, n);
    for (int start = 0; start < count; ++start) {
        int current = start;
        int prev = nums[start];
        do {
            int next = (current + k) % n;
            int temp = nums[next];
            nums[next] = prev;
            prev = temp;
            current = next;
        } while (start != current);
    }
}

    public int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
