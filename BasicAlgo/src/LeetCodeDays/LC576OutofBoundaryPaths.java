package LeetCodeDays;

public class LC576OutofBoundaryPaths {
    public int mod = (int) 1e9 + 7;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if (maxMove == 0) {
            return 0;
        }
        int[][][] dp = new int[m][n][maxMove + 1];
        for (int i = 0; i < m; i++) {
            for (int k = 0; k <= maxMove; k++) {
                dp[i][0][k] += 1;
                dp[i][n - 1][k] += 1;
            }

        }
        for (int j = 0; j < n; j++) {
            for (int k = 0; k <= maxMove; k++) {
                dp[0][j][k] += 1;
                dp[m - 1][j][k] += 1;
            }
        }

        for (int k = 2; k <= maxMove; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j][k] += i > 0 ? (dp[i - 1][j][k - 1] % mod) : 0;
                    dp[i][j][k] %= mod;
                    dp[i][j][k] += j > 0 ? (dp[i][j - 1][k - 1] % mod) : 0;
                    dp[i][j][k] %= mod;
                    dp[i][j][k] += j < n - 1 ? (dp[i][j + 1][k - 1] % mod) : 0;
                    dp[i][j][k] %= mod;
                    dp[i][j][k] += i < m - 1 ? (dp[i + 1][j][k - 1] % mod) : 0;
                    dp[i][j][k] %= mod;
                }
            }
        }
        return dp[startRow][startColumn][maxMove];
    }

    public int findPaths1(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][][] dp = new int[m][n][maxMove + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k <= maxMove; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return process(m, n, maxMove, startRow, startColumn, dp);
    }

    public int process(int m, int n, int rest, int i, int j, int[][][] dp) {

        if (i < 0 || i >= m || j < 0 || j >= n) {
            return 1;
        }

        if (dp[i][j][rest] != -1) {
            return dp[i][j][rest];
        }
        int ans = 0;
        if (rest == 0) {
            ans = 0;
        } else {
            ans = (ans + process(m, n, rest - 1, i - 1, j, dp)) % mod;
            ans = (ans + process(m, n, rest - 1, i + 1, j, dp)) % mod;
            ans = (ans + process(m, n, rest - 1, i, j + 1, dp)) % mod;
            ans = (ans + process(m, n, rest - 1, i, j - 1, dp)) % mod;
        }
        dp[i][j][rest] = ans % mod;
        return dp[i][j][rest];
    }

}
