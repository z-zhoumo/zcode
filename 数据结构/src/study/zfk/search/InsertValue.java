package study.zfk.search;

import java.util.Arrays;

/**
 * @create 09-21 9:58
 */
public class InsertValue {
    public static void main(String[] args) {
        int arr[]=new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i*6;
        }
        for (int i = 0; i < 100; i++) {
            if(arr[i]%5==0){
                arr[i]+=4;
            }
        }
        System.out.println(Arrays.toString(arr));
        int i = insertValueSearch(arr, 594, 0, arr.length - 1);
        System.out.println(i);

    }

    /*
    对于数据量比较大，关键字分布比较均匀的查找来说，采用插值查找比较快
    关键字分布不均匀，该方法不一定比二分好

    插值查找算法类似二分查找，不同的是插值查找每次从自适应mid处开始查找
    mid = start + (end + start)*(key - arr[start]) / arr[high] - arr[low])

     */
    public static int insertValueSearch(int []arr, int num,int start, int end){

        // 不判断可能导致 mid 越界
        // ||num < arr[0] || num > arr[arr.length-1]
        if(start>end ||num < arr[0] || num > arr[arr.length-1]){
            return -1;
        }
//        int mid = (start+end)/2;
        int mid = start +
                (end + start)*(num - arr[start]) /
                (arr[end] - arr[start]);
        System.out.println("中轴~~~~~"+mid);
        if(num > arr[mid]){
            return insertValueSearch(arr,num,mid+1,end);
        }else if(num <arr[mid]){
            return insertValueSearch(arr,num,start,mid-1);
        }else {
            return mid;
        }
    }
}
