package study.zfk.search;

import java.util.Arrays;

/**
 * @create 09-21 16:08
 * <p>
 * 0.618玄学数字
 */
public class GoldenCut {

    public static final int MAX_SIZE = 20;

    public static void main(String[] args) {

        int[] arr = {1, 8, 10, 89, 1000, 1024};
        System.out.println(FibonacciSearch(arr, 10));

    }


    /*
     * 后面 mid=low+F(k-1)-1,需要使用斐波那契数列，因此我们需要先获得一个
     * 斐波那契数列，非递归方法得到一个斐波那契数列
     * */
    public static int[] fib() {
        int f[] = new int[MAX_SIZE];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < f.length; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }


    /*
     * 斐波那契（黄金分割法）查找算法
     * 这个查找法的意义在于，二分查找向左递归，需要经过一次判断。
     * 向右递归需要两个判断
     * 为了弥补这种专项成本的不平衡，可以将mid向右调整多一些，而不是中心
     * 这就是斐波那契查找的意义
     * */
    public static int FibonacciSearch(int[] arr, int findVal) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0; //斐波那契分割数值的下标
        int mid = 0; //获取到斐波那契数列
        int f[] = fib(); //获取到斐波那契数列
        // 获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为 f[k] 值可能大于 a 的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向a[]
        //不足部分会使用0填充temp
        int[] temp = Arrays.copyOf(arr, f[k]);
        //{1,8,10,89,1000,1024} ==> {1,8,10,89,1000,1024,0,0,0,0....};
        //{1,8,10,89,1000,1024} ==> {1,8,10,89,1000,1024,1024,1024....};
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        //使用while循环处理，找到我们的数key
        while (low <= high) {
            //只要条件满足，可以得到
            mid = low + f[k - 1] - 1;
            if(findVal < temp[mid]) { //向数组前面继续查找（左边）
                high = mid -1;
                /*
                全部元素 = 前元素 + 后元素
                 f(k) = f(k-1)+f(k-2)
                 因为，前面有发f[k-1]个元素，
                 所以可以继续拆分f[k-1] = f[k-2]+f[k-3]
                 即在f[k-1] 的前面继续查找，k--
                 下次循环时 mid = f[k-1-1]-1
                */
                k--;
            }else if (findVal > temp[mid]){
                //向数组后面查找（右边）
                low = mid +1;
                /*
                全部元素 = 前元素 + 后元素
                 f(k) = f(k-1)+f(k-2)
                 因为，前面有发f[k-1]个元素，
                 所以可以继续拆分f[k-1] = f[k-2]+f[k-3]
                 即在f[k-1] 的后面继续查找，k-=2
                 下次循环时 mid = f[k-2-1]-1
                 即在f[k-2]的前面进行查找k-=2
                 即下次循环mid = f[k -1 - 2] - 1
                * */
                k-=2;
            }else{
                //需要确定返回的是哪个下标
                if(mid <= high){
                    return mid;
                }else{
                    return high;
                }
            }
        }
        return -1;

    }

}
