package study.zfk.sort;

/**
 * @create 09-19 14:06
 */
public class Merge {


     /*
     * 归并排序
     * */

     //分加和的方法
     public static void mergeSort(int[] arr, int left, int right ,int[] temp){
//          System.out.println("/(ㄒoㄒ)/~~");
          if(left < right){
               int mid = (left+right)/2;//中间的索引
               //向左递归进行分解
               mergeSort(arr,left,mid,temp);
               //向右递归进行分解
               mergeSort(arr,mid+1,right,temp);
               //到合并
               merge(arr,left,mid,right,temp);
          }
     }



     /**
      *   合并的方法
      * @param arr    排序的原始数组
      * @param left   左边有序数列的初始索引
      * @param mid     中间索引
      * @param right   右边索引
      * @param temp      中转数组
      */
     public static void merge(int[] arr, int left,int mid,int right ,int [] temp){
          int i = left;//初始化i，左边有序序列的初始索引
          int j = mid +1 ;//初始化j，右边有序序列的开始索引
          int t = 0; //temp数组的当前索引

          //  1
          //先把左右两边的数据按照规则，填充到temp数组
          //直到左右两边的有序序列，有一边处理完成为止
          while (i <= mid && j<=right){
               //如果左边的有序列的当前元素，小于等于右边有序序列的当前元素
               //即将左边的当前元素，copy到temp数组
               if(arr[i]<=arr[j]){
                    temp[t] = arr[i];
                    t++;//临时数组下标后移
                    i++;//左边数组后移
               }else {
                    temp[t] = arr[j];
                    t++;//临时数组下标后移
                    j++;//右边数组后移
               }
          }

          //  2
          // 把有剩余数据的一边依次全部填充到temp中
          while (i <= mid){
               //左边的有序徐磊还有剩余的元素，就全部填充到temp中去
               temp[t] = arr[i];
               i++;
               t++;
          }
          while (j <= right){
               //左边的有序徐磊还有剩余的元素，就全部填充到temp中去
               temp[t] = arr[j];
               j++;
               t++;
          }
          //   3
          // 将temp数组的元素拷贝到arr
          //并不是每次都拷贝所有
          t = 0;
          int tempLeft = left;//
          //第一次合并 tempLeft = 0，right = 1
          //tempLeft = 2  right = 3
          //tempLeft = 0   right = 3
          //tempLeft = 0   right = 7
          while (tempLeft <= right){
               arr[tempLeft] = temp[t];
               t++;
               tempLeft++;
          }

     }
}
