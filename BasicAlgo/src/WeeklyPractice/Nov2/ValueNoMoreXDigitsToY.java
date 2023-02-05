package WeeklyPractice.Nov2;

// 来自CISCO
// 给定两个正整数x、y，都是int整型(java里)
// 返回0 ~ x以内，每位数字加起来是y的数字个数
// 比如，x = 20、y = 5，返回2
// 因为0 ~ x以内，每位数字加起来是5的数字有：5、14
// x、y范围是java里正整数的范围
// x <= 2 * 10^9
// y <= 90

import java.util.Arrays;

public class ValueNoMoreXDigitsToY {
    public static int[][] map = new int[11][91];

    // 在 i 位中， 相加和为 j 的个数有多少
    static {
        map[0][0] = 1;
        for (int len = 1; len < 11; len++) {
            for (int sum = 0; sum <= len * 9; sum++) {
                for (int cur = 0; cur <= 9 && cur <= sum; cur++) {
                    map[len][sum] += map[len - 1][sum - cur];
                }
            }
        }
    }

    public static int num1(int x, int y) {
        if (y > 90 || x < 0) {
            return 0;
        }
        if (x == 0) {
            return y == 0 ? 1 : 0;
        }
        int offset = 1;
        int len = 1;
        while (offset < x / 10) {
            offset *= 10;
            len++;
        }
        int[][] dp = new int[len + 1][y + 1];
        for (int[] i : dp) {
            Arrays.fill(i, -1);
        }
        return process(x, offset, len, y, dp);

    }

    public static int process(int x, int offset, int len, int rest, int[][] dp) {

        if (len == 0) {
            return rest == 0 ? 1 : 0;
        }
        if (dp[len][rest] != -1) {
            return dp[len][rest];
        }
        int cur = (x / offset) % 10;
        int cnt = 0;
        for (int i = 0; i < cur && i <= rest; i++) {
            cnt += map[len - 1][rest - i];
        }
        int next = 0;
        if (cur <= rest) {
            next = process(x, offset / 10, len - 1, rest - cur, dp);
        }
        cnt += next;
        dp[len][rest] = cnt;
        return cnt;


    }

    // for test
    public static int num2(int x, int y) {
        int ans = 0;
        for (int i = 0; i <= x; i++) {
            if (check1(i, y)) {
                ans++;
            }
        }
        return ans;
    }

    public static boolean check1(int num, int y) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum == y;
    }

    // 为了测试
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);

        int x = 743732432;
        int y = 57;
        System.out.println(num1(x, y));
        System.out.println(num2(x, y));
    }

}
