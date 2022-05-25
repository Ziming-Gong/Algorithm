package WeeklyPractice.May11;


import basicAlgo.mergesorted.ans;

import java.util.Arrays;

// 一共有n个人，从左到右排列，依次编号0~n-1
// h[i]是第i个人的身高
// v[i]是第i个人的分数
// 要求从左到右选出一个子序列，在这个子序列中的人，从左到右身高是不下降的
// 返回所有符合要求的子序列中，分数最大累加和是多大
// n <= 10的5次方, 1 <= h[i] <= 10的9次方, 1 <= v[i] <= 10的9次方
public class SortedSubsequenceMaxSum {

    public static int right(int[] h, int[] v) {
        return process(h, v, 0, 0);
    }

    public static int process(int[] h, int[] v, int index, int preHeight) {
        if (index >= h.length) {
            return 0;
        }
        int p1 = process(h, v, index + 1, preHeight);
        int p2 = h[index] >= preHeight ? process(h, v, index + 1, h[index]) + v[index] : 0;
        return Math.max(p1, p2);
    }



    public static int maxSum(int[] h, int[] v) {
        int n = h.length;
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[i] = h[i];
        }
        Arrays.sort(rank);
        SegmentTree st = new SegmentTree(n);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int height = rank(rank, h[i]);
            //search
            // if i as the end, which value is the biggest
            int value = v[i] + st.max(height);
            st.update(height, value);
        }
        return st.max(n);
    }

    public static int rank(int[] rank, int num) {
        int l = 0;
        int r = rank.length-1;
        int mid = 0;
        int ans = 0;
        while (l <= r) {
            mid = (l + r) / 2;
            if (rank[mid] >= num) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans+1;
    }

    public static class SegmentTree {
        private static int n;
        private static int[] max;
        private static int[] update;

        public SegmentTree(int maxSize) {
            n = maxSize + 1;
            max = new int[n << 2];
            update = new int[n << 2];
            Arrays.fill(update, -1);
        }


        public static int max(int index) {
            return max(1, index, 1, n, 1);
        }

        public static int max(int L, int R, int l, int r, int rt) {
            if (L <= l && R >= r) {
                return max[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt);
            int ans = 0;
            if (L <= mid) {
                ans = Math.max(ans, max(L, R, l, mid, rt << 1));
            }
            if (R > mid) {
                ans = Math.max(ans, max(L, R, mid + 1, r, (rt << 1) | 1));
            }
            return ans;
        }

        public static void update(int index, int value) {
            update(index, index, value, 1, n, 1);
        }

        private static void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && R >= r) {
                max[rt] = C;
                update[rt] = C;
                return;
            }
            pushDown(rt);
            int mid = l + ((r - l) >> 1);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, (rt << 1) | 1);
            }
            pushUp(rt);
        }

        public static void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[(rt << 1) | 1]);
        }


        public static void pushDown(int rt) {
            if (update[rt] != -1) {
                update[rt << 1] = update[rt];
                max[rt << 1] = update[rt];
                update[(rt << 1) | 1] = update[rt];
                max[(rt << 1) | 1] = update[rt];
                update[rt] = -1;
            }
        }
    }

    // 为了测试
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v) + 1;
        }
        return ans;
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 30;
        int V = 100;
        int testTime = 20000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] h = randomArray(n, V);
            int[] v = randomArray(n, V);
            if (right(h, v) != maxSum(h, v)) {
                System.out.println("出错了!");
                break;
            }
        }
        System.out.println("测试结束");
    }


}
