package com.zim.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class isCBT {
    public static class Node{
        public int val;
        public Node left;
        public Node right;
        public Node(int val){
            this.val = val;
        }
    }
    public static boolean isCBT1(Node head){

        boolean isEnd = false;
        Queue<Node> queue = new LinkedList<>();
        if(head != null){
            queue.add(head);
        }
        Node node = null;
        while( !queue.isEmpty()){
            node = queue.poll();

            if(node.left == null && node.right != null){
                return false;
            }

            if(isEnd && node.left != null){
                return false;
            }else if(!isEnd && node.left != null){
                queue.add(node.left);
            }
            if(isEnd && node.right != null){
                return false;
            }else if (!isEnd && node.right != null){
                queue.add(node.right);
            }
            if(node.right == null){
                isEnd = true;
            }
        }
        return true;
    }
    public static boolean isCBT2(Node head){
        if(head == null){
            return true;
        }
        return process(head).isCbt;
    }
    public static class Info{
        public boolean isCbt;
        public boolean isFull;
        public int height;

        public Info(boolean isCbt, boolean isFull, int height) {
            this.isCbt = isCbt;
            this.isFull = isFull;
            this.height = height;
        }
    }

    public static Info process(Node head){
        if(head == null){
            return new Info(true,true,0);
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        boolean isFull = leftInfo.isFull &&
                         rightInfo.isFull &&
                         (leftInfo.height == rightInfo.height);

        boolean isCbt = false;
        if( isFull){
            isCbt = true;
        }else {
            if(rightInfo.isCbt && leftInfo.isCbt){
                if( leftInfo.isCbt && rightInfo.isFull &&
                    leftInfo.height == rightInfo.height + 1){
                    isCbt = true;
                }
                if( leftInfo.isFull && rightInfo.isFull &&
                        leftInfo.height == rightInfo.height + 1){
                    isCbt = true;
                }
                if( leftInfo.isFull && rightInfo.isCbt &&
                     leftInfo.height == rightInfo.height){
                    isCbt = true;
                }
            }
        }
        return new Info(isCbt,isFull,height);

    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 10000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
