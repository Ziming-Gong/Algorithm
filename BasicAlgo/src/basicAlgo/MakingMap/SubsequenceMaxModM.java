package basicAlgo.MakingMap;


import basicAlgo.IndexTree.IndexTree_Code;
import basicAlgo.mergesorted.ans;

import javax.print.DocFlavor;
import java.util.HashSet;
import java.util.TreeSet;

// 给定一个非负数组arr，和一个正数m。 返回arr的所有子序列中累加和%m之后的最大值。
public class SubsequenceMaxModM {
    //当m超大，arr中值不大
    public static int max1(int[] arr, int m) {
        HashSet<Integer> set = new HashSet<>();
        process(arr, 0, set, 0);
        int max = 0;
        for (Integer sum : set) {
            max = Math.max(max, sum % m);
        }
        return max;
    }

    public static void process(int[] arr, int index, HashSet<Integer> set, int sum) {
        if (index == arr.length) {
            set.add(sum);
        } else {
            process(arr, index + 1, set, sum + arr[index]);
            process(arr, index + 1, set, sum);
        }
    }

    //max1 DP
    public static int max2(int[] arr, int m) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        int N = arr.length;
        boolean[][] dp = new boolean[N][sum + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0]] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - arr[i] >= 0) {
                    dp[i][j] |= dp[i - 1][j - arr[i]];
                }
            }
        }
        int max = 0;
        for (int j = 0; j <= sum; j++) {
            if (dp[N - 1][j]) {
                max = Math.max(max, j % m);
            }
        }
        return max;
    }

    //当m不大， arr中值超大
    public static int max3(int[] arr, int m) {
        int N = arr.length;
        boolean[][] dp = new boolean[N][m];
        for (int i = 0; i < N; i++) {
            dp[i][0] = true;
        }
        dp[0][arr[0] % m] = true;
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i - 1][j];
                int x = arr[i] % m;
                if (x <= j) {
                    dp[i][j] |= dp[i - 1][j - x];
                } else {
                    dp[i][j] |= dp[i - 1][m + j - x];
                }
            }
        }
        int ans = 0;
        for (int j = m - 1; j >= 0; j--) {
            if (dp[N - 1][j]) {
                ans = j;
                break;
            }
        }
        return ans;
    }


    //如果arr的累加合很大， m也很大
    // 但是arr.length 不大
    //采用分治
    public static int max4(int[] arr, int m) {
        if (arr.length == 1) {
            return arr[0] % m;
        }
        int mid = (arr.length - 1) / 2;
        TreeSet<Integer> set1 = new TreeSet<>();
        process4(arr, 0, mid, 0, set1, m);
        TreeSet<Integer> set2 = new TreeSet<>();
        process4(arr, mid + 1, arr.length - 1, 0, set2, m);
        int ans = 0;
        for (Integer left : set1) {
            ans = Math.max(ans, left + (set2.floor(m - 1 - left)));
        }
        return ans;
    }

    public static void process4(int[] arr, int begin, int end, int sum, TreeSet<Integer> set, int m) {
        if (begin > end) {
            set.add(sum % m);
        } else {
            process4(arr, begin + 1, end, sum, set, m);
            process4(arr, begin + 1, end, sum + arr[begin], set, m);
        }

    }


    public static int[] generateRandomArray(int len, int value) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value);
        }
        return ans;
    }

    public static void main(String[] args) {
        int len = 10;
        int value = 100;
        int m = 76;
        int testTime = 500000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(len, value);
            int ans1 = max1(arr, m);
            int ans2 = max2(arr, m);
            int ans3 = max3(arr, m);
            int ans4 = max4(arr, m);
            if (ans1 != ans2 || ans2 != ans3 || ans3 != ans4) {
                System.out.println("Oops!");
            }
//            if (ans1 != ans2 || ans1 != ans3) {
//                System.out.println("Oops!");
//            }
        }
        System.out.println("test finish!");

    }
}
