package com.zim.BinaryTree;

import java.time.temporal.ValueRange;
import java.util.ArrayList;

public class isBST {
    public static class Node{
        public int val;
        public Node left;
        public Node right;
        public Node(int val){
            this.val = val;
        }
    }

    public static boolean isBST1(Node head){
        if(head == null){
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for( int i = 1; i < arr.size(); i++){
            if(arr.get(i - 1).val >= arr.get(i).val){
                return false;
            }
        }
        return true;
    }
    public static void in(Node head, ArrayList arr){
        if(head == null){
            return;
        }
        in(head.left,arr);
        arr.add(head);
        in(head.right,arr);
    }

    public static boolean isBST2(Node head){
        if(head == null){
            return true;
        }
        return process(head).isBST;

    }
    public static class Info{
        public boolean isBST;
        public int max;
        public int min;

        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }
    public static Info process(Node head){
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int max = head.val;
        if (leftInfo != null) {
            max = Math.max(max,leftInfo.max );
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
        }
        int min = head.val;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
        }
        boolean isBST = true;
        if( leftInfo != null && !leftInfo.isBST){
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        if (leftInfo != null && leftInfo.max >= head.val) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.min <= head.val) {
            isBST = false;
        }

        return new Info(isBST,max,min);
    }

    //for test
    public static Node generateRandomBST(int maxLevel, int maxValue){
        return generate(1,maxLevel,maxValue);
    }
    public static Node generate(int level, int maxLevel, int maxValue){
        if( level > maxLevel || Math.random() < 0.5){
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level +1, maxLevel,maxValue);
        head.right = generate(level + 1, maxLevel,maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTime = 1000000;
        for( int i = 0; i <testTime; i++){
            Node head = generateRandomBST(maxLevel,maxValue);
            if(isBST1(head) != isBST2(head)){
                System.out.println("oops!");

            }
        }
        System.out.println("finish!");
    }























}
