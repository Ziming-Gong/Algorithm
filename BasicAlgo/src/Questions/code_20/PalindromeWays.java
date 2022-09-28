package Questions.code_20;

public class PalindromeWays {
    public static int ways(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 3 : 2;
        }
        dp[N - 1][N - 1] = 1;

        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                if (str[i] == str[j]) {
                    dp[i][j] += dp[i + 1][j - 1] + 1;
                }
            }
        }


        return dp[0][N - 1];
    }

    public static int ways2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] s = str.toCharArray();
        int n = s.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = s[i] == s[i + 1] ? 3 : 2;
        }
        for (int L = n - 3; L >= 0; L--) {
            for (int R = L + 2; R < n; R++) {
                dp[L][R] = dp[L + 1][R] + dp[L][R - 1] - dp[L + 1][R - 1];
                if (s[L] == s[R]) {
                    dp[L][R] += dp[L + 1][R - 1] + 1;
                }
            }
        }
        return dp[0][n - 1];
    }

    public static String randomString(int len, int types) {
        char[] str = new char[len];
        for (int i = 0; i < str.length; i++) {
            str[i] = (char) ('a' + (int) (Math.random() * types));
        }
        return String.valueOf(str);
    }



    public static void main(String[] args) {
        int N = 10;
        int types = 5;
        int testTimes = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int len = (int) (Math.random() * N);
            String str = randomString(len, types);
            int ans1 = ways(str);
            int ans2 = ways2(str);
            if (ans1 != ans2) {
                System.out.println(str);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
