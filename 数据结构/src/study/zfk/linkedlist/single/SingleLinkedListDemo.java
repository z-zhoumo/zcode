package study.zfk.linkedlist.single;

import java.util.Stack;

/**
 * @author zfk
 * @create 2021-08-16 1:48
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试
        SingleLinkedList s = new SingleLinkedList();
        s.addByOrder(new HeroNode(1, "盖伦", "德玛西亚之力"));
        s.addByOrder(new HeroNode(3, "贾科斯", "武器大师"));
        s.addByOrder(new HeroNode(5, "易大师", "无极剑圣"));
        s.addByOrder(new HeroNode(7, "悠米", "魔法猫咪"));
        s.addByOrder(new HeroNode(2, "卡兹克", "螳螂"));
        s.addByOrder(new HeroNode(4, "雷恩加尔", "狮子狗"));
//        s.addByOrder(new HeroNode(4,"雷恩加尔","狮子狗"));
//        s.delete(7);
//
//        s.list();
//        System.out.println("有效节点个数"+getLength(s.getHead()));
//        //查找倒数链表为
//        System.out.println(findLastNode(s.getHead(), 5));
//        System.out.println(findLastNode(s.getHead(), 1));
//        System.out.println(findLastNode(s.getHead(), 7));
//        System.out.println(findLastNode(s.getHead(), 0));

        //测试反转链表
//        System.out.println("原链表");
//        s.list();
//        System.out.println("新链表");
//        reverseList(s.getHead());
//        s.list();
        //测试反转打印
//        s.list();   //old
//        System.out.println("^^^old******new:");
//        reversePrint(s.getHead());//new

        //Test 合并有序单链表

    }

    /**
     * 面试题，求单链表中节点的个数
     * 获取到单链表的节点的个数（如果是带头节点的链表，需求不统计头节点
     */
    public static int getLength(HeroNode head) {
        if (head.next == null)
            return 0;

        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length++;
    }


    /**
     * 查找单链表中倒数第k个节点
     * 暴力：遍历链表，获取链表总数len，要找的节点就是len+1-k x x 错误，链表里面的数不是123可能是1246
     */
    public static HeroNode findLastNode(HeroNode head, int index) {
        if (head.next == null) return null;
        int length = getLength(head);

        //校验index的值
        if (index <= 0 || index > length) return null;

        HeroNode temp = head.next;
        boolean flag = false;

        for (int i = 0; i < length - index; i++) {
            temp = temp.next;

        }
        return temp;

    }

    /**
     * 单链表的反转
     */
    public static void reverseList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转
        if (head.next == null || head.next.next == null) return;

        //定义一个辅助指针变量，帮助返回原来的链表
        HeroNode cur = head.next;
        HeroNode temp = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
        // 遍历原来的链表，每遍历一个节点，就将其取出，并放在reverseHead的最前端
        while (cur != null) {
            temp = cur.next;//保存当前节点的下一个节点
            cur.next = reverseHead.next;//让cur的下一节点指向反转链表的
            reverseHead.next = cur;//将cur的值连接到新的链表上
            cur = temp;//让cur后移
        }
        //将head.next指向reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;
    }


    /*
     *   逆序打印单链表
     *   使用栈的数据结构
     * */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) return;

        HeroNode cur = head.next;
        Stack<HeroNode> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (!stack.isEmpty()) {
            System.out.println("******" + stack.pop());
        }
    }


    /*
     * 合并两个有序的单链表，合并后任然有序
     * */
//    public static SingleLinkedList zhangsanJoinTwoLinkList(SingleLinkedList listOne,
//                                                   SingleLinkedList listTwo) {
//        if (listOne == null) {
//        }//前置条件..
//
//        HeroNode cur = listTwo.getHead().next;
//        while (cur!= null){
//            listOne.addByOrder(cur);
//            cur = cur.next;
//        }
//        return listOne;
//
//    }


}

