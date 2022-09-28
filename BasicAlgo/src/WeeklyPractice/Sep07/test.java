package WeeklyPractice.Sep07;

import java.util.Arrays;

public class test {
    public static int fast3(int n, int b, int[] powers, int[] rates) {
        if (n == 0) {
            return 0;
        }
        if (b == 0 || powers[0] > b) {
            return -1;
        }
        int l = 1;
        int r = rates[0] * n;
        int m = 0;
        int ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (minPower3(powers, rates, m) <= b) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    public static int minPower3(int[] powers, int[] rates, int time) {
        int n = powers.length;
        int[] dp = new int[n + 1];
        //      dp[n-1]  dp[n]
        //         n-1     n
        SegmentTree st = new SegmentTree(n + 1);
        st.update(n, 0);
        for (int i = n - 1; i >= 0; i--) {
            if (rates[i] > time) {
                dp[i] = Integer.MAX_VALUE;
            } else {
                int j = Math.min(i + (time / rates[i]) - 1, n - 1);
                // for.... logN
                int next = st.min(i + 1, j + 1);
                int ans = next == Integer.MAX_VALUE ? next : (powers[i] + next);
                dp[i] = ans;
            }
            st.update(i, dp[i]);
        }
        return dp[0];
    }

    public static class SegmentTree {
        private int n;
        private int[] min;

        public SegmentTree(int size) {
            n = size + 1;
            min = new int[n << 2];
            Arrays.fill(min, Integer.MAX_VALUE);
        }

        private void pushUp(int rt) {
            min[rt] = Math.min(min[rt << 1], min[rt << 1 | 1]);
        }

        public void update(int i, int v) {
            update(i + 1, i + 1, v, 1, n, 1);
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                min[rt] = C;
                return;
            }
            int mid = (l + r) >> 1;
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int min(int l, int r) {
            return min(l + 1, r + 1, 1, n, 1);
        }

        private int min(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return min[rt];
            }
            int mid = (l + r) >> 1;
            int left = Integer.MAX_VALUE;
            int right = Integer.MAX_VALUE;
            if (L <= mid) {
                left = min(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                right = min(L, R, mid + 1, r, rt << 1 | 1);
            }
            return Math.min(left, right);
        }

    }
}
