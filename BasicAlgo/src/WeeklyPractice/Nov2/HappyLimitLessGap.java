package WeeklyPractice.Nov2;

// 来自蚂蚁金服
// 小红有n个朋友, 她准备开个宴会，邀请一些朋友
// i号朋友的愉悦值为a[i]，财富值为b[i]
// 如果两个朋友同时参加宴会，这两个朋友之间的隔阂是其财富值差值的绝对值
// 宴会的隔阂值，是财富差距最大的两人产生的财富值差值的绝对值
// 宴会的愉悦值，是所有参加宴会朋友的愉悦值总和
// 小红可以邀请任何人，
// 希望宴会的愉悦值不能小于k的情况下， 宴会的隔阂值能最小是多少
// 如果做不到，返回-1
// 1 <= n <= 2 * 10^5
// 1 <= 愉悦值、财富值 <= 10^9
// 1 <= k <= 10^14

import java.util.Arrays;

public class HappyLimitLessGap {

    public static int lessGap1(int[] happy, int[] money, int k) {
        int n = happy.length;
        int[][] matrix = new int[n][2];
        int[] minMax = createMatrix(matrix, happy, money);

        int l = 0;
        int r = minMax[1] - minMax[0];

        int ans = -1, mid;

        while (l <= r) {
            mid = (l + r) >> 1;
            if (maxHappy1(matrix, mid) >= k) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    public static long maxHappy1(int[][] f, int limit) {
        int l = 0, r = 1;
        int cnt = f[0][0];
        int ans = f[0][0];
        while (r < f.length) {
            while (r < f.length && f[r][1] - f[l][1] <= limit) {
                cnt += f[r++][0];
            }
            ans = Math.max(ans, cnt);
            cnt -= f[l++][0];
            r = Math.max(l, r);
        }
        return ans;
    }

    public static int[] createMatrix(int[][] matrix, int[] happy, int[] money) {
        int n = happy.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            matrix[i][0] = happy[i];
            matrix[i][1] = money[i];
            min = Math.min(money[i], min);
            max = Math.max(money[i], max);
        }
        Arrays.sort(matrix, (a, b) -> (a[1] - b[1]));
        return new int[]{min, max};
    }

    // 正式方法
    // 二分答案
    public static int lessGap2(int[] a, int[] b, long k) {
        int n = a.length;
        //  a : 20  30 17
        //  b :  5  10 36
        //       0   1  2
        // [ 20, 5]  [30, 10]  [17, 36]
        //  0         1         2
        int[][] f = new int[n][2];
        int min = b[0];
        int max = b[0];
        for (int i = 0; i < n; i++) {
            f[i][0] = a[i];
            f[i][1] = b[i];
            min = Math.min(min, b[i]);
            max = Math.max(max, b[i]);
        }
        // 排序和大流程，没关系
        // 是子函数，maxHappy函数，需要用到，排了序
        // 根据财富排序，少 ->  多
        Arrays.sort(f, (x, y) -> x[1] - y[1]);
        // 隔阂值的范围 l ~  r
        int l = 0;
        int r = max - min;
        int m = 0;
        int ans = -1;
        while (l <= r) {
            // 0.........50
            //     25
            m = (l + r) / 2;
            if (maxHappy(f, m) >= k) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans;
    }

    public static long maxHappy(int[][] f, int limit) {
        int n = f.length;
        long sum = 0;
        long ans = 0;
        for (int l = 0, r = 0; l < n; l++) {
            while (r < n && f[r][1] - f[l][1] <= limit) {
                sum += f[r++][0];
            }
            ans = Math.max(ans, sum);
            sum -= f[l][0];
            r = Math.max(r, l + 1);
        }
        return ans;
    }

    public static int[] randomArray(int n, int v) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * v) + 1;
        }
        return arr;
    }

    // 为了验证
    public static void main(String[] args) {
        int N = 15;
        int V = 20;
        int testTime = 5000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] a = randomArray(n, V);
            int[] b = randomArray(n, V);
            int k = (int) (Math.random() * n * V) + 1;
            int ans1 = lessGap1(a, b, k);
            int ans2 = lessGap2(a, b, k);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
        }
        System.out.println("功能测试结束");
    }


}
