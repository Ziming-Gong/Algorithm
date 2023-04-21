package WeeklyPractice.Jan022023;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC662MaximumWidthofBinaryTree {

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

    long[][] levels = new long[3001][2];

    public int widthOfBinaryTree(TreeNode root) {
        for (int i = 0; i <= 3000; i++) {
            levels[i][0] = Integer.MAX_VALUE;
            levels[i][1] = Integer.MIN_VALUE;
        }
        dfs(root, 0, 1);
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i <= 3000; i++) {
            if (levels[i][1] == Integer.MIN_VALUE && levels[i][0] == Integer.MAX_VALUE) {
                break;
            }
            ans = Math.max((int) (levels[i][1] - levels[i][0] + 1), ans);
        }
        return ans;
    }

    public void dfs(TreeNode cur, int level, long num) {


        if (cur == null) {
            return;
        }

        levels[level][0] = Math.min(levels[level][0], num);
        levels[level][1] = Math.max(levels[level][1], num);


        dfs(cur.left, level + 1, 2 * num);
        dfs(cur.right, level + 1, 2 * num + 1);


    }

    public class Info {
        public TreeNode cur;
        public int num;

        public Info(TreeNode cur, int num) {
            this.cur = cur;
            this.num = num;
        }
    }

    public int widthOfBinaryTree1(TreeNode root) {
        LinkedList<Info> list = new LinkedList<>();
        list.add(new Info(root, 1));
        int ans = Integer.MAX_VALUE;
        LinkedList<Info> next = new LinkedList<>();
        while (!list.isEmpty()) {
            ans = Math.max(ans, list.peekLast().num - list.peekFirst().num + 1);
            while (!list.isEmpty()) {
                Info cur = list.pollFirst();
                if (cur.cur.left != null) {
                    next.add(new Info(cur.cur.left, cur.num * 2));
                }
                if (cur.cur.right != null) {
                    next.add(new Info(cur.cur.right, cur.num * 2 + 1));
                }
            }
            list = next;
            next = new LinkedList<>();
        }
        return ans;

    }
}
