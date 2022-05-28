package basicAlgo.QuadrangleInequality;

import java.sql.ResultSet;

public class StoneMerge {
    public static int[] sum(int[] arr) {
        int N = arr.length;
        int[] s = new int[N + 1];
        s[0] = 0;
        for (int i = 0; i < N; i++) {
            s[i + 1] = s[i] + arr[i];
        }
        return s;
    }

    public static int w(int[] s, int l, int r) {
        return s[r + 1] - s[l];
    }

    public static int min1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int[] s = sum(arr);
        return process1(0, N - 1, s);
    }

    public static int process1(int L, int R, int[] s) {
        if (L == R) {
            return 0;
        }
        int next = Integer.MAX_VALUE;
        for (int leftEnd = L; leftEnd < R; leftEnd++) {
            next = Math.min(next, process1(L, leftEnd, s) + process1(leftEnd + 1, R, s));
        }
        return next + w(s, L, R);
    }


    public static int sum(int[] arr, int l, int r) {
        return arr[r + 1] - arr[l];
    }

    public static int min2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n][n];
        int[] sum = new int[n + 1];
        for (int i = 0; i < arr.length; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                int next = Integer.MAX_VALUE;
                //    arr [i-1 .... j]中最小的代价
                for (int leftEnd = i; leftEnd < j; leftEnd++) {
                    next = Math.min(next, dp[i][leftEnd] + dp[leftEnd + 1][j]);
                }
                dp[i][j] = next + sum(sum, i, j);
            }
        }
        return dp[0][n - 1];
    }

    public static int min3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] sum = sum(arr);
        int n = arr.length;
        int[][] dp = new int[n][n];
        int[][] best = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = sum(sum, i, i + 1);
            best[i][i + 1] = i;
        }
        for (int L = n - 3; L >= 0; L--) {
            for (int R = L + 2; R < n; R++) {
                int next = Integer.MAX_VALUE;
                int bestChoose = -1;
                for (int leftEnd = best[L][R - 1]; leftEnd <= best[L + 1][R]; leftEnd++) {
                    if (next >= dp[L][leftEnd] + dp[leftEnd + 1][R]) {
                        bestChoose = leftEnd;
                        next = dp[L][leftEnd] + dp[leftEnd + 1][R];
                    }
                }
                best[L][R] = bestChoose;
                dp[L][R] = next + w(sum, L, R);
            }
        }
        return dp[0][n - 1];

    }


    public static int[] randomArray(int len, int maxValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 15;
        int maxValue = 100;
        int testTime = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, maxValue);
            int ans1 = min1(arr);
            int ans2 = min2(arr);
            int ans3 = min3(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
