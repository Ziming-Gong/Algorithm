package LeetCodeDays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC199BinaryTreeRightSideView {
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

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> ans = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size = 1;
        while (!queue.isEmpty()) {
            int temp = 0;
            while (size != 0) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.add(cur.left);
                    temp++;
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                    temp++;
                }
                if (size == 1) {
                    ans.add(cur.val);
                }
            }
            size = temp;
        }
        return ans;
    }
}
