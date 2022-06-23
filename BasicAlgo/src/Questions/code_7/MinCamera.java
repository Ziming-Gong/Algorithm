package Questions.code_7;

import javafx.animation.RotateTransition;

public class MinCamera {
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

    public class Info {
        public long uncovered;
        public long coveredHasCamera;
        public long coveredNotCamera;

        public Info(long u, long chc, long cnc) {
            uncovered = u;
            coveredHasCamera = chc;
            coveredNotCamera = cnc;
        }
    }

    public int minCameraCover(TreeNode root) {
        Info ans = process(root);
        return (int) Math.min(ans.uncovered + 1, Math.min(ans.coveredHasCamera, ans.coveredNotCamera));
    }

    public Info process(TreeNode node) {
        if (node == null) {
            return new Info(Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
        }
        Info left = process(node.left);
        Info right = process(node.right);
        // x这个点没有被覆盖，x孩子点都被覆盖
        long uncovered = left.coveredNotCamera + right.coveredNotCamera;

        //x这个点被覆盖+放相机
        long coveredHasCamera = Math.min(left.uncovered, Math.min(left.coveredHasCamera, left.coveredNotCamera)) +
                Math.min(right.uncovered, Math.min(right.coveredHasCamera, right.coveredNotCamera)) +
                1;
        //x这个点被覆盖，但是不放相机,x下面的点都被覆盖
        long coveredNotCamera = Math.min(left.coveredHasCamera + right.coveredHasCamera,
                Math.min(left.coveredHasCamera + right.coveredNotCamera, left.coveredNotCamera + right.coveredHasCamera));
        return new Info(uncovered, coveredHasCamera, coveredNotCamera);
    }

    public enum Status {
        UNCOVERED, COVERED_NOT_CAMERA, COVERED_HAS_CAMERA
    }

    public class Data {
        public Status status;
        public long num;

        public Data(Status status, long num) {
            this.status = status;
            this.num = num;
        }
    }

    public int minCameraCover1(TreeNode root) {
        Data ans = process1(root);
        return (int) (ans.status == Status.UNCOVERED ? ans.num + 1 : ans.num);

    }

    public Data process1(TreeNode head) {
        if (head == null) {
            return new Data(Status.COVERED_NOT_CAMERA, 0);
        }

        Data left = process1(head.left);
        Data right = process1(head.right);

        if (left.status == Status.UNCOVERED || right.status == Status.UNCOVERED) {
            return new Data(Status.COVERED_HAS_CAMERA, left.num + right.num + 1);
        }

        if (left.status == Status.COVERED_HAS_CAMERA || right.status == Status.COVERED_HAS_CAMERA) {
            return new Data(Status.COVERED_NOT_CAMERA, left.num + right.num);
        }
        return new Data(Status.UNCOVERED, left.num + right.num);

    }


}
