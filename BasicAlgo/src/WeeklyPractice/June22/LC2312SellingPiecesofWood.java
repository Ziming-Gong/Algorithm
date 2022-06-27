package WeeklyPractice.June22;


import java.io.PipedWriter;
import java.util.function.LongToDoubleFunction;

//https://leetcode.com/problems/selling-pieces-of-wood/
public class LC2312SellingPiecesofWood {
    public long sellingWood(int m, int n, int[][] prices) {
        long[][] values = new long[m + 1][n + 1];
        for (int[] cur : prices) {
            values[cur[0]][cur[1]] = cur[2];
        }
        long[][] dp = new long[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }
        return process(m, n, values, dp);
    }

    public long process(int m, int n, long[][] values, long[][] dp) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (dp[m][n] != -1) {
            return dp[m][n];
        }
        long ans = values[m][n];

        for (int split = 1; split < m; split++) {
            long p1 = process(split, n, values, dp);
            long p2 = process(m - split, n, values, dp);
            ans = Math.max(ans, p1 + p2);
        }

        for (int split = 1; split < n; split++) {
            long p1 = process(m, split, values, dp);
            long p2 = process(m, n - split, values, dp);
            ans = Math.max(ans, p1 + p2);
        }
        dp[m][n] = ans;
        return ans;
    }

    public long sellingWood1(int m, int n, int[][] prices) {
        long[][] dp = new long[m + 1][n + 1];
        for (int[] cur : prices) {
            dp[cur[0]][cur[1]] = cur[2];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= i >> 1; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }
                for (int k = 1; k <= j >> 1; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
                }
            }
        }
        return dp[m][n];


    }


}
