package Questions.code_10;

import javafx.animation.RotateTransition;

import javax.print.attribute.standard.OrientationRequested;

//https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
public class LC426ConvertBinarySearchTreetoSortedDoublyLinkedList {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    ;

    public class Info {
        public Node start;
        public Node end;

        public Info(Node s, Node e) {
            start = s;
            end = e;
        }
    }

    public Node treeToDoublyList(Node root) {
        if(root == null){
            return null;
        }
        Info list = process(root);
        list.start.left = list.end;
        list.end.right = list.start;
        return list.start;
    }

    public Info process(Node head) {
        if (head == null) {
            return new Info(null, null);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        Node start = left.start == null ? head : left.start;
        Node end = right.end == null ? head : right.end;
        head.left = left.end;
        head.right = right.start;
        if (left.end != null) {
            left.end.right = head;
        }
        if (right.start != null) {
            right.start.left = head;
        }

        return new Info(start, end);
    }
}
