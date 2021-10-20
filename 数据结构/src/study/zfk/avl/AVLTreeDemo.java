package study.zfk.avl;




/*

       4                           6
   3       6                    4      7
         5   7     左旋转=》   3    5      8
                8



            10                          8
         8      12    右旋转=》        7    10
       7   9                       6     9   12
     6



????????????????????????????? xxxxxxxxx

 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[ ] arr = {4,3,6,5,7,8};
        int[] arr = {6,7,8,9,10,12};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("**中序遍历****************");
        avlTree.infixOrder();

        System.out.println("平衡处理~~");
        System.out.println(avlTree.getRoot().height()+"目前高度");
        System.out.println("左子树高" + avlTree.getRoot().leftHeight());
        System.out.println("右子树高度" + avlTree.getRoot().rightHeight());
    }
}

//创建AVLTree
class AVLTree{
    private  Node root;

    public Node getRoot() {
        return root;
    }

    //查找要删除的结点
    public  Node search(int value ){
        if(root == null){
            return null;
        }else {
            return root.search(value);
        }
    }

    //查找父结点
    public  Node searchParent(int value){
        if(root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    /**
     *
     * @param node 传入的结点（当做二叉排序树的根结点
     * @return  返回的以 node 为根系欸但的二叉排序树的最小结点的值
     */
    public int delRightTreeMin( Node node){
         Node target = node;
        //循环查找左结点，找到最小值
        while (target.left != null){
            target = target.left;
        }
        //这时 target 就指向最小结点
        //删除最小结点
        delNode(target.value);
        return target.value;
    }

    //删除结点
    public void delNode(int value){
        if (root == null) {
            return;
        }else{
            // 1 找到要删除的结点 targetNode
             Node targetNode = search(value);
            //没有找到要删除的结点
            if(targetNode == null){
                return;
            }
            //如果我们发现 当前二叉排序树只有一个结点
            if(root.left == null && root.right == null ){
                root = null;
                return;
            }

            //查找 targetNode 的父结点
             Node parent = searchParent(value);
            //如果要删除的结点是叶子结点
            if(targetNode.left == null&&
                    targetNode.right == null){
                //判断targetNode 是父节点的左子结点，还是右子节点
                if(parent.left != null&&
                        parent.left.value == value){
                    parent.left = null;
                }else if(parent.right != null &&
                        parent.right.value == value){
                    parent.right = null;
                }
            }else if(targetNode.left != null && targetNode.right != null){
                //删除有两颗子树的结点

            }else{//删除只有一颗子树的结点
                //如果要删除的结点有左子结点
                if(targetNode.left != null){
                    //如果 targetNode 是 parent 的左子结点
                    if(parent.left.value == value){
                        parent.left = targetNode.left;
                    }else {
                        //targetNode 是 parent的右子结点
                        parent.right = targetNode.left;
                    }
                }else {  //如果 targetNode 是 parent 的右子结点
                    //如果 targetNode 是 parent 的左子结点
                    if(parent.left.value == value){
                        parent.left = targetNode.right;
                    }else {
                        //targetNode 是 parent的右子结点
                        parent.right = targetNode.right;
                    }
                }
            }

        }
    }

    //添加结点的方法
    public void add( Node node){
        if(root == null){
            root = node;
        }else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder(){
        if(root == null){
            return;
        }else {
            root.infixOrder();
        }
    }
}

//创建 Node 结点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //返回以该结点为根结点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height()+1,
                right == null ? 0 : right.height()) + 1;

    }

    //添加结点
    //递归形式添加结点，注意需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //判断传入结点的值，和当前子树的结点的值关系
        if (node.value < this.value) {
            //当前结点的左子节点为空
            if (this.left == null) {
                this.left = node;
            } else {
                //递归的向左子树添加
                this.left.add(node);
            }
        } else {//添加结点的值大于当前结点的值
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        //当添加完一个结点后，如果
        // height(右子树） - height（左子树） > 1,左旋转
        if(rightHeight() - leftHeight() > 1){
           leftRotate();//左旋转
        }
        // height(左子树） - height（右子树） > 1,左旋转
        if(leftHeight() - rightHeight() > 1){
            rightRotate();//左旋转
        }

    }

    //左边保持不动，改变右边排列
    //左旋转的方法
    private void leftRotate(){
        //创建新的结点，以当前根结点的值
        Node newNode = new Node(value);
        //把新的结点的左子树设置成当前结点的左子树
        newNode.left = left;
        //把新的结点的右子树设置成当前的右子树的左子树
        newNode.right = right.left;
        //把当前结点的值替换成右子结点的值
        value = right.value;
        //把当前结点的右子树设置成当前结点右子树的右子树
        right = right.right;
        //把当前结点的左子树（左子结点）设置成新的结点
        left = newNode;
    }


    //右旋转
    private void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }



        /*二叉树的删除
                                      7
                                 3        10
                              1     5   9    12
                               2

            思路：
            情况一：    删除叶子结点
            1 找到要删除的结点 targetNode
            2 找到 targetNode 的父节点 parent
                若没有父节点
            3 确定targetNode是parent的左子节点还是右子节点
            4 根据前面的情况来对应删除

            parent.left = null
            parent.right = null

            情况二：    删除只有一颗子树的的结点
            1 需求先去找到要删除的结点 targetNode
            2 找到 targetNode 得父节点  parent
            3 确定 targetNode 的 子 节点是左还是右
            4 targetNode 是 parent的 左还是右
                4.1 targetNode 是 parent 的左子节点
                    parent.left = targetNode.left;
                4.2 targetNode的子节点是右子节点
                    parent.right = targetNode.left;
            5 如果targetNode 是 parent 的左子结点
               parent.left = targetNode.right;
              如果 targetNode 是 parent 的右子结点
              target.right = targetNode.right


             情况三      删除有两颗子树的结点（比如：7，3，10）
             1 需求先去找到要删除的结点 targetNode
             2 找到targetNode 的父节点 parent
             3 从targetNode 的右子树找到最小的结点
             4 用一个临时变量，将最小结点的值保存 temp = 12
             5 删除该最小结点
             6 targetNode.value = temp
        * */

    //查找要删除的结点

    /**
     * @param value 希望删除的饿结点的值
     * @return 如果找到返回该结点，否则返回 null
     */
    public Node search(int value) {
        if (value == this.value) {//找到就是该结点
            return this;
        } else if (value < this.value) {//如果查找的值小于当前结点，向左子树递归查找
            if (this.left != null) {//左子节点为空
                return null;
            }
            return this.left.search(value);
        } else {//如果查找的值不小于当前结点，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);

        }
    }

    //查找要删除结点的父节点

    /**
     * @param value 要找到的结点的值
     * @return 返回的是要删除的结点的父结点，如果没有返回null
     */
    public Node searchParent(int value) {
        //如果当前结点就是要删除的结点的父节点，就返回
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前结点的值，并且当前结点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);//向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);//向右子树递归查找
            } else {
                return null;
            }
        }

    }


    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


}











