package LeetCodeDays;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;
import com.sun.xml.internal.ws.handler.MessageUpdatableContext;

import javax.swing.text.rtf.RTFEditorKit;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

public class LC2569HandlingSumQueriesAfterUpdate {
    public static long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        long sum = 0L;
        for (int i : nums2) {
            sum += i;
        }
        int n = 0;
        for (int[] q : queries) {
            if (q[0] == 3) {
                n++;
            }
        }
        long[] ans = new long[n];
        n = nums1.length;
        int index = 0;
        SegmentTree st = new SegmentTree(nums1);
        for (int[] query : queries) {
            if (query[0] == 1) {
                st.update(query[1], query[2]);
            } else if (query[0] == 2) {
                int temp = st.sum(1, n);
                sum += (long) temp * query[1];
            } else {
                ans[index++] = sum;
            }
        }
        return ans;
    }

    public static class SegmentTree {
        public int[] arr;
        public int[] update;
        public int N;

        public SegmentTree(int[] num) {
            N = num.length;
            arr = new int[(N + 1) << 2];
            update = new int[(N + 1) << 2];
            Arrays.fill(update, 1);
            for (int i = 0; i < N; i++) {
                add(i + 1, num[i] == 0 ? -1 : 1);
            }
        }

        public int sum(int l, int r) {
//            return sum(l, r, 1, N, 1);
//            pushDown(1);
            return arr[1];
        }

        private int sum(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                pushDown(rt);
                return arr[rt];
            }
            pushDown(rt);
            int mid = (l + r) / 2;
            int ans = 0;
            if (L <= mid) {
                ans += sum(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += sum(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }

        public void update(int l, int r) {
            update(l + 1, r + 1, -1, 1, N, 1);
        }

        private void update(int L, int R, int c, int l, int r, int rt) {
            if (L <= l && r <= R) {
                update[rt] *= c;
                return;
            }
            pushDown(rt);
            int mid = (l + r) / 2;
            if (L <= mid) {
                update(L, R, c, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, c, mid + 1, r, rt << 1 | 1);
            }
            // pushUp(rt);
        }

        private void pushDown(int rt) {
            if (update[rt] != 1) {
                arr[rt] *= update[rt];
                update[rt << 1] *= update[rt];
                update[rt << 1 | 1] *= update[rt];
                update[rt] = 1;
            }
        }

        public void add(int index, int c) {
            add(index, index, c, 1, N, 1);
        }

        private void add(int L, int R, int c, int l, int r, int rt) {
            if (L <= l && r <= R) {
                arr[rt] = c;
                return;
            }
            int mid = (l + r) / 2;
            if (L <= mid) {
                add(L, R, c, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, c, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        private void pushUp(int rt) {
            arr[rt] = arr[rt << 1] * update[rt << 1] + arr[rt << 1 | 1] * update[rt << 1 | 1];
        }
    }

    public static void main(String[] args) {
        int[] num1 = {1, 0, 1};
        int[] num2 = {0, 0, 0};
        int[][] q = {{1, 1, 1}, {2, 1, 0}, {3, 0, 0}};
        System.out.println(handleQuery(num1, num2, q));
    }
}
