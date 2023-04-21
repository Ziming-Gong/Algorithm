package Contest.Feb182023;

import javax.naming.InitialContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LC2572CounttheNumberofSquareFreeSubsets {

    // 30以内的质数 ： 2，3，7，11，13，17，19，23，29

    public static int mod = (int) 1e9 + 7;
    public static HashMap<Integer, Integer> map;
    public static HashSet<Integer> set;
    public static int[][] dp;

    public static int squareFreeSubsets(int[] nums) {
        init();
        int r = filer(nums);
        int tempr = filerOne(nums, r);
        int num = r - tempr;
        int n = nums.length;
        int m = (1 << 11);
        r = tempr + 1;
        dp = new int[r + 1][m];
        for (int i = 0; i <= r; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }
        System.out.println(r);

        int ans1 = process(nums, 0, 0, dp, r);
        System.out.print(ans1);
        long ans2 = ans2(num);
        long ans = (((ans2 * ans1) % mod) + ans2 - 1) % mod;
        return (int) ans;


    }

    public static long ans2(int num) {
        long ans = 1;
        int t = 2;
        while (num > 0) {
            if ((num & 1) == 1) {
                ans *= t;
                ans %= mod;
            }
            t *= t;
            t %= mod;
            num >>= 1;
        }
        return  ans;
    }

    public static int filerOne(int[] arr, int r) {
        int l = 0;
        while (l < r) {
            if (arr[l] == 1) {
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r--] = temp;
            } else {
                l++;
            }
        }
        return r;
    }

    public static int filer(int[] arr) {
        int r = arr.length - 1;
        int l = 0;
        while (l < r) {
            if (set.contains(arr[l])) {
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r--] = temp;
            } else {
                l++;
            }
        }
        return r;
    }


    public static int process(int[] arr, int index, int cur, int[][] dp, int end) {
        if (index == end) {
            return cur == 0 ? 0 : 1;
        }
        if (dp[index][cur] != -1) {
            return dp[index][cur];
        }
        int p1 = process(arr, index + 1, cur, dp, end) % mod;
        int[] prime = get(arr[index]);
        int add = 0;
        boolean can = true;
        for (int i : prime) {
            if (i != 0) {
                if ((add & (1 << map.get(i))) != 0 && (add & (1 << map.get(i))) != (1 << 10)) {
                    can = !can;
                    break;
                }
                add += (1 << map.get(i));
            }
        }
        if (((cur & add) == 0 || (cur & add) == (1 << 10)) && can) {
            if (((cur & add) == (1 << 10))) {
                cur ^= (1 << 10);
            }
            p1 += process(arr, index + 1, cur + add, dp, end) % mod;
        }
        p1 %= mod;
        dp[index][cur] = p1;
        return p1;

    }

    public static int[] get(int num) {
        int[] ans = new int[5];
        int index = 0;
        ans[index++] = 1;
        for (int i = 2; num != 1; i++) {
            if ((num % i) == 0) {
                while (num % i == 0) {
                    num /= i;
                    ans[index++] = i;
                }
            }
        }
        return ans;
    }

    public static void init() {
        map = new HashMap<>();
        set = new HashSet<>();
        map.put(2, 0);
        map.put(3, 1);
        map.put(5, 2);
        map.put(7, 3);
        map.put(11, 4);
        map.put(13, 5);
        map.put(17, 6);
        map.put(19, 7);
        map.put(23, 8);
        map.put(29, 9);
        map.put(1, 10);
        set.add(4);
        set.add(9);
        set.add(8);
        set.add(12);
        set.add(16);
        set.add(18);
        set.add(20);
        set.add(24);
        set.add(25);
        set.add(28);
    }

}
















