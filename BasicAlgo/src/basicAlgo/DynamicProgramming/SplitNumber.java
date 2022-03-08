package basicAlgo.DynamicProgramming;

public class SplitNumber {
    public static int ways(int test) {
        return process(1, test);
    }

    public static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }

        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int i = pre; i <= rest; i++) {
            ways += process(i, rest - i);
        }
        return ways;
    }

    public static int dp1(int test) {
        int[][] dp = new int[test + 1][test + 1];
        for (int i = 0; i <= test; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }

        for (int pre = test - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= test; rest++) {
                for (int i = pre; i <= rest; i++) {
                    dp[pre][rest] += dp[i][rest - i];
                }
            }
        }
        return dp[1][test];
    }

    public static int dp2(int n) {
        int[][] dp = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for(int pre = n -1 ; pre >= 1; pre --){
            for(int rest = pre + 1; rest <= n; rest ++){
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest -pre];
            }
        }
        return dp[1][n];
    }


    public static void main(String[] args) {
        int test = 100;
        System.out.println(ways(test));
        System.out.println(dp1(test));
        System.out.println(dp2(test));
    }
}
