package basicAlgo.QuadrangleInequality;

import java.util.Arrays;

public class PostOfficeProblem {
    public static int min1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //w[][] 是辅助表，w[i][j]意义是i....j中有一邮局的总距离是多少
        int n = arr.length;
        int[][] w = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                w[i][j] = w[i][j - 1] + arr[j] - arr[(j + i) >> 1];
            }
        }
        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            dp[i][1] = w[0][i];
        }
        for (int j = 2; j <= k; j++) {
            for (int i = j; i < n; i++) {
                int ans = Integer.MAX_VALUE;
                for (int range = 0; range < i; range++) {
                    ans = Math.min(ans, dp[range][j - 1] + w[range + 1][i]);
                }
                dp[i][j] = ans;
            }
        }
        return dp[n - 1][k];
    }

    public static int min2(int[] arr, int k) {
        if (arr == null || k < 1 || arr.length < k) {
            return 0;
        }
        int n = arr.length;
        int[][] w = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                w[i][j] = w[i][j - 1] + arr[j] - arr[(j + i) >> 1];
            }
        }
        int[][] dp = new int[n][k + 1];
        int[][] best = new int[n][k + 1];
        for (int i = 1; i < n; i++) {
            dp[i][1] = w[0][i];
            best[i][1] = -1; //i >> 1;
        }
        for (int j = 2; j <= k; j++) {
            for (int i = n - 1; i >= j; i--) {
                int up = i == n - 1 ? n - 1 : best[i + 1][j];
                int down = best[i][j - 1];
                int ans = Integer.MAX_VALUE;
                int choose = -1;
                for (int range = down; range <= up; range++) {
                    int left = range == -1 ? dp[0][j - 1] : dp[range][j - 1];
                    int right = w[range + 1][i];
                    if (ans >= left + right) {
                        ans = left + right;
                        choose = range;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = choose;
            }
        }
        return dp[n - 1][k];
    }

    public static void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.printf(arr[i][j] + ", ");
            }
            System.out.println();
        }
    }

    // for test
    public static int[] randomSortedArray(int len, int range) {
        int[] arr = new int[len];
        for (int i = 0; i != len; i++) {
            arr[i] = (int) (Math.random() * range);
        }
        Arrays.sort(arr);
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int N = 30;
        int maxValue = 100;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N) + 1;
            int[] arr = randomSortedArray(len, maxValue);
            int num = (int) (Math.random() * N) + 1;
            int ans1 = min1(arr, num);
            int ans2 = min2(arr, num);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(num);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");

    }


}
