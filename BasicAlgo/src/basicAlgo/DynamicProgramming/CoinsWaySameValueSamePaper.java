package basicAlgo.DynamicProgramming;

import java.util.HashMap;
import java.util.Map.Entry;

public class CoinsWaySameValueSamePaper {
    public static class Info {
        public int[] coins;
        public int[] nums;

        public Info(int[] coins, int[] nums) {
            this.coins = coins;
            this.nums = nums;
        }
    }

    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 0);
            }
            map.put(arr[i], map.get(arr[i]) + 1);
        }
        int[] nums = new int[map.size()];
        int[] coins = new int[map.size()];
        int index = 0;
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            nums[index] = entry.getKey();
            coins[index++] = entry.getValue();
        }
        return new Info(coins, nums);
    }

    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        return process(info.coins, info.nums, 0, aim);
    }

    public static int process(int[] coins, int[] nums, int index, int aim) {
        if (index == coins.length) {
            return aim == 0 ? 1 : 0;
        }
        if (aim < 0) {
            return 0;
        }
        int ways = 0;
        for (int i = 0; i * coins[index] <= aim && i <= nums[index]; i++) {
            ways += process(coins, nums, index + 1, aim - (i * coins[index]));
        }
        return ways;
    }

    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] nums = info.nums;
        int n = coins.length;
        int[][] dp = new int[n + 1][aim + 1];
        dp[n][0] = 1;
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int i = 0; i * coins[index] <= rest && i <= nums[index]; i++) {
                    if (rest - (i * coins[index]) >= 0) {
                        ways += dp[index + 1][rest - (i * coins[index])];
                    }
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] nums = info.nums;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest] + (rest - coins[index] >= 0 ? dp[index][rest - coins[index]] : 0)
                        - (rest - (nums[index] + 1) * coins[index] >= 0 ? dp[index + 1][rest - (nums[index] + 1) * coins[index]] : 0);
//                dp[index][rest] = dp[index + 1][rest];
//                if (rest - coins[index] >= 0) {
//                    dp[index][rest] += dp[index][rest - coins[index]];
//                }
//                if (rest - coins[index] * (nums[index] + 1) >= 0) {
//                    dp[index][rest] -= dp[index + 1][rest - coins[index] * (nums[index] + 1)];
//                }
            }
        }
        return dp[0][aim];

    }

    // for test
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 200000;
        System.out.println("test begins");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("test ends");
    }


}
