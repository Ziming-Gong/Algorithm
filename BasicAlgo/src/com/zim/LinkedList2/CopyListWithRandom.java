package com.zim.LinkedList2;

import java.util.HashMap;
// 测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/


public class CopyListWithRandom {
    public static class Node{
        public int val;
        public Node next;
        public Node random;
        public Node(int val){
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public static Node copyRandomList1( Node head ){
        Node cur = head;
        HashMap<Node, Node> map = new HashMap<>();
        while( cur != null){
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while( cur != null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
    public static Node copyRandomList2(Node head) {
        if(head == null){
            return head;
        }
        Node cur = head;
        Node next = null;
        while( cur != null){
            next = cur.next;
            Node node = new Node(cur.val);
            cur.next = node;
            node.next = next;
            cur = next;
        }

        cur = head;
        Node newCur = null;
        while( cur != null){
            newCur = cur.next;
            newCur.random = cur.random == null ? null : cur.random.next;
            cur = newCur.next;
        }

        cur = head;
        Node newHead = head.next;
        // while(cur != null){
        //     next = cur.next.next;
        //     newCur = cur.next;
        //     cur.next = next;
        //     newCur.next = next!=null? next.next : null;
        //     cur = next;
        // }
        newCur = head.next;
        while( newCur != null){
            cur.next = newCur.next == null ? null: newCur.next;
            cur = cur.next;
            newCur.next = cur == null? null : cur.next;
            newCur = newCur.next;
        }
        return newHead;
    }
}
