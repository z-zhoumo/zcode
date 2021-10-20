package study.zfk.zhangsanTest;

import java.util.Arrays;

/**
 * @create 09-18 16:49
 */
public class TestSort {
    public static void main(String[] args) {
        int a = (int) 0.5;
        int arr[] ={1,2,9,4,-7,4,5,8};
        System.out.println(Arrays.toString(arr));
        sort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }



    public static void sort(int[] arr, int left ,int right){
        int l = left;
        int r = right;
        int temp;
        int modVal = arr[(l+r)/2];

        while (l<r){
            while (modVal>arr[l]){
                l++;
            }
            while (modVal<arr[r]){
                r--;
            }
            if(l>=r){
                break;
            }
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;

            if(modVal == arr[r]){
                l++;
            }
            if(modVal == arr[l]){
                r--;
            }
        }
        //防止栈溢出
        if(r == l){
            r--;
            l++;
        }
        if(left < r){
            sort(arr,left,r);
        }
        if(right > l){
            sort(arr,l,right);
        }



    }





    public static void bubbleSort(int[] arr){
        if(arr == null && arr.length==0)return;

        int temp = 0;
        boolean flag = false;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if(arr[j]>arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if(!flag){
                System.out.println("不用继续排序");
                return;
            }else {
                flag = false;
            }

        }
    }
}
