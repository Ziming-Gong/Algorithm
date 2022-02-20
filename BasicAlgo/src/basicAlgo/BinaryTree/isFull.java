package basicAlgo.BinaryTree;

public class isFull {
    public static class Node{
        public int val;
        public Node left;
        public Node right;
        public Node(int val){
            this.val = val;
        }
    }

    public static boolean isFull1(Node head){
        if (head == null) {
            return true;
        }
        Info info = process1(head);
        int height = info.height;
        int num = info.nodeNum;
        return ((1 << height) - 1) == num;
    }
    public static class Info{
        public int height;
        public int nodeNum;

        public Info(int height, int nodeNum) {
            this.height = height;
            this.nodeNum = nodeNum;
        }
    }
    public static Info process1(Node head){
        if(head == null){
            return new Info(0,0);
        }
        Info leftInfo = process1(head.left);
        Info rightInfo = process1(head.right);

        int height = Math.max(leftInfo.height,rightInfo.height) + 1;
        int nodeNum = leftInfo.nodeNum + rightInfo.nodeNum + 1;

        return new Info(height,nodeNum);
    }

    public static boolean isFull2(Node head){
        if (head == null) {
            return true;
        }
        return process2(head).isFull;
    }
    public static class Info2{
        public boolean isFull;
        public int height;

        public Info2(boolean isFull, int height) {
            this.isFull = isFull;
            this.height = height;
        }
    }
    public static Info2 process2(Node head){
        if( head == null){
            return new Info2(true, 0);
        }
        Info2 leftInfo = process2(head.left);
        Info2 rightInfo = process2(head.right);

        boolean isFull = leftInfo.isFull &&
                         rightInfo.isFull &&
                         leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info2(isFull, height);
    }

    public static Node generateRandomBst(int maxLevel, int maxVal){
        return generate(1,maxLevel,maxVal);
    }

    public static Node generate(int level, int maxLevel, int maxVal){
        if( level > maxLevel || Math.random() < 0.5){
            return null;
        }
        Node head = new Node((int) Math.random() * maxVal);
        head.left = generate(level + 1, maxLevel, maxVal);
        head.right = generate(level + 1, maxLevel,maxVal);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxVal = 100;
        int testTime = 10000000;
        for( int i = 0; i < testTime; i++){
            Node head = generateRandomBst(maxLevel,maxVal);
            if(isFull1(head) != isFull2(head)){
                System.out.println("oops!");
                break;
            }
        }
        System.out.println("finish!");
    }



























}
