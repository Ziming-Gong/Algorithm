package Contest.Aug1;

import WeeklyPractice.April02.PerfectPairNumber;
import sun.tools.attach.HotSpotVirtualMachine;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;

public class LC2370LongestIdealSubsequence {
    public static int longestIdealString(String s, int k) {
        char[] str = s.toCharArray();
        int[] dp = new int[26];
        int N = str.length;
        SegmentTree st = new SegmentTree();
        for (int i = 0; i < str.length; i++) {
            int l = Math.max(0, str[i] - 'a' - k);
            int r = Math.min(25, str[i] - 'a' + k);
            int max = st.query(l + 1, r + 1);
            st.update(str[i] - 'a' + 1, max + 1);
        }
        return st.query(1, 26);
    }

    public static class SegmentTree {
        // public int[] arr;
        public int[] max;
        public int SIZE;

        public SegmentTree() {
            SIZE = 26 * 4;
            // arr = new int[SIZE];
            max = new int[SIZE];
        }

        public void update(int index, int value) {
            update(index, 1, 26, 1, value);
        }

        public void update(int c, int L, int R, int rt, int value) {
            if (L >= c && R <= c) {
                System.out.println(rt);
                max[rt] = value;
                return;
            }
            int mid = (L + R) / 2;
            max[rt] = Math.max(max[rt], value);
            if (c <= mid) {
                update(c, L, mid, rt * 2, value);
            }
            if (c > mid) {
                update(c, mid + 1, R, rt * 2 + 1, value);
            }
        }

        public int query(int l, int r) {
            return query(l, r, 1, 26, 1);
        }

        public int query(int l, int r, int L, int R, int rt) {
            if (l <= L && r >= R) {
                return max[rt];
            }

            int mid = (L + R) / 2;
            int left = l <= mid ? query(l, r, L, mid, rt * 2) : Integer.MIN_VALUE;
            int right = r > mid ? query(l, r, mid + 1, R, rt * 2 + 1) : Integer.MIN_VALUE;
            return Math.max(left, right);
        }
    }

    public static void main(String[] args) {
        String str = "abz";
        int k = 2;
        longestIdealString(str, k);
    }
}
