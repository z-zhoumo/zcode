package study.zfk.sort;

import java.util.Arrays;

/**
 * @create 09-19 8:38
 */
public class Shell {
    /*
     * 希尔排序
     * 对插入排序进行宏观调控
     * 1 采用交换法，
     * 2 移动法
     * */
    public static void shellSort(int[] arr) {
        System.out.println("原数组" + Arrays.toString(arr));

        for (int gap = arr.length/2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有的元素（共5组，每组两个元素，步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于步长元素，交换
                    if (arr[j] > arr[j + gap]) {
                        swap(arr, j, j + gap);
                    }
                }
            }
            System.out.printf("步长为%d排序后的数组", gap);
            System.out.println(Arrays.toString(arr));

        }


//        //将10个数据分为5组 8，9，1，7，2，3，5，4，6，0
//        //希尔排序第一轮 10/2
//        for (int i = 5; i < arr.length; i++) {
//            // 遍历各组中所有的元素（共5组，每组两个元素，步长5
//            for (int j = i-5; j >=0; j-=5) {
//                //如果当前元素大于步长元素，交换
//                if(arr[j] > arr[j+5]){
//                    swap(arr,j,j+5);
//                }
//            }
//
//        }
//        //希尔排序第二轮 5/2
//        for (int i = 2; i < arr.length; i++) {
//            // 遍历各组中所有的元素（共5组，每组两个元素，步长5
//            for (int j = i-2; j >=0; j-=2) {
//                //如果当前元素大于步长元素，交换
//                if(arr[j] > arr[j+2]){
//                    swap(arr,j,j+2);
//                }
//            }
//
//        }
//        //希尔排序第3轮 2/2
//        for (int i = 1; i < arr.length; i++) {
//            // 遍历各组中所有的元素（共5组，每组两个元素，步长5
//            for (int j = i-1; j >=0; j-=1) {
//                //如果当前元素大于步长元素，交换
//                if(arr[j] > arr[j+1]){
//                    swap(arr,j,j+1);
//                }
//            }
//
//        }

    }


    /*
    * 移位的希尔排序
    * */
    public static void shellSort2(int[] arr){
//        System.out.println("原数组" + Arrays.toString(arr));

        for (int gap = arr.length/2; gap > 0; gap /= 2) {
            //对gap个元素，逐个进行直接插入排序
            for (int i = gap; i <arr.length ; i++) {
                int j = i;
                int temp = arr[j];
                if(arr[j]<arr[j-gap]){
                    while (j - gap >=0&& temp < arr[j-gap]){
                        //移动
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }
                    //当退出while后，就给temp找到了插入的位置
                    arr[j] = temp;
                }
            }
            System.out.printf("步长为%d排序后的数组", gap);
//            System.out.println(Arrays.toString(arr));

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
}
