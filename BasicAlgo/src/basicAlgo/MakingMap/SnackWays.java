package basicAlgo.MakingMap;

import basicAlgo.DynamicProgramming.KillerMonster;

public class SnackWays {
    public static int way1(int[] arr, int w) {
        return process(arr, w, 0);
    }

    public static int process(int[] arr, int rest, int index) {
        if (rest < 0) {
            return -1;
        }
        if (index == arr.length) {
            return 1;
        }
        int p1 = process(arr, rest, index + 1);
        int p2 = process(arr, rest - arr[index], index + 1);
        return p1 + (p2 != -1 ? p2 : 0);
    }

    public static int way2(int[] arr, int w) {
        int N = arr.length;
        int[][] dp = new int[N + 1][w + 1];
        for (int j = 0; j <= w; j++) {
            dp[N][j] = 1;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= w; j++) {
                dp[i][j] = dp[i + 1][j] + ((j - arr[i] >= 0) ? dp[i + 1][j - arr[j]] : 0);
            }
        }
        return dp[0][w];
    }



}
