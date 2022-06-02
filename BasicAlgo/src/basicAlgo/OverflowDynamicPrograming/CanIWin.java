package basicAlgo.OverflowDynamicPrograming;


//https://leetcode.com/problems/can-i-win/
public class CanIWin {

    public static boolean canIWin0(int choose, int total) {
        if (total == 0) {
            return true;
        }
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }
        int[] arr = new int[choose];
        for (int i = 0; i < choose; i++) {
            arr[i] = i + 1;
        }
        return process(arr, total);
    }


    public static boolean process(int[] arr, int rest) {
        if (rest <= 0) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != -1) {
                int cur = arr[i];
                arr[i] = -1;
                boolean next = process(arr, rest - cur);
                arr[i] = cur;
                if (!next) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean canIWin(int choose, int total) {
        if (total == 0) {
            return true;
        }
        if ((choose * (choose + 1) >> 1) < total) {
            return false;
        }
        int[] dp = new int[1 << (choose + 1)];
//        dp[] = 0 未使用， 1：true -1: false
        return process1(dp, choose, total, 0);
    }

    public static boolean process1(int[] dp, int choose, int total, int status) {
        if (total <= 0) {
            dp[status] = -1;
            return false;
        }
        if (dp[status] != 0) {
            return dp[status] == 1? true : false;
        }
        for (int i = 1; i <= choose; i++) {
            if (((1 << i) & status) == 0) {
                boolean cur = process1(dp, choose, total - i, status | (1 << i));
                if (!cur) {
                    dp[status] = 1;
                    return true;
                }
            }

        }
        dp[status] = -1;
        return false;
    }



}
