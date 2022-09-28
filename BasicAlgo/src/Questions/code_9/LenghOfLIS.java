package Questions.code_9;

import basicAlgo.mergesorted.ans;

public class LenghOfLIS {
    public int lengthOfLIS(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        int[] end = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            int min = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    min = Math.max(min, dp[j]);
                }
            }
            dp[i] = min + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int lengthOfLIS1(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];
        int[] end = new int[N];
        int max = 0;
        int size = 0;
        for (int i = 0; i < N; i++) {
            int L = 0;
            int R = size;
            int min = 0;
            while (L < R) {
                int mid = (L + R) >> 1;
                if (end[mid] < nums[i]) {
                    L = mid + 1;
                    min = end[mid];
                } else {
                    R = mid - 1;
                }
            }
            dp[i] = min + 1;
            end[min] = i;
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
