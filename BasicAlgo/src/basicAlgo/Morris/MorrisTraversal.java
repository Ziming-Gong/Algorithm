package basicAlgo.Morris;

import basicAlgo.Recursive.PrintAllPermutations;
import sun.nio.cs.ext.MS874;

public class MorrisTraversal {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // common binary tree traversal
    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.printf(head.value + ", ");
        pre(head.left);
        pre(head.right);
    }

    public static void in(Node head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.printf(head.value + ", ");
        in(head.right);
    }

    public static void post(Node head) {
        if (head == null) {
            return;
        }
        post(head.left);
        post(head.right);
        System.out.printf(head.value + ", ");
    }

    //Morris traversal
    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.printf(cur.value + ", ");
            cur = cur.right;
        }
    }

    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    System.out.printf(cur.value + ", ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.printf(cur.value + ", ");
            }
            cur = cur.right;
        }
        System.out.println();
    }

    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    print(cur.left);

                }
            }
            cur = cur.right;
        }
        print(head);

    }

    public static void print(Node node){
        Node tail = reverse(node);
        Node cur = tail;
        while(cur != null){
            System.out.printf(cur.value +", ");
            cur = cur.right;
        }
        reverse(tail);

    }
    public static Node reverse(Node node){
        Node pre = null;
        Node post = null;
        while(node != null){
            post = node.right;
            node.right = pre;
            pre = node;
            node = post;
        }
        return pre;
    }
//
//    public static boolean isBST(Node head){
//        if(head == null){
//            return false;
//        }
//    }





    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
//        pre(head);
//        System.out.println();
//        in(head);
//        System.out.println();
        post(head);
        System.out.println();
//        printTree(head);
//        morrisIn(head);
//        System.out.println();
//        morrisPre(head);
        morrisPos(head);
//        printTree(head);
//        System.out.println(head);

    }
}
