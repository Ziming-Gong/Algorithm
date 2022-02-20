package basicAlgo.BinaryTree;

import java.util.ArrayList;

public class MaxSubBSTSize  {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            this.value = val;
        }
    }
    public static int maxSubBSTSize2(Node head) {
        if(head == null) {
            return 0;
        }
        return process(head).maxBSTSubtreeSize;
    }

    public static class Info {
        public int maxBSTSubtreeSize;
        public int allSize;
        public int max;
        public int min;

        public Info(int m, int a, int ma, int mi) {
            maxBSTSubtreeSize = m;
            allSize = a;
            max = ma;
            min = mi;
        }
    }
    public static Info process(Node head){
        if (head == null) {
            return null;
        }

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int max = head.value;
        int min = head.value;
        int allSize = 1;
        int p1 = -1;
        int p2 = -1;
        int p3 = -1;

        if(leftInfo != null){
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            allSize += leftInfo.allSize;
            p1 = leftInfo.maxBSTSubtreeSize;
        }
        if (rightInfo != null) {
            min = Math.min(min,rightInfo.min);
            max = Math.max(max, rightInfo.max);
            allSize += rightInfo.allSize;
            p2 = rightInfo.maxBSTSubtreeSize;
        }

        boolean leftBST = leftInfo == null ? true : (leftInfo.allSize == leftInfo.maxBSTSubtreeSize);
        boolean rightBST = rightInfo == null ? true : (rightInfo.allSize == rightInfo.maxBSTSubtreeSize);

        if (leftBST && rightBST) {
            boolean leftMaxLess = leftInfo == null ? true : (leftInfo.max < head.value);
            boolean rightMaxLess = rightInfo == null ? true : (rightInfo.min > head.value);
            if(leftMaxLess && rightMaxLess){
                p3 = (leftInfo == null ? 0 : leftInfo.allSize) +
                        (rightInfo == null ? 0 : rightInfo.allSize)  + 1;
            }
        }
        return new Info( Math.max(p1, Math.max(p2,p3)),allSize,max,min);
    }

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
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
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSubBSTSize1(head) != maxSubBSTSize2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }











}
