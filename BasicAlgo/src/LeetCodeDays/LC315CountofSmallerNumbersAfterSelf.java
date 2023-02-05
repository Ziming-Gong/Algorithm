package LeetCodeDays;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.*;

public class LC315CountofSmallerNumbersAfterSelf {
    public static List<Integer> countSmaller(int[] nums) {
//         int max = Integer.MIN_VALUE;
//         int min = Integer.MAX_VALUE;
//         for (int i : nums) {
//             max = Math.max(max, i);
//             min = Math.min(min, i);
//         }
//         int gap = -min + 1;

//         SegmentTree st = new SegmentTree(max + gap);
//         List<Integer> ans = new LinkedList<>();
//         int n = nums.length;
//         for (int i = n - 1; i >= 0; i--) {
//             int a = nums[i] + gap;
//             ans.add(0, st.query(nums[i] + gap - 1));
//             st.add(nums[i] + gap);
//         }
        int[] arr = init(nums);

        SegmentTree st = new SegmentTree(cnt);
        List<Integer> ans = new LinkedList<>();
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            // int a = nums[i] + gap;
            ans.add(0, st.query(arr[i] - 1));
            st.add(arr[i]);
        }
        return ans;
    }
    public static int cnt;
    public static int[] init(int[] arr){
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < arr.length; i ++){
            List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            map.put(arr[i], list);
        }
        int[] help = new int[arr.length];
        for(int i = 0; i < arr.length; i ++){
            help[i] = arr[i];
        }
        Arrays.sort(help);
        cnt = 1;
        int[] res = new int[arr.length];
        for(int i = 0; i < arr.length; i ++){
            if(i != 0 && help[i] == help[i - 1]){
                continue;
            }
            for(int j : map.get(help[i])){
                res[j] = cnt;
            }
            cnt ++;
        }
        return res;
    }

    public static class SegmentTree {
        public int[] arr;
        public int[] sum;
        public int[] lazy;
        public int size;

        public SegmentTree(int n) {
            size = n + 1;
            System.out.println(size);
            arr = new int[size << 2];
            sum = new int[size << 2];
            lazy = new int[size << 2];
        }

        public void pushUp(int rt) {
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
        }


        public void add(int index) {
            add(index, index, 1, 1, size, 1);
        }

        private void add(int l, int r, int c, int L, int R, int rt) {
            if (l <= L && R <= r) {
                sum[rt] += c;
                return;
            }
            int mid = (L + R) >> 1;
            if (mid >= l) {
                add(l, r, c, L, mid, rt << 1);
            }
            if (mid < r) {
                add(l, r, c, mid + 1, R, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public int query(int index) {
            if(index == 0){
                return 0;
            }
            System.out.println("1");
            return query(1, index, 1, size, 1);
        }

        private int query(int l, int r, int L, int R, int rt) {
            if (l <= L && R <= r) {
                return sum[rt];
            }

            int mid = (L + R) / 2;
            int ans = 0;
            if (mid >= l) {
                ans += query(l, r, L, mid, rt << 1);
            }
            if (mid < r) {
                ans += query(l, r, mid + 1, R, rt << 1 | 1);
            }
            return ans;
        }

    }


}
