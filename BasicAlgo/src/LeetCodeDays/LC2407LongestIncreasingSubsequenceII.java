package LeetCodeDays;

public class LC2407LongestIncreasingSubsequenceII {
    public static int lengthOfLIS(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        int gap = min - 1;
        int n = arr.length;
        SegmentTree st = new SegmentTree(max);
        int ans = Integer.MIN_VALUE;
        st.add(arr[0], 1);
        for (int i = 1; i < n; i++) {
            int cur = st.search(Math.max(1, arr[i] - k), arr[i] - 1);
            cur++;
            st.add(arr[i], cur);
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    public static class SegmentTree {
        public int[] arr;
        public int[] max;
        public int[] lazy;
        public int size;

        public SegmentTree(int s) {
            this.size = s + 1;
            arr = new int[size << 2];
            max = new int[size << 2];
            lazy = new int[size << 2];
        }

        public void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }


        public int search(int l, int r) {
            if (l > r) {
                return 0;
            }
            return search(l, r, 1, size, 1);
        }

        private int search(int L, int R, int l, int r, int rt) {
            if (l >= L && R >= r) {
                return max[rt];
            }
            int mid = (l + r) >> 1;
            int ans = 0;
            if (L <= mid) {
                ans = Math.max(ans, search(L, R, l, mid, rt << 1));
            }
            if (R > mid) {
                ans = Math.max(ans, search(L, R, mid + 1, r, (rt << 1) | 1));
            }
            return ans;
        }

        public void add(int index, int C) {
            add(index, index, C, 1, size, 1);
        }

        private void add(int L, int R, int C, int l, int r, int rt) {
            if (l >= L && R >= r) {
                max[rt] = Math.max(max[rt], C);
                return;
            }
            int mid = (l + r) >> 1;
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, (rt << 1) | 1);
            }
            pushUp(rt);
        }
    }

    public static void main(String[] args) {
        int[] arr = {5,8,15};
        int k = 3;
        System.out.println(lengthOfLIS(arr, k));
    }
}
