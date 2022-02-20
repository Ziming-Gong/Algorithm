package basicAlgo.BinaryTree;

import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LowestAncestor {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            this.value = val;
        }
    }
    public static Node lowestAncestor1(Node head, Node o1, Node o2){
        if (head == null) {
            return null;
        }
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head,null);
        fillParentMap(head,parentMap);
        HashSet<Node> set = new HashSet<>();
        Node cur = o1;
        set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            set.add(cur);
        }
        cur = o2;
        while (!set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap){
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left,parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }
    public static Node lowestAncestor2(Node head, Node o1, Node o2){
        if (head == null) {
            return null;
        }
        return process(head,o1,o2).lowestAncestor;

    }

    public static class Info{
        public boolean findA;
        public boolean findB;
        public Node lowestAncestor;

        public Info(boolean findA, boolean findB, Node lowestAncestor) {
            this.findA = findA;
            this.findB = findB;
            this.lowestAncestor = lowestAncestor;
        }
    }

    public static Info process(Node head, Node o1, Node o2){
        if (head == null) {
            return new Info(false,false,null);
        }
        Info leftInfo = process(head.left,o1, o2);
        Info rightInfo = process(head.right,o1,o2);



        boolean findA = (o1 == head) || leftInfo.findA || rightInfo.findA ;
        boolean findB = (o2 == head) || leftInfo.findB || rightInfo.findB;


        Node lowestAncestor = leftInfo.lowestAncestor != null ? leftInfo.lowestAncestor : (rightInfo.lowestAncestor != null ? rightInfo.lowestAncestor : null);
        if (lowestAncestor == null && findA && findB) {
            lowestAncestor = head;
        }

        return new Info(findA, findB, lowestAncestor);
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

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }












}
