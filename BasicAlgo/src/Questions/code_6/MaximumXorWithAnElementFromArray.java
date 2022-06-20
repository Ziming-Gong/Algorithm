package Questions.code_6;

import javax.xml.bind.ValidationEvent;

public class MaximumXorWithAnElementFromArray {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        if (nums.length == 1) {
            return null;
        }
        BitTier bt = new BitTier();
        for (int i = 0; i < nums.length; i++) {
            bt.add(nums[i]);
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = bt.maxXor(queries[i][0], queries[i][1]);
        }
        return ans;

    }

    public class Node {
        public int min;
        public Node[] nexts = new Node[2];


        public Node() {
            min = Integer.MAX_VALUE;
            nexts = new Node[2];
        }
    }

    public class BitTier {
        public Node head;

        public BitTier() {
            head = new Node();
        }

        public void add(int value) {
            Node cur = head;
            head.min = Math.min(head.min, value);
            for (int i = 30; i >= 0; i--) {
                int path = (value >> i) & 1;
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new Node();
                }
                cur = cur.nexts[path];
                cur.min = Math.min(cur.min, value);

            }
        }

        public int maxXor(int value, int small) {
            int ans = 0;
            Node cur = head;
            if (head.min > small) {
                return -1;
            }
            for (int move = 30; move >= 0; move--) {
                int path = (value >> move) & 1;
                int best = path ^ 1;
                if (cur.nexts[best] == null || cur.nexts[best].min > small) {
                    best = best ^ 1;
                }
                ans |= (path ^ best) << move;
                cur = cur.nexts[best];
            }
            return ans;
        }
    }
}
