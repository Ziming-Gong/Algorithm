package Questions.code_5;

// 如果一个节点X，它左树结构和右树结构完全一样，那么我们说以X为头的子树是相等子树
// 给定一棵二叉树的头节点head，返回head整棵树上有多少棵相等子树
public class LeftRightSameTreeNumber {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int sameNumber1(Node head) {
        if (head == null) {
            return 0;
        }
        int ans = sameNumber1(head.left) + sameNumber1(head.right) + (same(head.left, head.right) ? 1 : 0);
        return ans;
    }

    public static boolean same(Node n1, Node n2) {
        if (n1 == null ^ n2 == null) {
            return false;
        }
        if (n1 == null && n2 == null) {
            return true;
        }
        return same(n1.left, n2.left) && same(n1.right, n2.right) && (n1.value == n2.value);
    }

    // 时间复杂度O(N)
    public static class Info {
        public String str;
        public int num;

        public Info(String string, int n) {
            str = string;
            num = n;
        }
    }

    public static int sameNumber2(Node head) {
        String algo = "SHA-256";
        Hash hash = new Hash(algo);
        return f(head, hash).num;
    }

    public static Info f(Node head, Hash hash) {
        if (head == null) {
            return new Info(hash.hashCode("#"), 0);
        }
        Info left = f(head.left, hash);
        Info right = f(head.right, hash);
        int num = left.num + right.num + (left.str.equals(right.str) ? 1 : 0);
        String str = hash.hashCode(head.value + left.str + right.str);
        return new Info(str, num);

    }

    public static Node randomBinaryTree(int restLevel, int maxValue) {
        if (restLevel == 0) {
            return null;
        }
        Node head = Math.random() < 0.2 ? null : new Node((int) (Math.random() * maxValue));
        if (head != null) {
            head.left = randomBinaryTree(restLevel - 1, maxValue);
            head.right = randomBinaryTree(restLevel - 1, maxValue);
        }
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 8;
        int maxValue = 4;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node head = randomBinaryTree(maxLevel, maxValue);
            int ans1 = sameNumber1(head);
            int ans2 = sameNumber2(head);
            if (ans1 != ans2) {
                System.out.println("出错了！");
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }

}
