package LeetCodeDays;

import WeeklyPractice.April02.PerfectPairNumber;


public class LC105ConstructBinaryTreefromPreorderandInorderTraversal {
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

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return process(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }


    public TreeNode process(int[] pre, int[] in, int pl, int pr, int il, int ir) {
        if (pl == pr) {
            return new TreeNode(pre[pr]);
        }

        TreeNode head = new TreeNode(pre[pl]);

        int leftsum = 0;
        for (int i = il; i <= ir; i++) {
            if (in[i] == pre[pl]) {
                break;
            }
            leftsum++;
        }
        int rightsum = ir - il - leftsum;
        head.left = process(pre, in, pl + 1, pl + leftsum, il, il + leftsum);
        head.right = process(pre, in, pl + leftsum + 1, pr, il + leftsum + 1, ir);


        return head;


    }
}
