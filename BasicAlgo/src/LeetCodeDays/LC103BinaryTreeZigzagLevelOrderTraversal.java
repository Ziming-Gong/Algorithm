package LeetCodeDays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC103BinaryTreeZigzagLevelOrderTraversal {
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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<List<Integer>> ans = new ArrayList<>();
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        int size = 1;
        boolean toRight = true;
        while (!list.isEmpty()) {
            List<Integer> curList = new ArrayList<>();
            int temp = 0;
            while (size > 0) {
                TreeNode cur = list.poll();
                curList.add(cur.val);
                if (cur.left != null) {
                    temp++;
                    list.add(cur.left);
                }
                if (cur.right != null) {
                    temp++;
                    list.add(cur.right);
                }
            }
            size = temp;
            if (!toRight) {
                reverse(curList);
            }
            ans.add(curList);
            toRight ^= true;
        }
        return ans;
    }

    public void reverse(List<Integer> list) {
        int L = 0;
        int R = list.size() - 1;
        while (L < R) {
            int temp = list.get(L);
            list.set(L, list.get(R));
            list.set(R, temp);
            L++;
            R--;
        }
    }
}
