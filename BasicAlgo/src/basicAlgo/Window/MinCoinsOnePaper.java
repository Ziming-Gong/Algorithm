package basicAlgo.Window;

import com.sun.scenario.effect.impl.prism.PrCropPeer;

import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public class MinCoinsOnePaper {
    public static int minCoins(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (rest < 0) {
            return Integer.MAX_VALUE;
        }
        //not use
        int p1 = process(arr, index + 1, rest);
        int p2 = process(arr, index + 1, rest - arr[index]);
        if (p2 != Integer.MAX_VALUE) {
            p2++;
        }
        return Math.min(p1, p2);
    }

    public static int dp1(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int j = 0; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        dp[N][0] = 0;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : Integer.MAX_VALUE;
                if (p2 != Integer.MAX_VALUE) {
                    p2++;
                }
                dp[index][rest] = Math.min(p1, p2);
            }
        }
        return dp[0][aim];
    }

    public static class Info {
        public int[] coins;
        public int[] nums;

        public Info(int[] c, int[] nums) {
            this.coins = c;
            this.nums = nums;
        }
    }

    public static Info getInfo(int[] coins) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < coins.length; i++) {
            if (!map.containsKey(coins[i])) {
                map.put(coins[i], 0);
            }
            map.put(coins[i], map.get(coins[i]) + 1);
        }
        int[] coin = new int[map.size()];
        int[] nums = new int[map.size()];
        int index = 0;
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            coin[index] = entry.getKey();
            nums[index++] = entry.getValue();
        }
        return new Info(coin, nums);
    }

    public static int dp2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] nums = info.nums;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                for (int i = 1; i <= nums[index] && coins[index] * i <= aim; i++) {
                    if (rest - coins[index] * i >= 0 && dp[index + 1][rest - coins[index] * i] != Integer.MAX_VALUE) {
                        dp[index][rest] = Math.min(dp[index][rest], i + dp[index + 1][rest - coins[index] * i]);
                    }
                }
            }
        }
        return dp[0][aim];
    }

    public static int dp3(int[] arr, int aim) {
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] nums = info.nums;
        int N = coins.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int j = 0; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        dp[N][0] = 0;
        for (int index = N - 1; index >= 0; index--) {
            for (int mod = 0; mod < Math.min(aim + 1, coins[index]); mod++) {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(mod);
                dp[index][mod] = dp[index + 1][mod];
                for (int rest = mod + coins[index]; rest <= aim; rest += coins[index]) {
                    while (!list.isEmpty() && (dp[index + 1][list.peekLast()] == Integer.MAX_VALUE || dp[index + 1][list.peekLast()]
                            + compensate(list.peekLast(), rest, coins[index]) >= dp[index + 1][rest])) {
                        list.pollLast();
                    }
                    list.addLast(rest);
                    int overdue = rest - coins[index] * (nums[index] + 1);
                    if(list.peekFirst() == overdue){
                        list.pollFirst();
                    }
                    dp[index][rest] = dp[index+1][list.peekFirst()]+ compensate(list.peekFirst(), rest, coins[index]);
                }
            }
        }
        return dp[0][aim];
    }

    public static int compensate(int pre, int cur, int coin) {
        return (cur - pre) / coin;
    }

    // 为了测试
    public static int[] randomArray(int N, int maxValue) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoins(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            int ans4 = dp3(arr, aim);
            if (ans1 != ans2 || ans3 != ans4 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
                break;
            }
        }
        System.out.println("功能测试结束");

        System.out.println("==========");

        int aim = 0;
        int[] arr = null;
        long start;
        long end;
        int ans2;
        int ans3;

        System.out.println("性能测试开始");
        maxLen = 30000;
        maxValue = 20;
        aim = 60000;
        arr = randomArray(maxLen, maxValue);

        start = System.currentTimeMillis();
        ans2 = dp2(arr, aim);
        end = System.currentTimeMillis();
        System.out.println("dp2答案 : " + ans2 + ", dp2运行时间 : " + (end - start) + " ms");

        start = System.currentTimeMillis();
        ans3 = dp3(arr, aim);
        end = System.currentTimeMillis();
        System.out.println("dp3答案 : " + ans3 + ", dp3运行时间 : " + (end - start) + " ms");
        System.out.println("性能测试结束");

        System.out.println("===========");

        System.out.println("货币大量重复出现情况下，");
        System.out.println("大数据量测试dp3开始");
        maxLen = 20000000;
        aim = 10000;
        maxValue = 10000;
        arr = randomArray(maxLen, maxValue);
        start = System.currentTimeMillis();
        ans3 = dp3(arr, aim);
        end = System.currentTimeMillis();
        System.out.println("dp3运行时间 : " + (end - start) + " ms");
        System.out.println("大数据量测试dp3结束");

        System.out.println("===========");

        System.out.println("当货币很少出现重复，dp2比dp3有常数时间优势");
        System.out.println("当货币大量出现重复，dp3时间复杂度明显优于dp2");
        System.out.println("dp3的优化用到了窗口内最小值的更新结构");
    }


}
