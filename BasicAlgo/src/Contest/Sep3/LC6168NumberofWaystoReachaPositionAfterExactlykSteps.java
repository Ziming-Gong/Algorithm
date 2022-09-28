package Contest.Sep3;

import basicAlgo.DynamicProgramming.KillerMonster;

public class LC6168NumberofWaystoReachaPositionAfterExactlykSteps {

    public int mod = (int) 1e9 + 7;

    public int numberOfWays(int startPos, int endPos, int k) {
        endPos -= startPos;
        startPos = 0;
        if (endPos > k) {
            return 0;
        }
        int[][] dp = new int[2 * k + 1][k + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = -1;
            }
        }
        return process(startPos, endPos, k, dp, k);
    }

    public int process(int cur, int end, int rest, int[][] dp, int k) {
        if (rest == 0) {
            return cur == end ? 1 : 0;
        }
        if (dp[cur + k][rest] != -1) {
            return dp[cur + k][rest];
        }
        int p1 = process(cur + 1, end, rest - 1, dp, k);
        int p2 = process(cur - 1, end, rest - 1, dp, k);

        dp[cur + k][rest] = (p1 + p2) % mod;
        return (p1 + p2) % mod;
    }
}
