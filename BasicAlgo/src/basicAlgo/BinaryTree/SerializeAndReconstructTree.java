package basicAlgo.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SerializeAndReconstructTree {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static Queue<String> preSerial(Node head){
        Queue<String> queue = new LinkedList<>();
        pres(head,queue);
        return queue;
    }
    public static void pres(Node head, Queue<String> queue){
        if( head == null){
            queue.add(null);
        }else{
            queue.add(String.valueOf(head.value));
            pres(head.left,queue);
            pres(head.right,queue);
        }
    }

    public static Queue<String> inSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        ins(head,ans);
        return ans;
    }
    public static void ins(Node head, Queue<String>ans){
        if(head == null){
            ans.add(null);
        }else {
            ins(head.right, ans);
            ans.add(String.valueOf(head.value));
            ins(head.left,ans);
        }
    }
    public static Queue<String> posSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        poss(head,ans);
        return ans;
    }
    public static void poss(Node head,Queue<String> ans){
        if(head == null){
            ans.add(null);
        }else{
            poss(head.left,ans);
            poss(head.right,ans);
            ans.add(String.valueOf(head.value));
        }
    }

    public static Node buildByPre(Queue<String> preList){
        if(preList == null || preList.size() == 0){
            return null;
        }
        return preb(preList);
    }
    public static Node preb(Queue<String> preList){
        String value = preList.poll();
        if(value == null){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = preb(preList);
        head.right = preb(preList);
        return head;
    }

    public static Node buildByPos(Queue<String> posList){
        if(posList == null || posList.size() == 0){
            return null;
        }
        Stack<String> stack = new Stack<>();
        while(!posList.isEmpty()){
            stack.push(posList.poll());
        }
        return posb(stack);
    }
    public static Node posb(Stack<String> stack){
        String value = stack.pop();
        if(value == null){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.right = posb(stack);
        head.left = posb(stack);
        return head;
    }
    public static Queue<String> levelSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        if(head == null){
            ans.add(null);
        }else{
            ans.add(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while( !queue.isEmpty()){
                head = queue.poll();
                if(head.left!= null){
                    ans.add(String.valueOf(head.left.value));
                    queue.add(head.left);
                }else{
                    ans.add(null);
                }
                if(head.right != null){
                    ans.add(String.valueOf(head.right.value));
                    queue.add(head.right);
                }else{
                    ans.add(null);
                }
            }
        }
        return ans;
    }
    public static Node buildByLevel(Queue<String> levelList){
        if(levelList == null || levelList.size() == 0){
            return null;
        }
        Node head = generateNode(levelList.poll());
        Queue<Node> queue = new LinkedList<>();
        if(head != null){
            queue.add(head);
        }
        Node node = null;
        while(!queue.isEmpty()){
            node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
        return head;
    }
    public static Node generateNode(String str){
        if(str == null){
            return null;
        }
        return new Node(Integer.valueOf(str));
    }
 //for test
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
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    // for test
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = preSerial(head);
            Queue<String> pos = posSerial(head);
            Queue<String> level = levelSerial(head);
            Node preBuild = buildByPre(pre);
            Node posBuild = buildByPos(pos);
            Node levelBuild = buildByLevel(level);
            if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }
































}
