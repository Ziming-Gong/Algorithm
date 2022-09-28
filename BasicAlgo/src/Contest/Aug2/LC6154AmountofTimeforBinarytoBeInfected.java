package Contest.Aug2;

import javafx.animation.RotateTransition;

import javax.xml.stream.FactoryConfigurationError;

public class LC6154AmountofTimeforBinarytoBeInfected {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int amountOfTime(TreeNode root, int start) {
        Info ans = process(root, start);
        return Math.max(ans.findMax, Math.max(ans.root, ans.height));
    }

    public Info process(TreeNode cur, int start) {
        if (cur == null) {
            return new Info(false, 0, 0, 0);
        }
        Info left = process(cur.left, start);
        Info right = process(cur.right, start);
        if (cur.val == start) {
            return new Info(true, 0, Math.max(left.height, right.height), 0);
        }
        if(left.findS || right.findS){
            int toRoot = (left.findS ? left.root : right.root) + 1;
            int height = Math.max(left.findS ? left.height : right.height, (left.findS ? left.root + right.height: right.root + left.height) + 1);
            return new Info(true, height, height, toRoot);
        }
        return new Info(false, Math.max(left.height, right.height), 0, 0);

    }

    public class Info {
        public boolean findS;
        public int height;
        public int findMax;
        public int root;


        public Info(boolean s, int h, int f, int r) {
            findS = s;
            height = h;
            findMax = f;
            root = r;
        }
    }
}
