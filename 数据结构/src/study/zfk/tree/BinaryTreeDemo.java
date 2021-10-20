package study.zfk.tree;

/*
树
    数组存储方式分析
    优点：通过下标的方式访问元素，速度快。对于有序数组，还可以使用二分查找提高检索速度
    缺点：如果要检索具体某个值，或者插入（按一定顺序）会整体移动，效率低

    链式存储方式分析
    优点：在一定程度上对数组存储方式有优化（添加，删除效率提高）
    缺点：进行检索时，效率任然比较低





 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //说明，手动创建二叉树，后面改递归创建
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        //test
        /*              1
                    2       3
                  *   *   5   4

                  递归查找：比较次数 4  3  2


        * */
//        System.out.println("前序遍历");//12354
//        binaryTree.preOrder();
//        System.out.println("中序遍历");// 21534
//        binaryTree.infixOrder();
//        System.out.println("后序遍历");// 25431
//        binaryTree.postOrder();

//        HeroNode resNode = binaryTree.preOrderSearch(5);
//        HeroNode resNode = binaryTree.infixOrderSearch (5);
//        HeroNode resNode = binaryTree.postOrderSearch(2);

//        if (resNode != null) {
//            System.out.println("找到了" + resNode);
//        } else {
//            System.out.println("找不到");
//        }

//        System.out.println("测试删除***************************");
//        binaryTree.delNode(3);//3子树整体被删除，即白遍历结果为1 2
//        binaryTree.preOrder();
//
        System.out.println("测试删除plus");
        binaryTree.delNode(3);// 1254
        binaryTree.preOrder();

    }
}


//定义一个二叉树 BinaryTree
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int no) {
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

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
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


    public HeroNode preOrderSearch(int no) {
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


    public HeroNode infixOrderSearch(int no) {
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

    public HeroNode postOrderSearch(int no) {
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
