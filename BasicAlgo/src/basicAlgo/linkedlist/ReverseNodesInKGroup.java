package basicAlgo.linkedlist;

import java.util.List;
//https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
public class ReverseNodesInKGroup {
    public static class ListNode<V>{
        public V value;
        public ListNode<V> next;

        public ListNode(V value) {
            this.value = value;
            this.next = null;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if( head == null || head.next == null){
            return head;
        }
        ListNode start = head;
        ListNode end = findEndNode(start, k);
        head = findEndNode(head,k);
        ListNode nextStart = end.next;
        reverse( start, end);
        while( nextStart != null){
            ListNode cur = start.next;
            end = findEndNode(cur, k);
            if(end == null){
                return head;
            }
            nextStart = end.next;
            start.next = end;
            start = cur;
            reverse( start , end);
        }

        return head;




    }

    public static ListNode findEndNode(ListNode start, int k){
        while( --k != 0 && start != null){
            start = start.next;
        }
        return start;
    }
    public static void reverse( ListNode start, ListNode end){
        ListNode pre = null;
        ListNode next = null;
        ListNode cur = start;
        end = end.next;
        while( cur != end){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }
    public static void printLinkedList(ListNode n1){
        while( n1 != null){
            System.out.println(n1.value + ", ");
            n1 = n1.next;
        }
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = null;
        reverseKGroup(n1,2);
        printLinkedList(n1);

    }
}
