package WeeklyPractice.Feb2023.Feb03;


import java.util.Arrays;

import static OA.TikTok.Code05_SendOutDiamondDays.days1;
import static OA.TikTok.Code05_SendOutDiamondDays.days2;

public class TTthrowDiamonds {
    public static int mySolution(int[] diamonds) {
        int ans = 0;
        int n = diamonds.length;
        IndexTree it = new IndexTree(n);
        SegmentTree st = new SegmentTree(diamonds);
        int start = 0, find = 0;
        while (it.sum(1, n) != 0) {
            find = find(st, start, n);
            ans += days(it, start, find, n);
            it.add(find, -1);
            st.update(find, Integer.MAX_VALUE);
            start = find;
        }

        return ans;

    }

    public static int days(IndexTree it, int start, int find, int n) {
        if (start <= find) {
            return it.sum(start, find);
        } else {
            return it.sum(start, n) + it.sum(1, find);
        }

    }

    public static int find(SegmentTree st, int start, int end) {
        int l, r, min = st.min(1, end);
        if (st.min(start, end) == min) {
            l = start;
            r = end;
        } else {
            l = 1;
            r = start;
        }
        int ans = -1, m;
        while (l <= r) {
            m = (l + r) >> 1;
            if (st.min(l, m) == min) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    ///
    public static class IndexTree {
        public int[] arr;
        public int size;

        public IndexTree(int n) {
            size = n + 1;
            arr = new int[size];
            for (int i = 1; i < size; i++) {
                add(i, 1);
            }
        }

        public void add(int index, int num) {
            while (index <= size) {
                arr[index] += num;
                index += (index & -index);
            }
        }

        public int sum(int index) {
            int ans = 0;
            while (index > 0) {
                ans += arr[index];
                index -= index & -index;
            }
            return ans;
        }

        public int sum(int l, int r) {
            return sum(r) - sum(l - 1);
        }
    }

    public static class SegmentTree {
        private int n;
        private int[] arr;

        public SegmentTree(int[] arr) {
            n = arr.length;
            this.arr = new int[(n + 1) << 2];
            Arrays.fill(arr, Integer.MAX_VALUE);
            for (int i = 1; i <= arr.length; i++) {
                update(i, arr[i - 1]);
            }
        }

        public void update(int i, int c) {
            update(i, i, 1, n, c, 1);
        }

        private void update(int L, int R, int l, int r, int c, int rt) {
            if (L <= l && r <= R) {
                arr[rt] = c;
                return;
            }
            int mid = (l + r) >> 1;
            if (L <= mid) {
                update(L, R, l, mid, c, rt << 1);
            }
            if (R > mid) {
                update(L, R, mid + 1, r, c, rt << 1 | 1);
            }
            pushUp(rt);
        }

        private void pushUp(int rt) {
            arr[rt] = Math.min(arr[rt << 1], arr[rt << 1 | 1]);
        }

        public int min(int l, int r) {
            return min(l, r, 1, n, 1);
        }

        private int min(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return arr[rt];
            }
            int mid = (l + r) >> 1;
            int ans = Integer.MAX_VALUE;
            if (L <= mid) {
                ans = min(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans = Math.min(ans, min(L, R, mid + 1, r, rt << 1 | 1));
            }
            return ans;
        }

    }


    // 为了测试
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 3, 1, 2};
        System.out.println(days1(arr));
        System.out.println(days2(arr));

        int N = 100;
        int V = 100000;
        int testTimes = 1000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] diamonds = randomArray(n, V);
            int ans1 = days1(diamonds);
            int ans2 = mySolution(diamonds);
            if (ans1 != ans2) {
                System.out.println("出错了!");
                break;
            }
        }
        System.out.println("功能测试结束");

        System.out.println("性能测试开始");
        int n = 100000;
        int v = 1000000000;
        int[] diamonds = randomArray(n, V);
        System.out.println("宝石数量 : " + n);
        System.out.println("价值范围 : " + v);
        long start = System.currentTimeMillis();
        mySolution(diamonds);
        long end = System.currentTimeMillis();
        System.out.println("运行时间 : " + (end - start) + " 毫秒");
        System.out.println("性能测试结束");
    }

    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v);
        }
        return ans;
    }

}
