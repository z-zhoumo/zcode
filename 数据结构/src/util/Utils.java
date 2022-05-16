package util;

public class Utils {

    public static int[] swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
        return nums;

    }
}
