package LeetCodeDays;

import java.lang.annotation.Target;

public class SumWithMultiplicity_923 {

    // Time exceed

    public static int threeSumMulti(int[] arr, int target) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, target, 3);
    }

    public static int process(int[] arr, int index, int target, int rest) {
        if (index == arr.length) {
            return target == 0 && rest == 0 ? 1 : 0;
        }
        // if(rest == 0){
        //     return target == 0 ? 1 : Integer.MAX_VALUE;
        // }

        int p1 = process(arr, index + 1, target, rest);
        int p2 = process(arr, index + 1, target - arr[index], rest - 1);
        // int p2 = 0;
        // if(next != Integer.MAX_VALUE){
        //     p2 = next;
        // }

        return p1 + p2;
    }

    public static int dp1(int[] arr, int target) {
        int N = arr.length;
        int[][][] dp = new int[N + 1][target + 1][4];
        dp[N][0][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int aim = 0; aim <= target; aim++) {
                for (int rest = 1; rest <= 3; rest++) {
                    int p1 = dp[index + 1][aim][rest];
                    int p2 = 0;
                    if (aim - arr[index] >= 0) {
                        p2 = dp[index + 1][aim - arr[index]][rest - 1];
                    }
                    dp[index][aim][rest] = p1 + p2;
                }
            }
        }
        return dp[0][target][3];
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
        int target = 8;
        System.out.println(threeSumMulti(arr, target));
        System.out.println(dp1(arr, target));
    }
}
