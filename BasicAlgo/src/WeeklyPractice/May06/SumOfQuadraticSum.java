package WeeklyPractice.May06;

import java.util.HashMap;
import java.util.HashSet;

// f(i) : i的所有因子，每个因子都平方之后，累加起来
// 比如f(10) = 1平方 + 2平方 + 5平方 + 10平方 = 1 + 4 + 25 + 100 = 130
// 给定一个数n，求f(1) + f(2) + .. + f(n)
// n <= 10的9次方
// O(n)的方法都会超时！低于它的！
// O(根号N)的方法，就过了，一个思路
// O(log N)的方法，
public class SumOfQuadraticSum {
    //打表测试
    public static void test(int nums) {
        int[] res = new int[nums + 1];
        for (int i = 1; i <= nums; i++) {
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    res[j]++;
                }
            }
        }
        for (int i = 1; i <= nums; i++) {
            System.out.println("因子 : " + i + ", 个数 : " + res[i]);
        }
    }

    public static void test1(int n) {
        int[] cnt = new int[n + 1];
        for (int num = 1; num <= n; num++) {
            for (int j = 1; j <= num; j++) {
                if (num % j == 0) {
                    cnt[j]++;
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.println("因子 : " + i + ", 个数 : " + cnt[i]);
        }
    }

    public static long sum1(long N) {
        int[] cnt = new int[(int) N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    cnt[j]++;
                }
            }
        }
        long ans = 0;
        for (long i = 1; i <= N; i++) {
            ans += i * i * (long) cnt[(int) i];
        }
        return ans;
    }

    public static long sum2(long N) {
        long sqrt = (long) Math.pow((double) N, 0.5);
        long ans = 0;
        for (int i = 1; i <= sqrt; i++) {
            ans += i * i * (N / i);
        }
        for (long i = N / (sqrt + 1); i >= 1; i--) {
            ans += sumOfLimitNumber(N, i);
        }
//        for (long i = sqrt - 1; i >= 1; i--) {
//            long left = N / i;
//            long right = N / i + 1;
//            ans += sumOfLimitNumber2(left,right,N)
//        }
        return ans;
    }

    public static long sumOfLimitNumber(long v, long n) {
        long r = cover(v, n);
        long l = cover(v, n + 1);
        return ((r * (r + 1) * ((r << 1) + 1)
                - l * (l + 1) * ((l << 1) + 1)) * n)
                / 6;
    }

    public static long cover(long v, long n) {
        long l = 1;
        long r = v;
        long m = 0;
        long ans = 0;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (m * n <= v) {
                l = m + 1;
                ans = m;
            } else {
                r = m - 1;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        test(200);
        test1(200);

//        System.out.println("测试开始");
//        for (long i = 1; i < 1000; i++) {
//            if (sum1(i) != sum2(i)) {
//                System.out.println("出错了!");
//            }
//        }
//        System.out.println("测试结束");
//
//        long n = 50000000000L; // 5 * 10的10次方
//        long start = System.currentTimeMillis();
//        sum2(n);
//        long end = System.currentTimeMillis();
//        System.out.println("大样本测试，n = " + n);
//        System.out.println("运行时间 : " + (end - start) + " ms");
    }
}
