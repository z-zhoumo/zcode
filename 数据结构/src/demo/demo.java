package demo;

import util.Utils;

import java.util.Arrays;
import java.util.List;

public class demo {
    public static void main(String[] args) {
        int arr[] = new int[]{5, 33, 0, 123, 1, 2, 2, 1, 4, 23, 123, 123, 1, 2, 42, 13, 423, 4, 12};
//        bubbleSort(arr);
//         quickSort(arr, 0, arr.length - 1);
        radixSort(arr);
        System.out.println(Arrays.toString(arr));

    }


    public static void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j + 1] < nums[j]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }

            }
        }

    }

    public static void radixSort(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        max += 1;

        int[][] bucket = new int[10][nums.length];
        int[] bucketCount = new int[10];

        for (int i = 0,n = 1; i < max; i++,n*=10) {
            int temp = 0;
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j]/n%10;
                bucket[temp][bucketCount[temp]] = nums[j];
                bucketCount[temp]++;
            }
            int index = 0;
            for (int k = 0; k < bucketCount.length; k++) {
                if(bucketCount[k] != 0 ){
                    for (int m = 0; m < bucketCount[k]; m++) {
                        nums[index] = bucket[k][m];
                        index ++;
                    }
                }
                bucketCount[k] = 0;
            }

        }
    }


    public static void quickSort(int[] nums, int left, int right) {
        int a = left;
        int b = right;
        int midVal = nums[(a + b) / 2];

        while (a < b) {
            while (nums[a] < midVal) {
                a++;
            }
            while (nums[b] > midVal) {
                b--;
            }

            if (a >= b) {
                break;
            }
            Utils.swap(nums, a, b);
            if (nums[a] == midVal) {
                b--;
            }
            if (nums[b] == midVal) {
                a++;
            }
        }
        if (a == b) {
            a++;
            b--;
        }
        if (a < right) {
            quickSort(nums, a, right);
        }
        if (b > left) {
            quickSort(nums, left, b);
        }

    }
}
