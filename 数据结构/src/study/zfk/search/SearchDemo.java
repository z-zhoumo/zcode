package study.zfk.search;

import java.util.Arrays;

/**
 * @create 09-20 11:26
 */
public class SearchDemo {
    public static void main(String[] args) {
//        int arr[] = {2,3,4,5,6,8,1,7,11};
//        int i = SeqSearch(arr, 3);

        int arr[] = {-1,0,0,0,0,0,1,1,1,1,2,3,4,5,6,7};
//        int arr[] = {0,0,0,0,0,0,0,0,0,0,0,0,0};
       int[] res =  Binary.binarySearchPlus(arr,0,0,arr.length-1);
        System.out.print("该元素的数组下标为："+ Arrays.toString(res));


    }



    /*
    * 线性查找
    * */
    public static int SeqSearch(int [] arr,int num){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==num){
                return i;
            }
        }
        return -1;
    }




}
