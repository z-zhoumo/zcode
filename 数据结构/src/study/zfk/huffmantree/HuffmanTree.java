package study.zfk.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 *
 * 路径，在一颗树中，从一个节点往下可以达到的孩子或孙子结点之间的通路
 * 路径长度：通路中分支的数组称为路劲长度。若规定根节点的层数为1，
 *          则到第 L 层结点的路劲长度为 L-1
 * 权：将树中结点赋给一个有着某种含义的数值，则这个数值为该结点的权
 * 带权路径长度：从根节点到该结点之间的路劲长度与该结点的权的乘积
 *
 *
 * 构建赫夫曼树的步骤 huffman
 * 1 从小到大进行排序，将每一个数据，每个数据都是一个节点，
 *   每个节点可以看出是一个最简单的二叉树
 * 2 取出跟节点权值最小的两颗二叉树
 * 3 组成一个新的二叉树，该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
 * 4 再将这颗新的二叉树，以根节点的权值大小再次排序，不断重复 1234 步骤
 *   直到数列中，所有的数据都被处理，就得到一个赫夫曼树
 *
 * 哈夫曼树的定义
    带权路径长度（WPL）
    Weighted Path Length

 *
 *
 * */

/*
*  功能： 根据huffman编码压缩数据原理
*   i like like like java do you like a java
* 1 Node{data(存放数据），weight（权值），left和right
* 2 得到对应的数组
* 3 编写方法，将准备构建huffman树的Node结点放到list，
*   [Node{data = 97,weight =5},Node[],data = 32,weight = 9...]
*   体现
* 4 可以通过List 创建对应的huffman树
*
* */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node result = createHuffmanTree(arr);
       preOrder(result); //前序遍历
        //67  29  38  15  7  8  23  10  4  1  3  6  13
       /*
       huffman树
                   67

          29                   38
                        15               23
                     7      8         10    13
                                    4    6
                                  1   3
       */
    }


    //创建huffman树的方法
    public static Node createHuffmanTree(int[] arr) {
        //第一步
        //1 遍历 arr 数组
        //2 将 arr 的每个元素构成一个Node
        //3 将 Node 放入ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {

            //排序 从小到大
            Collections.sort(nodes);//排序
            //第二步
            // 取出根节点权值最小的两颗二叉树
            //1 取出权值最小的节点（二叉树）
            Node leftNode = nodes.get(0);
            //2 取出权值第二小的节点（二叉树）
            Node rightNode = nodes.get(1);
            //3 构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //4 删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5 将parent加入导nodes
            nodes.add(parent);
//            Collections.sort(nodes);

        }
        return nodes.get(0);


    }

    //前序遍历的方法
    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("是空树，无法遍历");
        }
    }


}


//创建节点类
class Node implements Comparable<Node> {




    int value;//节点权值
    Node left;//左子节点
    Node right;//右子节点

    public Node(int value) {
        this.value = value;
    }

    //前序遍历
    public void preOrder(){
        System.out.print(this.value+"  ");

        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
