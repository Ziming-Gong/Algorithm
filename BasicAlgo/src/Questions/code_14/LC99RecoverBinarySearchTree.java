package Questions.code_14;

import LeetCodeDays.LC199BinaryTreeRightSideView;

public class LC99RecoverBinarySearchTree {
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

    public int index;

    public void recoverTree(TreeNode root) {
        TreeNode[] list = new TreeNode[1000];
        index = 0;
        inOrder(root, list);
        int count = 0;
        TreeNode first = null;
        TreeNode second = null;
        for (int i = 1; i < 1000; i++) {
            if (list[i] == null) {
                break;
            }
            if (list[i].val < list[i - 1].val) {
                if (count == 0) {
                    count++;
                    first = list[i - 1];
                    second = list[i];
                } else if (count == 1) {
                    second = list[i];
                    count++;
                    break;
                }

            }
        }
        first.val ^= second.val;
        second.val ^= first.val;
        first.val ^= second.val;


    }

    public void inOrder(TreeNode head, TreeNode[] list) {
        if (head == null) {
            return;
        }
        inOrder(head.left, list);
        list[index++] = head;
        inOrder(head.right, list);
    }

    public void recoverTree1(TreeNode root) {

    }
}
