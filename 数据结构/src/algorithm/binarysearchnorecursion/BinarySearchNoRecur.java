package algorithm.binarysearchnorecursion;


/*
* 二分查找非递归
* */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1,3,8,11,67,100};
        int index = binarySearch(arr,6);
        System.out.println("index-->" + index);
    }


    /**
     * 二分查找的非递归实现
     *
     * @param arr   待查找的数组
     * @param target    需要查找的数组
     * @return  返回对应下标，-1表示没有找到
     */
    public static int binarySearch(int [] arr,int target){

        int left = 0;
        int right = arr.length-1;
        while (left < right){//继续查找
            int mid = (right + left)/2;
            if(arr[mid] == target){
                return mid;
            } else if(arr[mid] > target){
              right = mid - 1;//需要向左边查找
            }else{
                left = mid + 1;//需要向右边查找
            }
        }
        return -1;
    }
}
