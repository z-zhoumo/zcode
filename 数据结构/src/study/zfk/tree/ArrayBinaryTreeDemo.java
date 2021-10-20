package study.zfk.tree;

/**
 *
 *
 顺序存储二叉树的特点
 1） 顺序二叉树通常只考虑完全二叉树
 2） 第n个元素的左子节点为 2*n+1
 3） 第n个元素的右子节点为 2*n+2
 4） 第n个元素的父节点为  （n-1)/2
 其中 n 表示二叉树中第几个元素

          0                                          1
    1               2                            2        3
  3      4        5      6                    4    5    6     7
7  8   9  10    11  12 13 14

 0 1 3 7 8 4 9 10 2 5 11 12 6 13 14
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int []arr ={ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        //创建ArrayBinaryTree
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();// 0 1 3 7 8 4 9 10 2 5 11 12 6 13 14
    }
}



//ArrayBinaryTree，实现顺序二叉树的遍历

class ArrayBinaryTree{
    private int [] arr;

    public ArrayBinaryTree(int [] arr){
        this.arr = arr;
    }
    //编写一个一个方法，完成顺序存储二叉树的前序遍历

    /**
     *
     * @param index 数组的下标
     */
    public void preOrder(int index){
        //数组为空
        if(arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前这个元素，前序遍历
        System.out.print(arr[index]+" ");
        //向左递归遍历,优先判断数组角标是否越界
        if((index * 2 +1) < arr.length){
            preOrder(2 * index +1);
        }
        //向右递归遍历
        if((index *2 +2 )< arr.length){
            preOrder(2 * index +2);
        }

    }

    public void preOrder(){
        preOrder(0);
    }
}











