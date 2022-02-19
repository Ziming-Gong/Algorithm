package com.zim.linkedlist;

import java.util.List;
//https://leetcode-cn.com/problems/sum-lists-lcci/submissions/
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode n5 = new ListNode(5);
        ListNode n4 = new ListNode(5,n5);
        ListNode n3 = new ListNode(5,n4);
        ListNode n2 = new ListNode(5,n3);
        ListNode n1 = new ListNode(5,n2);
        ListNode head1 = n1;
        ListNode n51 = new ListNode(1);
        ListNode n41 = new ListNode(2,n51);
        ListNode n31 = new ListNode(3,n41);
        ListNode n21 = new ListNode(4,n31);
        ListNode n11 = new ListNode(5,n21);
        ListNode head2 = n11;

        ListNode head = addTwoNumbers(head1, head2);
        System.out.println();
    }
    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if ( l1 == null || l2 == null){
            return l1 == null? l2 : l1;
        }
        ListNode s = calLength(l1) < calLength(l2) ? l1 : l2;
        ListNode l = s == l1? l2 : l1;
        ListNode ans = l;
        ListNode fakeL = l;
        int carry = 0;
        while (s != null){
            int sum = s.val+l.val+carry;
            l.val = sum%10;
            carry = sum/10;
            s = s.next;
            l = l.next;
            if(fakeL.next!= null){
                fakeL = fakeL.next;
            }
        }

        while( l != null){
            int sum = l.val + carry;
            l.val = sum %10;
            carry = sum/10;
            l = l.next;
            if(fakeL.next != null){
                fakeL = fakeL.next;
            }
        }
        if(carry != 0){
            ListNode n = new ListNode(carry);
            fakeL.next = n;
        }
        return ans;


    }

    public static int calLength(ListNode l){
        int count = 0;
        while( l != null){
            count++;
            l = l.next;
        }
        return count;
    }
}
