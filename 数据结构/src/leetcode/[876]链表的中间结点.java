package leetcode;//给定一个头结点为 head 的非空单链表，返回链表的中间结点。
//
// 如果有两个中间结点，则返回第二个中间结点。 
//
// 
//
// 示例 1： 
//
// 
//输入：[1,2,3,4,5]
//输出：此列表中的结点 3 (序列化形式：[3,4,5])
//返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
//注意，我们返回了一个 ListNode 类型的对象 ans，这样：
//ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = 
//NULL.
// 
//
// 示例 2： 
//
// 
//输入：[1,2,3,4,5,6]
//输出：此列表中的结点 4 (序列化形式：[4,5,6])
//由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
// 
//
// 
//
// 提示： 
//
// 
// 给定链表的结点数介于 1 和 100 之间。 
// 
// Related Topics 链表 双指针 👍 478 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
///
//   Definition for singly-linked list.
//   public class ListNode {
//       int val;
//       ListNode next;
//       ListNode() {}
//       ListNode(int val) { this.val = val; }
//       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//   }
//  /

import study.zfk.tree.HeapSortDemo;

import java.util.HashMap;
import java.util.List;

class Solution876 {
    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode temp = head;
        for (int i = 1; i <= 6; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        middleNode(head);
    }

    public static ListNode middleNode(ListNode head) {
        int len = 0;
        ListNode temp = head;

        while(temp.next != null ){
            len ++;
            temp = temp.next;
        }
        temp = head;
       if(len%2 ==0){
           len = len/2 + 1;
       }else {
           len = len/2;
       }
        while(len > 0){
            len --;
            temp = temp.next;
        }
        return temp;

    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
