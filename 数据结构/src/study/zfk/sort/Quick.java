package study.zfk.sort;

/**
 * @create 09-19 9:53
 */
public class Quick {

    /*
    * 快速排序
    *
    * p1 < p2 基本条件
    * 1 两个while循环，找到对应值，交换
    * 2 判断 p1 >= p2，说明 左边全部小于midVal 右边全部大于midVal
    * 3 当交换后，发现 p1 指向的值是midVal时，需要将右边 p2 后移
    *       否则，右边判断时，p2指向的值一直是左边交换而来的值，导致一直重复判断
    * 4 当 p1==p2 时，需要p1++ p2--，防止栈溢出
    *       否者无限交换，死龟
    * 5 判断向左递归还是向右递归
    *       start < p2 则向左递归，同理
    *
    * */
    public static void quickSort(int[] arr,int start, int end){
        int p1 = start; //左索引
        int p2 = end; //右索引
        int midVal = arr[(start+end)/2]; //中间值


        //while循环的目的，让比mid值小的放到左边，大的右边
        while (p1 < p2){
            //在midVal左边一直找，找到大于midVal的值
            while (midVal>arr[p1]){
                p1++;
            }
            //在midVal右边一直找，找到小于midVal的值
            while (midVal<arr[p2]){
                p2--;
            }
            //如果 p1 >= p2 说明midVal左右两边的值，已经全部是
            // 左边小于等于midVal，有边大于midVal
            if(p1 >= p2){
               break;
            }
            // 交换两个位置的值
            swap(arr,p1,p2);
            //当交换完成后，发现arr[p1] == midVal，前移
            if(arr[p1] == midVal){
                p2--;
            }
            //当交换完成后，发现arr[p2] == midVal，前移
            if(arr[p2] == midVal){
                p1++;
            }
        }
        // 如果 p1==p2 ,必须p1++,p2--,否则栈溢出
        if (p1 == p2) {
            p1++;
            p2--;
        }

        //向左递归
        if(start < p2){
            quickSort(arr,start,p2);
        }
        //向右递归
        if(end > p1){
            quickSort(arr,p1,end);
        }

//        System.out.println("数组排序" + Arrays.toString(arr));


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
