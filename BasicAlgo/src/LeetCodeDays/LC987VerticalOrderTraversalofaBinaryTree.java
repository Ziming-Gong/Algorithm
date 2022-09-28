package LeetCodeDays;

import java.util.*;

public class LC987VerticalOrderTraversalofaBinaryTree {
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

    public int maxLeft;
    public int maxRight;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<PriorityQueue<Node>> arr = new ArrayList<>();
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        System.out.println(maxLeft);
        System.out.println(maxRight);
        maxLeft = 0;
        maxRight = 0;
        process(root, arr, 0, indexMap, 0);
        List<List<Integer>> ans = new ArrayList<>();
        int size = arr.size();
        for (int i = 0; i < size; i++) {
            ans.add(new ArrayList<>());
        }

        for (int i = maxLeft; i <= maxRight; i++) {
            PriorityQueue<Node> queue = arr.get(i);
            if (queue.isEmpty()) {
                continue;
            }
            while (!queue.isEmpty()) {
                ans.get(i).add(queue.poll().val);
            }
        }


        return ans;

    }

    public void process(TreeNode cur, List<PriorityQueue<Node>> arr, int index, HashMap<Integer, Integer> indexMap, int height) {
        if (cur == null) {
            return;
        }
        int i;
        if (indexMap.containsKey(index)) {
            i = indexMap.get(index);
        } else {
            i = arr.size();
            arr.add(new PriorityQueue<>(new NodeComparator()));
            indexMap.put(index, i);
        }
        maxLeft = Math.min(index, maxLeft);
        maxRight = Math.max(maxRight, index);
        arr.get(i).add(new Node(height, cur.val));
        process(cur.left, arr, index - 1, indexMap, height + 1);
        process(cur.right, arr, index + 1, indexMap, height + 1);
    }

    public class Node {
        public int row;
        public int val;

        public Node(int r, int v) {
            row = r;
            val = v;
        }
    }

    public class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.row == o2.row ? o1.val - o2.val : o1.row - o2.row;
        }
    }


}
