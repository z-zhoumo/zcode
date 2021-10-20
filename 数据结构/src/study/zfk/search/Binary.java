package study.zfk.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @create 09-21 9:40
 */
public class Binary {
    public static void main(String[] args) {

//        int arr[] = {-1,0,0,0,0,0,1,1,1,1,2,3,4,5,6,7};
        int arr[] = {1,2,2,2,2,2,5,7};
        int[] res =  Binary.binarySearchPlus(arr,2,0,arr.length-1);
        System.out.print("该元素的数组下标为："+ Arrays.toString(res));

//        List<Integer> list = binarySearchPlusTeacher(arr, 0, 0, arr.length - 1);
//        list.forEach(i -> {
//            System.out.print(i +"  ");
//        });


    }
    /*
     * 二分查找
     * 数组有序要求
     * 结束递归的时机
     * 递归完整个数组，任然没有找到，也需要结束递归，当
     * */
    public static int binarySearch(int []arr, int num,int start, int end){
        if(start>end){
            return -1;
        }
        int mid = (start+end)/2;

        if(num > arr[mid]){
            return binarySearch(arr,num,mid+1,end);
        }else if(num <arr[mid]){
            return binarySearch(arr,num,start,mid-1);
        }else {
            return mid;
        }
    }


    /*
     * 二分查找  plus
     * 查找所有相同的
     *
     * */
    public static int[] binarySearchPlus(int []arr, int num,int start, int end){
        if(start>end){
            return null;
        }
        int mid = (start+end)/2;
        int res[] = new int [arr.length];
        int count = 0;

        if(num > arr[mid]){
            return binarySearchPlus(arr,num,mid+1,end);
        }else if(num <arr[mid]){
            return binarySearchPlus(arr,num,start,mid-1);
        }else {
            for (int i = start; i <= end ; i++) {
                if(num == arr[i]){
                    res[count++] = i;
                }
            }

        }
        return res;
    }


    /*
     * 二分查找  plus
     * 查找所有相同的
     *
     * */
    public static List<Integer> binarySearchPlusTeacher(int []arr, int num,int start, int end){
        if(start>end){
            return null;
        }
        int mid = (start+end)/2;
        int res[] = new int [arr.length];
        int count = 0;

        if(num > arr[mid]){
            return binarySearchPlusTeacher(arr,num,mid+1,end);
        }else if(num <arr[mid]){
            return binarySearchPlusTeacher(arr,num,start,mid-1);
        }else {
          /*思路分析
          * 1 在找到mid索引值，不要马上返回
          * 2 向mid索引值的左边扫描，将所有满足1000 ，的元素的下标，加入ArrayList
          * 3 向mid索引值的右边扫描，将所有满足1000，的元素下标加入集合
          * 4 返回集合
          * */
            List<Integer> resIndexList = new ArrayList<>();
            int temp = mid -1;
            while (true){
                if(temp < 0 || arr[temp] != num){
                    break;
                }
                //否则，就将temp 放入到list
                resIndexList.add(temp);
                temp--;
            }
            resIndexList.add(mid);

             temp = mid +1;
            while (true){
                if(temp > arr.length-1 || arr[temp] != num){
                    break;
                }
                //否则，就将temp 放入到list
                resIndexList.add(temp);
                temp++;
            }
           return resIndexList;
        }
    }

}
