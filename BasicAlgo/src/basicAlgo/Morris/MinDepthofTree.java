package basicAlgo.Morris;

import javax.swing.tree.TreeNode;

public class MinDepthofTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    // morris
    public int minDepth(Node root) {
        if(root == null){
            return 0;
        }
        Node cur = root;
        Node mostRight = null;
        int min = Integer.MAX_VALUE;
        int level = 0;
        while(cur != null){
            mostRight = cur.left;
            if(mostRight != null){
                int curLevel = 1;
                while(mostRight.right != null && mostRight.right != cur){
                    mostRight = mostRight.right;
                    curLevel ++;
                }
                if(mostRight.right == null){
                    level ++;
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else{
                    if(mostRight.left == null){
                        min = Math.min(min, level);
                    }
                    level -= curLevel;
                    mostRight.right = null;
                }
            }else{
                level ++;
            }
            cur = cur.right;
        }
        cur = root;
        int right = 1;
        while(cur.right != null){
            right++;
            cur = cur.right;
        }
        if(cur.left == null){
            min = Math.min(min, right);
        }
        return min;
    }

    //common
    public static int minDepth1(Node root) {
        if(root == null){
            return 0;
        }
        return process(root);
    }
    public static int process(Node root){
        if(root.left == null && root.right == null){
            return 1;
        }
        int leftH = Integer.MAX_VALUE;
        if(root.left != null){
            leftH = process(root.left);
        }
        int rightH = Integer.MAX_VALUE;
        if(root.right != null){
            rightH = process(root.right);
        }
        return 1 + Math.min(leftH, rightH);
    }

}
