package WeeklyPractice.May26;

import java.util.*;

public class test {



    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] ch = s.toCharArray();
        int pre = 0;
        int ans = 0;
        for(int i = 0; i < ch.length; i ++){
            char cur = ch[i];
            if(!map.containsKey(cur) || map.get(cur) < pre){
                map.put(cur, map.getOrDefault(cur, i));
                ans = Math.max(ans, i - pre + 1);
            }else{
                // if(pre == i){
                //     break;
                // }
                pre = map.get(cur) + 1;
                map.put(cur, i);
            }
        }
        return ans;

    }



    public static int maxLen2(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[] sorted = new int[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = arr[i];
        }
        Arrays.sort(sorted);
        SegmentTree st = new SegmentTree(n);
        st.update(rank(sorted, arr[0]), 1);
        int[] dp = new int[n];
        dp[0] = 1;
        int ans = 1;
        // 一个数字也不删！长度！
        int cur = 1;
        for (int i = 1; i < n; i++) {
            int rank = rank(sorted, arr[i]);
            // (dp[i - 1] + 1)
            int p1 = arr[i - 1] < arr[i] ? (dp[i - 1] + 1) : 1;
//			// rank : 就是当前的数字
//			// 1~rank-1 : 第二个信息的max
            int p2 = rank > 1 ? (st.max(rank - 1) + 1) : 1;
            dp[i] = Math.max(p1, p2);
            ans = Math.max(ans, dp[i]);
            if (arr[i] > arr[i - 1]) {
                cur++;
            } else {
                cur = 1;
            }
            // 我的当前值是rank
            // 之前有没有还是rank的记录！
            if (st.get(rank) < cur) {
                st.update(rank, cur);
            }
        }
        return ans;
    }

    public static int rank(int[] arr, int num) {
        int l = 0;
        int r = arr.length - 1;
        int m = 0;
        int ans = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (arr[m] >= num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans + 1;
    }

    public static class SegmentTree {
        private int n;
        private int[] max;
        private int[] update;

        public SegmentTree(int maxSize) {
            n = maxSize + 1;
            max = new int[n << 2];
            update = new int[n << 2];
            Arrays.fill(update, -1);
        }

        public int get(int index) {
            return max(index, index, 1, n, 1);
        }

        public void update(int index, int c) {
            update(index, index, c, 1, n, 1);
        }

        public int max(int right) {
            return max(1, right, 1, n, 1);
        }

        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        private void pushDown(int rt, int ln, int rn) {
            if (update[rt] != -1) {
                update[rt << 1] = update[rt];
                max[rt << 1] = update[rt];
                update[rt << 1 | 1] = update[rt];
                max[rt << 1 | 1] = update[rt];
                update[rt] = -1;
            }
        }

        private void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                max[rt] = C;
                update[rt] = C;
                return;
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                update(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                update(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        private int max(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return max[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            int ans = 0;
            if (L <= mid) {
                ans = Math.max(ans, max(L, R, l, mid, rt << 1));
            }
            if (R > mid) {
                ans = Math.max(ans, max(L, R, mid + 1, r, rt << 1 | 1));
            }

            return ans;
        }

    }
    public int subarraySum(int[] arr, int k) {
        int N = arr.length;
        int[] sum = new int[N+ 1];
        HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
        map.put(0,new LinkedList<>());
        map.get(0).add(0);

        for(int i = 0; i < N; i++){
            sum[i+1] = sum[i] + arr[i];
            if(!map.containsKey(sum[i+1])){
                map.put(sum[i+1], new LinkedList<>());
            }
            map.get(sum[i+1]).add(i+1);
        }
        int ans = 0;
        for(int i = 0; i < N+1; i ++){
            int cur = sum[i] + k;
            map.get(sum[i]).remove(i);
            if(!map.containsKey(cur)){
                continue;
            }
            ans ++;
        }

        return ans;


    }


}
