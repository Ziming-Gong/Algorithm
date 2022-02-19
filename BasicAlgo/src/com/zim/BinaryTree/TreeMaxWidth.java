package com.zim.BinaryTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class TreeMaxWidth {

    public static class Node{
        public int val;
        public Node left;
        public Node right;
        public Node(int val){
            this.val = val;
        }
    }

    public static int maxWidthNoMap(Node head){
        if (head == null) {
            return 0;
        }
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(head);
        int max = 0;
        Node curNode = head;
        Node endNode = null;
        int curCount = 0;
        while(!nodes.isEmpty()){
            Node cur = nodes.poll();

            if(cur.left != null){
                nodes.add(cur.left);
                endNode = cur.left;
            }
            if(cur.right != null){
                nodes.add(cur.right);
                endNode = cur.right;
            }
            curCount++;
            if( cur == curNode){
                max = Math.max(curCount,max);
                curCount = 0;
                curNode = endNode;
            }
        }
        return max;
    }
    public static int maxWidthUseMap(Node head){
        if( head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head,1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = 0;
        while( !queue.isEmpty()){
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if(cur.left != null){
                queue.add(cur.left);
                levelMap.put(cur.left, curNodeLevel+1);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                levelMap.put(cur.right,curNodeLevel+1);
            }
            if( curNodeLevel == curLevel){
                curLevelNodes++;
            }else {
                curLevel ++;
                max = Math.max(max,curLevelNodes);
                curLevelNodes = 1;
            }
        }
        max = Math.max(max,curLevelNodes);
        return max;
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
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }
}
