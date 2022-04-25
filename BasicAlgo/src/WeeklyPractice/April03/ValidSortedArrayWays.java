package WeeklyPractice.April03;

import basicAlgo.DynamicProgramming.KillerMonster;

import java.util.Arrays;

public class ValidSortedArrayWays {

    public static long ways1(int[] nums, int k) {
        int n = nums.length;
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][1] = 1;
        }
        for (int i = 1; i <= k; i++) {
            dp[1][i] = i;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        long ans = 1;
        for (int i = 0,  j = 0; i < n; i++) {
            if (nums[i] == 0) {
                int startVal = i - 1 < 0 ? 1 : nums[i - 1];
                j = i + 1;
                while (j < n && nums[j] == 0) {
                    j++;
                }
                int endVal = j < n ? nums[j] : k;
                ans *= dp[j - i][endVal - startVal + 1];
                i = j;
            }
        }
        return ans;

    }


    /////////
    public static long ways2(int[] nums, int k) {
        long res = 1;
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                j = i + 1;
                while (j < nums.length && nums[j] == 0) {
                    j++;
                }
                int leftValue = i - 1 >= 0 ? nums[i - 1] : 1;
                int rightValue = j < nums.length ? nums[j] : k;
                int numbers = j - i;
                res *= c(rightValue - leftValue + numbers, numbers);
                i = j;
            }
        }
        return res;
    }

    // 从一共a个数里，选b个数，方法数是多少
    public static long c(int a, int b) {
        if (a == b) {
            return 1;
        }
        long x = 1;
        long y = 1;
        for (int i = b + 1, j = 1; i <= a; i++, j++) {
            x *= i;
            y *= j;
            long gcd = gcd(x, y);
            x /= gcd;
            y /= gcd;
        }
        return x / y;
    }

    public static long gcd(long m, long n) {
        return n == 0 ? m : gcd(n, m % n);
    }


    // for test
    public static int[] randomArray(int n, int k) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * k) + 1;
        }
        Arrays.sort(ans);
        for (int i = 0; i < n; i++) {
            ans[i] = Math.random() < 0.5 ? 0 : ans[i];
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int N = 20;
        int K = 30;
        int testTimes = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int k = (int) (Math.random() * K) + 1;
            int[] arr = randomArray(n, k);
            long ans1 = ways1(arr, k);
            long ans2 = ways2(arr, k);
            if (ans1 != ans2) {
                System.out.println("出错了");
                for (int num : arr) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println(ans1);
                System.out.println(ans2);
            }
        }
        System.out.println("测试结束");
    }
}
