package study.zfk.linkedlist.single;

/**
 * @author zfk
 * @create 2021-08-16 3:44
 */
//定义SingLeLikedList管理我们的英雄
class SingleLinkedList {
    //初始化头节点，头节点不动，不放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    public void add(HeroNode heroNode) {
        //头节点不能动，需要辅助变量
        HeroNode temp = head;

        //找到节点的最后节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //将最后这个节点的next 指向新节点
        temp.next = heroNode;
    }

    //第二种方式添加，根据排名将英雄插入到指定的位置
    //  如果有这个排名，添加失败，并给出提示
    //因为是单链表，我们要找的temp是位于添加位置前一个节点，否则加入失败
    public void addByOrder(HeroNode heroNode) {
        //头节点不能改
        HeroNode temp = head;
        boolean flag = false;

        while (true) {
            if (temp.next == null) {
                break;//链表找到最后
            }
            if (temp.next.no > heroNode.no) {
                break;//下一个节点的no大于需要添加节点的no
            } else if (heroNode.no == temp.next.no) {
                flag = true;
                break;//改no已经存在，无法添加
            }
            temp = temp.next;

        }

        if (flag) {
            System.out.printf("编号[%d]存在，添加失败\n", heroNode.no);
        } else {

            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //遍历链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(" " + temp);
            //temp后移
            temp = temp.next;
        }
    }

    //修改节点的信息，根据no编号修改，即no编号不能改
    public void update(HeroNode newHerNode) {
        if (head.next == null) {//判断是否为空
            System.out.println("链表为空=========");
            return;
        }

        HeroNode temp = head.next;
        boolean flag=false;
        while (true) {
            if (temp == null) {
               break;
            }
            if (temp.no == newHerNode.no) {
                flag=true;
                temp = newHerNode;
                return;
            }
            temp = temp.next;
        }

    }

    //删除节点信息
    public void delete(int no){

        HeroNode temp=head;
        boolean flag = false;
        while (true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.next=temp.next.next;
            System.out.println("删除成功");
        }else {
            System.out.println("【"+no+"】删除失败");
        }
    }


}
