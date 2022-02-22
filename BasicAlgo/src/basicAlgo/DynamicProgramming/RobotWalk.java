package basicAlgo.DynamicProgramming;

import basicAlgo.mergesorted.ans;

public class RobotWalk {
    public static int ways1(int N, int start, int aim, int K) {
        return process1(start, K, N, aim);
    }

    public static int process1(int cur, int rest, int N, int aim) {
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            return process1(cur + 1, rest - 1, N, aim);
        }
        if (cur == N) {
            return process1(cur - 1, rest - 1, N, aim);
        }

        int p1 = process1(cur + 1, rest - 1, N, aim);
        int p2 = process1(cur - 1, rest - 1, N, aim);
        return p1 + p2;
    }

    public static int ways2(int N, int start, int aim, int K) {
        if (K < 1 || N < 2 || start < 1 || start > N || aim > N || aim < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        for (int row = 0; row <= N; row++) {
            for (int col = 0; col <= K; col++) {
                dp[row][col] = -1;
            }
        }
        return process2(start, K, aim, N, dp);
    }

    public static int process2(int cur, int rest, int aim, int N, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }


        int ans = 0;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(2, rest - 1, aim, N, dp);
        } else if (cur == N) {
            ans = process2(N - 1, rest - 1, aim, N, dp);
        } else {
            ans = process2(cur + 1, rest - 1, aim, N, dp) + process2(cur - 1, rest - 1, aim, N, dp);
        }

        dp[cur][rest] = ans;
        return ans;
    }

    public static int ways3(int N, int start, int aim, int K) {
        if (K < 1 || N < 2 || start < 1 || start > N || aim > N || aim < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }

        return dp[start][K];


    }


    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }

}
