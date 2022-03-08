package basicAlgo.DynamicProgramming;

public class KillerMonster {
    public static double right(int N, int M, int K) {
        if (N <= 0 || M <= 0 || K <= 0) {
            return 0;
        }
        return (double) process(N, M, K) / Math.pow(M + 1, K);
    }

    public static long process(int hp, int M, int rest) {
        if (rest == 0) {
            return hp <= 0 ? 1 : 0;
        }
        if (hp <= 0) {
            return (int) Math.pow(M + 1, rest);
        }
        int ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process(hp - i, M, rest - 1);
        }
        return ways;
    }

    public static double dp1(int N, int M, int K) {
        if (N <= 0 || M <= 0 || K <= 0) {
            return 0;
        }
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {

                long ways = 0;
                for (int i = 0; i <= M; i++) {
                    if (hp - i >= 0) {
                        ways += dp[times - 1][hp - i];
                    } else {
                        ways += (long) Math.pow(M + 1, times - 1);
                    }
                }
                dp[times][hp] = ways;
            }
        }
        return dp[K][N] / Math.pow(M + 1, K);
    }

    public static double dp2(int N, int M, int K) {
        if (N <= 0 || M <= 0 || K <= 0) {
            return 0;
        }
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                dp[times][hp] = dp[times - 1][hp] + dp[times][hp - 1];
                if (hp - 1 - M <= 0) {
                    dp[times][hp] -= (long) Math.pow(M + 1, times - 1);
                } else {
                    dp[times][hp] -= dp[times - 1][hp - 1 - M];
                }
            }
        }
        long kill = dp[K][N];
        long all = (long) Math.pow(M + 1, K);
        return (double) ((double) kill / (double) all);
    }


    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = right(N, M, K);
            double ans2 = dp1(N, M, K);
            double ans3 = dp2(N, M, K);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("test all");
    }


}
