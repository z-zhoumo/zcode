package study.zfk.tree.thrededbinarytree;

public class ThreadedBinaryTreeDemo{
    public static void main(String[] args) {
        //测试中序 遍历
        HeroNode root = new HeroNode(1, "Tom1");
        HeroNode node2 = new HeroNode(3, "Tom2");
        HeroNode node3 = new HeroNode(6, "Tom3");
        HeroNode node4= new HeroNode(8, "Tom4");
        HeroNode node5 = new HeroNode(10, "Tom5");
        HeroNode node6 = new HeroNode(14, "Tom6");

        //人工造树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        /*                         1
        *                   3             6
        *                8     10      14
        *
        *       虚         3   3  1    1 6
        * */

        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(root);
        tree.threadedNodes();

        //以10号结点为测试结点
        HeroNode left = node5.getLeft();
        HeroNode right = node5.getRight();
        System.out.println("10号结点的前驱"+left);//3
        System.out.println("10号结点的后继"+right);//1

        System.out.println("线索化方式遍历");
        tree.threadedList();
    }
}

/*
前序，中序，后序，啥时候输出自己！！！


* 线索化二叉树
* n个节点的二叉链表中有 n+1 个空指针域     2n-(n-1) = n+1
* 线索：利用二叉树的空指针域，存放指向该节点 在某种遍历次序下的前驱和后劲节点的指针
*       这种附加的指针叫线索
* 线索链表：加上了线索的二叉树。可分为前序，中序，后序线索二叉树
*
* 前驱节点：一个节点的前一个节点
* 后继节点：一个节点的后一个节点
* 
* */





//ThreadedBinaryTree，实现顺序二叉树的遍历
class ThreadedBinaryTree {
    private  HeroNode root;
    //为了实现线索化，需要创建要给指向当前节点的前驱节点的指针
    //在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre = null;

    public void setRoot( HeroNode root) {
        this.root = root;
    }

    public void threadedNodes(){
        threadedNodes(root);
    }

    //遍历线索化二叉树的方法
    public void threadedList(){
        //定义一个变量，存储当前结点，从root开始
        HeroNode node = root;
        while (node != null){
            //循环的找到leftType == 1结点，第一个找到的就是8结点
            //后面随着遍历而变化，因为当leftType == 1时，说明该结点时按照线索化
            //处理后的有效结点
            while (node.getLeftType() == 0){
                node = node.getLeft();
            }
            //打印当前结点
            System.out.println(node);
            //如果当前结点的右指针指向的是后继系欸但，就一直输出
            while(node.getRightType() == 1){
                //获取到当前结点的后继结点
                 node = node.getRight();
                System.out.println(node);
            }
            //替换遍历的结点
            node = node.getRight();
        }
    }

    //中序线索化  node就是当前需要线索化的结点
    public void threadedNodes(HeroNode node){
        //如果 node==null,不能线索化
        if(node == null){
            return;
        }

        //1 线索化左子树
        threadedNodes(node.getLeft());
        //2 线索化当前节点**********
        if(node.getLeft() == null){
            //让当前结点的左指针指向当前结点
            node.setLeft(pre);
            //修改当前结点的左指针的类型
            node.setLeftType(1);
        }
        //处理后继结点
        if(pre !=null && pre.getRight()==null){
            //让前驱节点的右指针指向当前结点
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;

        //3 线索化右子树
        threadedNodes(node.getRight());
    }

    //删除结点
    public void delNode(int no){
        if(root != null){
            //如果只有一个root结点，这里立即判断root是不是就是要删除的结点
            if(root.getNo() == no){
                root = null;
            }else {
                //递归删除
                root.delNodePlus(no);
            }
        }else{
            System.out.println("空树无法删除");
        }
    }



    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("当前二叉树为空");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("当前二叉树为空");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("当前二叉树为空");
        }
    }

    //前序查    ~~~~~~~~~
    public  HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    public  HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public  HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }


}






//先创建HeroNode结点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;


    // 1 如果leftType == 0 表示指向的是左子树 是1表示指向前驱节点
    // 1 如果rightType == 0 表示指向的是右子树 是1表示指向后继驱节点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  HeroNode getLeft() {
        return left;
    }

    public void setLeft( HeroNode left) {
        this.left = left;
    }

    public  HeroNode getRight() {
        return right;
    }

    public void setRight( HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }


    /*
     *  完成删除结点的操作
     * 规定：
     * 1 如果删除的结点是叶子结点，则删除该结点
     * 2 如果删除的结点是非叶子结点，则删除该子树
     *
     * 思路分析：
     * 1 树为空，只有一个root结点，则等价与将二叉树置空
     * 1 因为我们的二叉树是单向的，所以我们是判断当前结点的子节点是否需要删除结点，
     *   而不能去判断当前这个结点是不是需要删除结点
     * 2 如果当前结点的左子结点不为空，并且左子结点就是要删除的结点，
     *   将this.left = null并且就返回（结束递归删除）;
     * 3 如果当前结点的右子节点不为空，并且右子节点就是要删除的结点，就将
     *   this.right = null置空（结束递归）
     * 4 如果2 3都没有删除结点，我们需要向左子树递归删除
     * 5 如果左子树失败，则向右子树删除。
     *
     * */
    public void delNode(int no){

        //2 判断左子节点
        if(this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        //3 判断右子节点
        if(this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        //4 左子树递归
        if(this.left != null){
            this.left.delNode(no);
        }
        //5 右子树递归
        if(this.right != null){
            this.right.delNode(no);
        }


    }


    /*
     * 1 如果该非叶子结点a只有一个子节点b，则子节点代替结点A
     * 2 如果该非叶子结点a只有左结点B和右子节点C，则让左子节点
     *   B代替结点A
     *
     * */
    public void delNodePlus(int no){

        //2 判断左子节点
        if(this.left != null && this.left.no == no){
            if(this.left.left != null){
                this.left.left.right=this.left.right;
                this.left = this.left.left;
                return;
            }else if(this.left.right != null) {
                this.left = this.left.right;
                return;
            }
            this.left = null;
            return;
        }
        //3 判断右子节点
        if(this.right != null && this.right.no == no){
            if(this.right.left != null){
                this.right.left.right = this.right.right;
                this.right = this.right.left;
                return;
            }else if(this.right.right != null) {
                this.right = this.right.right;
                return;
            }
            this.right = null;
            return;
        }
        //4 左子树递归
        if(this.left != null){
            this.left.delNodePlus(no);
        }
        //5 右子树递归
        if(this.right != null){
            this.right.delNodePlus(no);
        }


    }


    //前序遍历
    public void preOrder() {
        System.out.println(this);//先输出父节点
        //递归左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }


    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }


    public  HeroNode preOrderSearch(int no) {
        System.out.println("preOrderSearch_____");
        //比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        //则判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
         HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {//左子树找到了,返回
            return resNode;
        }
        if (this.right != null) {// 右子树是否找到
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }


    public  HeroNode infixOrderSearch(int no) {
        // 判断当前结点的左子节点是否为空，不为空，递归中序
         HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("infixOrderSearch_____");
        //res为空，没有找到，和当前结点比较，
        if (this.no == no) {
            return this;
        }

        //否则继续进行右递归的中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    public  HeroNode postOrderSearch(int no) {
        //判断当前结点的左子节点是否为空，不为空，递归后序查找
         HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {//判断左子树是否找到
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {//判断又子树是否找到
            return resNode;
        }
        System.out.println("postOrderSearch_____");
        if (this.no == no) {//左右子树都没有找到，比较当前结点
            return this;
        }
        return resNode;
    }


}
