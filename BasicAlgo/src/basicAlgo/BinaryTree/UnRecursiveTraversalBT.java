package basicAlgo.BinaryTree;

import java.util.Stack;

public class UnRecursiveTraversalBT {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int value){
            this.value = value;
        }
    }

    public static void pre(Node head){
        System.out.println("pre: ");
        if( head != null){
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while(!stack.isEmpty()){
                head = stack.pop();
                System.out.print(head.value + ",");
                if(head.right != null){
                    stack.push(head.right);
                }
                if(head.left != null){
                    stack.push(head.left);
                }

            }
        }
        System.out.println();
    }

    public static void in(Node head){
        System.out.println("in:");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            Node cur = head;
            while (cur != null || !stack.isEmpty()) {
                if( cur != null){
                    stack.push(cur);
                    cur = cur.left;
                }else {
                    cur = stack.pop();
                    System.out.print(cur.value +",");
                    cur = cur.right;
                }
            }
        }
        System.out.println();

    }
    public static void pos1(Node head){
        System.out.println("pos 1:");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            Stack<Node> ansStack = new Stack<>();
            stack.push(head);
            while(!stack.isEmpty()){
                head = stack.pop();
                ansStack.push(head);
                if(head.left != null){
                    stack.push(head.left);
                }
                if(head.right != null){
                    stack.push(head.right);
                }
            }
            while (!ansStack.isEmpty()) {
                System.out.print(ansStack.pop().value +",");
            }
        }
        System.out.println();
    }
    public static void pos2(Node head){
        System.out.println("pos2: ");
        if(head != null){
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            Node cur = null;
            while (!stack.isEmpty()) {
                cur = stack.peek();
                if(cur.left != null && head != cur.left && head != cur.right){
                    stack.push(cur.left);
                }else if(cur.right != null && head != cur.right){
                    stack.push(cur.right);
                } else{
                    System.out.print(stack.pop().value + ",");
                    head = cur;
                }
            }
        }

    }


    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");
    }
}
