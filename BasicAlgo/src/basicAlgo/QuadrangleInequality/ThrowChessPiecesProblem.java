package basicAlgo.QuadrangleInequality;

import org.w3c.dom.ranges.Range;

import java.util.Arrays;

// leetcode测试链接：https://leetcode.com/problems/super-egg-drop
// 方法1和方法2会超时
// 方法3勉强通过
// 方法4打败100%
// 方法5打败100%，方法5是在方法4的基础上做了进一步的常数优化
public class ThrowChessPiecesProblem {

    /**
     * @param k 个棋子
     * @param n 层
     * @return 最少需要的步数
     */
    public static int superEggDrop1(int k, int n) {
        if (k < 1 || n < 1) {
            return 0;
        }
        return process(k, n);
    }

    public static int process(int restK, int restN) {
        if (restK == 0) {
            return 0;
        }
        if (restK == 1) {
            return restN;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= restK; i++) {
            int b = process(i - 1, restN - 1);
            int a = process(restK - i, restN);
            min = Math.min(min, Math.max(a, b));
        }
        return min + 1;
    }

    /**
     * 上面方法的DP没有优化
     *
     * @param k 棋子
     * @param n 层数
     * @return
     */
    public static int superEggDrop2(int k, int n) {
        if (k < 1 || n < 1) {
            return 0;
        }
        if (k == 1) {
            return n;
        }
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
        }
        for (int j = 2; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                int min = Integer.MAX_VALUE;
                for (int range = 1; range <= i; range++) {
                    min = Math.min(min, Math.max(dp[range - 1][j - 1], dp[i - range][j]));
                }
                dp[i][j] = min + 1;
            }
        }
        return dp[n][k];
    }

    /**
     * 四边型不等式
     *
     * @param k
     * @param n
     * @return
     */
    public static int superEggDrop3(int k, int n) {
        if (k < 1 || n < 1) {
            return 0;
        }

        if (k == 1) {
            return n;
        }
        int[][] best = new int[n + 1][k + 1];
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][1] = i;
        }
        for (int j = 1; j <= k; j++) {
            dp[1][j] = 1;
            best[1][j] = 1;
        }
        // print(dp);
        // System.out.println();
        // print(best);

        for (int i = 2; i <= n; i++) {
            for (int j = k; j >= 2; j--) {
                int min = Integer.MAX_VALUE;
                int choose = -1;
                int down = best[i - 1][j];
                int up = j == k ? i : best[i][j + 1];
                for (int range = down; range <= up; range++) {
                    int left = dp[range - 1][j - 1];
                    int right = dp[i - range][j];
                    int cur = Math.max(left, right);
                    if (cur <= min) {
                        min = cur;
                        choose = range;
                    }
                }
                dp[i][j] = min + 1;
                best[i][j] = choose;
            }
        }
        return dp[n][k];
    }

    public void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int superEggDrop4(int k, int n) {
        if (k < 1 || n < 1) {
            return 0;
        }

        if (k == 1) {
            return n;
        }
        int bsTimes = log2N(n) + 1;
        if (k >= bsTimes) {
            return bsTimes;
        }
        int[] arr = new int[k + 1];
        Arrays.fill(arr, 1);
        int res = 1;
        while (true) {
            for (int i = k; i >= 2; i--) {
                arr[i] = arr[i-1] + arr[i] + 1;
                if (arr[i] >= n) {
                    return ++res;
                }
            }
            arr[1] = ++res;
        }
    }
    public static int log2N(int n) {
        int res = -1;
        while (n != 0) {
            res++;
            n >>>= 1;
        }
        return res;
    }


}
