package study.zfk.tree;

import java.util.Arrays;


public class HeapSortDemo {
    public static void main(String[] args) {
        int arr[] = {4,6,8,5,9,78,-5,2,-99};
//        int arr[] = {4,6,8,5,9};


        heapSort(arr);
    }


    //堆排序的方法
    public static void heapSort(int arr[]){
        System.out.println("Heap排序");

        int temp = 0;
//        //分步
//        // 4 6 8 5 9
//        adjustHeap(arr,1,arr.length);
//        System.out.println("第一次"+ Arrays.toString(arr));// 4 9 8 5 6
//
//        adjustHeap(arr,0,arr.length);
//        System.out.println("第二次"+ Arrays.toString(arr));// 9 6 8 5 4

        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶推
        for (int i = arr.length/2-1;i >= 0;i--) {
            adjustHeap(arr,i,arr.length);
        }
        System.out.println("构建Heap完成"+Arrays.toString(arr));
        /*
        * 将堆顶元素与末尾元素交换，将最大元素 沉 到数组末端；
        * 重新调整结构，使其满足堆的定义，然后继续交换堆顶元素，反复
        * */
        for (int i = arr.length-1; i >0 ; i--) {
             //交换
            temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,i);
        }
        System.out.println("排序完成"+Arrays.toString(arr));


    }

    //将一个数组（二叉树），调整成一个大顶堆

    /**
     *                            0
     *                         1     2
     *                       3   4  5   6
     *             由图可知，索引 i 节点的 左 子节点是 2i+1
     *                                 右 子节点是 2i+2
     *
     *
     *
     * 功能：完成 将以 i 对应的非叶子结点的树调整成大顶堆
     * @param arr   待调整的数组
     * @param i     非叶子结点在数组中的索引
     * @param length    表示对多少个元素继续调整，length 在逐渐减少
     */
    public static void adjustHeap(int arr[],int i,int length){

        int temp = arr[i];//取出当前元素的值，保存在临时变量

        for (int k = i*2+1; k < length; k=2*k+1) {//向左子节点遍历
            if(k+1 < length && arr[k] < arr[k+1]){ // 当左子节点的值小于右子节点，
                k++;//k指向右子节点，让 arr[k] 的值是大的那一个
            }
            if(arr[k] > temp){//如果子节点大于父节点
                arr[i] = arr[k]; //将较大的值赋给当前节点
                i = k; //！！！ i指向 k，继续比较循环
            }else {
                break;
            }
        }
        //当 for 循环结束后，我们已经将以 i 为父节点的树的最大值，放在了最顶（局部）
        arr[i] = temp;//将temp值放到调整后的位置
    }











}
