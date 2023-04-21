package LeetCodeDays;

import java.util.Arrays;
import java.util.Stack;
import java.util.TreeMap;

public class LC2031CountSubarraysWithMoreOnesThanZeros {
    static int mod = (int) 1e9 + 7;

    public static int subarraysWithMoreZerosThanOnes(int[] nums) {
        int min = 0;
        int max = 0;
        int n = nums.length;
        for (int i : nums) {
            if (i == 0) {
                min++;
            } else {
                max++;
            }
        }
        IndexTree it = new IndexTree(max + min + 1);
        it.add(min, 1);
        long ans = 0;
        int pre = 0;
        for (int i = 0; i < n; i++) {
            pre += nums[i] == 0 ? -1 : 1;
            int to = pre + min;
            int curAns = it.sum(pre + min);
            ans += curAns;
            ans %= mod;
            it.add(pre + min, 1);
        }
        return (int) ans;
    }

    public static class IndexTree {
        public long[] arr;
        public int size;

        public IndexTree(int n) {
            size = n + 1;
            arr = new long[size];
        }


        public int sum(int index) {
            int res = 0;
            while (index > 0) {
                res += arr[index] % mod;
                index -= (index & -index);
            }
            return res;
        }

        public void add(int index, int num) {
            index++;
            while (index < size) {
                arr[index] += num;
                arr[index] %= mod;
                index += (index & -index);
            }
        }
    }

    public int subarraysWithMoreZerosThanOnes1(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int n = nums.length;
        int pre = 0;
        int[] preSum = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] == 0 ? -1 : 1;
        }
        for (int i = 1; i < n; i++) {
            preSum[i] = pre + nums[i];
            pre += nums[i];
        }
//        pre += nums[n - 1];
        Stack<Integer> stack = new Stack<>();
        int[] help = new int[n];
        stack.add(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            while (!stack.isEmpty() && preSum[stack.peek()] > preSum[i]) {
                help[stack.pop()] = i;
            }
            stack.add(i);
        }
        while (!stack.isEmpty()) {
            help[stack.pop()] = -1;
        }
        long ans = 0l;
        long[] dp = new long[n];
        for (int i = 0; i < n; i++) {
            int curAns = nums[i] == -1 ? 0 : 1;
            if (help[i] != -1) {
                curAns += dp[help[i]];
            }
            ans += curAns;
            dp[i] = curAns;
        }
        return (int) ans;
    }
}
