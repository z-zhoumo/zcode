package study.zfk.linkedlist.doubleLinkList;

/**
 * @create 09-13 7:56
 */
public class DoubleLinkListDemo {
    public static void main(String[] args) {
        DoubleLinkList s = new DoubleLinkList();
       HeroNode2 node1 = new HeroNode2(1, "盖伦", "德玛西亚之力");
       HeroNode2 node2 = new HeroNode2(3, "贾科斯", "武器大师");
       HeroNode2 node3 = new HeroNode2(4, "雷恩加尔", "狮子狗");
       HeroNode2 node4 = new HeroNode2(5, "易大师", "无极剑圣");
       HeroNode2 node5 = new HeroNode2(7, "悠米", "魔法猫咪");
       HeroNode2 node7 = new HeroNode2(2, "卡兹克", "螳螂");
//       s.add(node1);
//       s.add(node2);
//       s.add(node3);
//       s.add(node4);
//       s.add(node5);
//       s.add(node7);
        s.addOrder(node2);
        s.addOrder(node7);
        s.addOrder(node1);
        s.addOrder(node4);
        s.addOrder(node5);
        s.addOrder(node3);
       s.list();
        System.out.println("delete*****************");
        s.delete(7);
        s.list();
        System.out.println("update*****************");
        HeroNode2 node6 = new HeroNode2(1, "泰达米尔", "蛮族之王");
        s.update(node6);
        s.list();
    }

}

class DoubleLinkList{
    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }

    //添加节点到双向向链表
    public void add(HeroNode2 heroNode) {
        //头节点不能动，需要辅助变量
        HeroNode2 temp = head;

        //找到节点的最后节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //将最后这个节点的next 指向新节点
        temp.next = heroNode;
        heroNode.pre = temp;
    }


    //遍历链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
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
    public void update(HeroNode2 newHerNode) {
        if (head.next == null) {//判断是否为空
            System.out.println("链表为空=========");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHerNode.no) {
                flag=true;
               break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.name = newHerNode.name;
            temp.nickname = newHerNode.nickname;
            System.out.printf("修改**%d**成功\n",newHerNode.no);
            return;
        }else {
            System.out.printf("**%d**不存在\n",newHerNode.no);
        }

    }



    //删除节点信息
    public void delete(int no){

        HeroNode2 temp=head;
        boolean flag = false;
        while (true){
            if(temp==null){
                break;
            }
            if(temp.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
           temp.pre.next = temp.next;
           //预防空指针
           if(temp.next != null){
               temp.next.pre = temp.pre;
           }
            System.out.println("【"+no+"】删除成功");
        }else {
            System.out.println("【"+no+"】删除失败");
        }
    }


    //按照编号添加
    public void addOrder(HeroNode2 node){
        HeroNode2 temp = head;
        boolean flag = false;
        while (true){
            if(temp.next == null){
               add(node);
               break;
            }
          if(temp.next.no > node.no){
              temp.next.pre = node;
              node.next = temp.next;
              temp.next = node;
              node.pre = temp;
              break;
              //下一个节点的no大于自己的no就添加
          }else if (node.no == temp.next.no){
              System.out.printf("编号***%d***存在",node.no);
              break;
          }
          temp = temp.next;
        }
    }





}














class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了重写方便，重写toString
    @Override
    public String toString() {
        return "HeroNode222{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname +
                '}';
    }
}

