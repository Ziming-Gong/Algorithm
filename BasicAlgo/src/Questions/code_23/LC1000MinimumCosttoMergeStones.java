package Questions.code_23;

import com.sun.xml.internal.bind.v2.runtime.reflect.Accessor;

import java.util.Properties;

public class LC1000MinimumCosttoMergeStones {
    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) > 0) {
            return -1;
        }
        int sum = 0;
        int[] preSum = new int[stones.length + 1];
        preSum[0] = 0;
        for (int i = 1; i <= stones.length; i++) {
            preSum[i] = preSum[i - 1] + stones[i - 1];
        }
        int[][][] dp = new int[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int p = 0; p <= k; p++) {
                    dp[i][j][p] = -1;
                }
            }
        }

        return f(stones, 0, stones.length - 1, k, 1, preSum, dp);


    }

    public int f(int[] stones, int l, int r, int k, int part, int[] preSum, int[][][] dp) {
        if (l == r) {
            return part == 1 ? 0 : -1;
        }
        if (dp[l][r][part] != -1) {
            return dp[l][r][part];
        }

        int ans = Integer.MAX_VALUE;

        if (part == 1) {
            int next = f(stones, l, r, k, k, preSum, dp);
            if (next == -1) {
                ans = -1;
            } else {
                ans = next + preSum[r + 1] - preSum[l];
            }
        } else {
            for (int i = l; i < r; i += k - 1) {
                int next1 = f(stones, l, i, k, 1, preSum, dp);
                int next2 = f(stones, i + 1, r, k, part - 1, preSum, dp);
                if (next1 != -1 && next2 != -1) {
                    ans = Math.min(ans, next1 + next2);
                }
            }
        }

        dp[l][r][part] = ans;
        return ans;
    }
}
