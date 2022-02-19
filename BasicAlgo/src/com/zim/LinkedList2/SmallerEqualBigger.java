package com.zim.LinkedList2;

public class SmallerEqualBigger {
    public static class Node{
        public int val;
        public Node next;
        public Node(int val){
            this.val = val;
        }
    }

    public static Node listPartition1(Node head, int pivot){
        if(head == null){
            return head;
        }
        Node cur = head;
        int i = 0;
        while( cur != null){
            i++;
            cur = cur.next;
        }
        Node[] nodes = new Node[i];
        i = 0;
        cur = head;
        for(i = 0; i < nodes.length; i++){
            nodes[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodes,pivot);
        for( i = 1; i < nodes.length; i++){//!=
            nodes[i - 1].next = nodes[i];
        }
        nodes[i - 1].next = null;
        return nodes[0];
    }

    public static void arrPartition(Node[] nodes, int pivot){
        int small = -1;
        int big = nodes.length;
        int index = 0;
        while( index != big){
            if (nodes[index].val < pivot) {
                swap(nodes, index++, ++small);
            } else if (nodes[index].val == pivot){
                index++;
            }else{
                swap(nodes, index ++, --big);
            }
        }
    }

    public static void swap(Node[] nodes, int i, int j){
        Node temp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = temp;
    }
    public static Node listPartition2(Node head, int pivot) {
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node bH = null;
        Node bT = null;
        Node next = null;
        while( head != null){
            next = head.next;
            head.next = null;
            if( head.val < pivot){
                if( sH == null){
                    sH = head;
                    sT = head;
                }else{
                    sT.next = head;
                    sT = sT.next;
                }
            }else if ( head.val == pivot){
                if( eH == null){
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = eT.next;
                }
            }else{
                if(bH == null){
                    bH = head;
                    bT = head;
                }else{
                    bT.next = head;
                    bT = bT.next;
                }
            }
            head = next;
        }
        if( sH != null){
            sT.next = eH;
            eT = eT != null ? eT : sT;
        }
        // 小头和等头都为空
        // 两个头至少有一个不为空
        if (eT  != null) {
            eT.next = bH;
        }
        return sH != null ? sH : (eH != null ? eH : bH);
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        //head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }
}
