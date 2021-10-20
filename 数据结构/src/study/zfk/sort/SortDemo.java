package study.zfk.sort;
import study.zfk.tree.HeapSortDemo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 如果排序对象是简单数据，则三种算法的差距不大但顺序仍然是插入>选择>冒泡；
 * 而如果排序对象是复杂数据结构，则选择要远快于插入，冒泡的耗时接近插入的3倍。
 */
public class SortDemo {
    public static final int ARR_LENGTH = 8000000;

    public static void main(String[] args) {

//        int arr[] = {3, 9, -1, 10, -2,7,8};
//        int arr[] ={-9,78,0,23,-567,70};
//        int arr[] = {1,2,3,4,5,6,7,8};
//          int arr[] = {8,9,1,7,2,3,5,4,6,0};
//          int arr[] = {8,4,5,7,1,3,6,2,8,0};
//          int arr[] = {53,3,542,748,14,214};


        int arr[] = new int[ARR_LENGTH];
        for (int i = 0; i < arr.length; i++) {
            //random（）是[0,1)所以产生随机数是[0,8000000)
            arr[i] =(int) (Math.random()*8000000);
        }


//        list(arr);//电脑叫起来了哈哈
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = simpleDateFormat.format(date1);

//        bubbleSort(arr);
        //排序前2021-09-18 16:42:10
        //排序后2021-09-18 16:42:21

//        selectSort(arr);
        //排序前2021-09-18 17:22:45
        //排序后2021-09-18 17:22:48

//        insertSort(arr);
        //ARR_LENGTH = 8 0000;
        //排序前2021-09-19 05:43:12
        //排序后2021-09-19 05:43:13

//        Shell.shellSort(arr);
        //排序前2021-09-19 09:37:04
        //排序后2021-09-19 09:37:12
//        Shell.shellSort2(arr);
        //排序前2021-09-19 09:48:35
        //排序后2021-09-19 09:48:35

//        Quick.quickSort(arr,0,arr.length-1);
        //ARR_LENGTH = 8000 0000;
        //排序前2021-09-19 15:09:06
        //排序后2021-09-19 15:09:19

//        int temp[] = new int[arr.length];
//        Merge.mergeSort(arr,0,arr.length-1,temp);
        //ARR_LENGTH = 8000 0000;
        //排序前2021-09-19 15:07:33
        //排序后2021-09-19 15:07:47

//        Radix.radixSort(arr);
        //ARR_LENGTH = 800 0000;
        // 排序前2021-09-20 11:05:16
        //排序后2021-09-20 11:05:17

        HeapSortDemo.heapSort(arr);
        // 800 0000
        //排序前2021-10-03 14:01:17
        //排序后2021-10-03 14:01:19



        Date date2 = new Date();
        String dateStr2 = simpleDateFormat.format(date2);
        System.out.println("\n排序前" + dateStr1 + "\n排序后" + dateStr2);


//        list(arr);


    }


    /**
     * 冒泡排序  时间复杂度  O(n^2)
     * 一共进行arr.length-1次循环
     * 每趟排序次数在逐渐减少
     * 如果我们发现在某次排序中，没有发生一次交换，可以提前解释冒泡排序
     */
    public static void bubbleSort(int[] arr) {
        System.out.print("原数组");
        System.out.println(Arrays.toString(arr));
        int count = 0; //统计交换次数，建议用boolean
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    count++; //交换次数增加
                    swap(arr, j, j + 1);
                }
            }
            if (count == 0) {
                System.out.println("不用后续排序，已经完成");
                return;
            }
            System.out.printf("产生%d次交换第 %d 次排序后的数组", count, i);
            System.out.println(Arrays.toString(arr));
            count = 0;//重置交换次数
        }
    }

    /*
     * 选择排序         时间复杂度     O(n^2)
     * 1 选择排序一共有arr.length-1轮排序
     * 2 每 1 轮排序，又是一个循环，循环的规则
     *   2.1 假定当前为最小数
     *   2.2 与每个数进行比较，如果发现有比当前数更小的数，重新确认最小数，得到下标
     *   2.3 当遍历到数组的最后时，就得到了本轮最小数和下标
     *   2.4 交换
     * */
    public static void selectSort(int[] arr) {
        System.out.println("原数组" + Arrays.toString(arr));
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            temp = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[temp] < arr[j]) {//修改 <  > 就可以排序
                    temp = j;
                }
            }
            swap(arr, i, temp);
            System.out.printf("交换第%d和第%d个的值", i + 1, temp + 1);
            System.out.println("得到数组" + Arrays.toString(arr));

        }
    }


    /*
    * 插入排序
    * 1 将数组分为两个部分，前一个部分时已经排序的，后一个为待排序的数组
    * insertVal是我们需要插入的值
    *2  insertIndex是已经拍好序的长度
    * 当insertVal的值大于arr[insertIndex],将insertVal的值赋给arr[insertIndex+1]
    * 当insertVal的值小于arr[insertIndex],用arr[insertIndex]的值覆盖arr[insertIndex+1]
    * 然后将insertIndex--，后移，然后重复上述操作，直到找到，arr[insertIndex]比insertVal小
    * 得到insertIndex，这时arr[insertIndex+1]==arr[insertIndex++1],将arr[insertIndex+1]覆盖，
    * */
    public static void insertSort(int [] arr){
        System.out.print("原数组");
        System.out.println(Arrays.toString(arr));
        int insertVal ;
        int insertIndex ;
        for (int i = 1; i < arr.length; i++) {
             insertVal = arr[i];//待插入的值
             insertIndex = i -1 ;//待插入数的前一个下标，已排序的长度
            while(insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];//用已排序最后的值，覆盖要排序的值
                insertIndex--; //循环覆盖，直到末尾
            }
            //已经找到插入位置，insertIndex+1
            arr[insertIndex+1] = insertVal;
            System.out.printf("第%d轮插入，",i );
            System.out.println("数组为" + Arrays.toString(arr));
        }

    }






    /*
     * 交换数组两个数的位置
     * */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
     * 遍历数组
     * */
    public static void list(int[] arr) {
        System.out.print("遍历数组===>");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }


}
