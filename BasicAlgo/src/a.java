import Questions.code_7.MinCamera;

import java.util.Stack;

public class a {
    public int maxProfit(int[] arr) {
        if (arr.length < 3) {
            return 0;
        }
        int N = arr.length;
        int[] dp = new int[N];
        dp[N - 1] = arr[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            dp[i] = Math.max(arr[i], dp[i + 1]);
        }
        int ans = 0;
        for (int i = 0; i < N - 2; i++) {
            ans = Math.max(ans, dp[i + 2] - arr[i]);
        }
        Stack<Integer> stack = new Stack<>();
        return ans;
    }
}
