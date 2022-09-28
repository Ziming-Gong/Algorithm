package Contest.Sep17;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class LC6182ReverseOddLevelsofBinaryTree {

    public static class TreeNode {
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

    public static TreeNode reverseOddLevels(TreeNode root) {
        if (root == null) {
            return root;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        boolean shouldReverse = true;
        while (!list.isEmpty()) {
            LinkedList<TreeNode> temp = new LinkedList<>();
            Stack<TreeNode> stack = new Stack<>();
            LinkedList<TreeNode> help = new LinkedList<>();
            while (!list.isEmpty()) {
                TreeNode cur = list.poll();
                TreeNode left = cur.left;
                TreeNode right = cur.right;
                if(left != null){
                    temp.add(left);
                    if(shouldReverse){
                        stack.push(left);
                        help.addLast(left);
                    }
                }
                if(right != null){
                    if(shouldReverse){
                        stack.push(right);
                        help.addLast(right);
                    }
                    temp.add(right);
                }
            }
            if (shouldReverse) {
                while (!stack.isEmpty()) {
                    TreeNode target = stack.pop();
                    TreeNode cur = help.pollFirst();
                    cur.val = target.val;
                }

            }

            shouldReverse = !shouldReverse;
            list = temp;
        }
        return root;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(5);
        root.left = left;
        root.right = right;
        reverseOddLevels(root);

    }
}
