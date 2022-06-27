package WeeklyPractice.June15;

public class test {
    // n/1 + n/2 + n/3 + n/4 + ... + n/n -> O(N * logN)
    public static int countDifferentSubsequenceGCDs(int[] nums) {
        // 找到数组中的最大数！max
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        // 1~max，哪个数有哪个数没有
        boolean[] set = new boolean[max + 1];
        for (int num : nums) {
            set[num] = true;
        }
        int ans = 0;
        // a是当前想确定，是不是某个子序列的最大公约数，有a！
        for (int a = 1; a <= max; a++) {
            // 1)找到，离a最近的，a的倍数！1 2 3 ... g就是
            int g = a;
            for (; g <= max; g += a) {
                if (set[g]) {
                    break;
                }
            }
            // 2) 找到了a最近的倍数，g
            // g + 0 , g ?= a
            // g + a , g ?= a
            // g + 2a , g ?= a
            // g + 3a , g ?= a
            for (int b = g; b <= max; b += a) {
                if (set[b]) {
                    g = gcd(g, b);
                    if (g == a) {
                        ans++;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public static int gcd(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }
}
