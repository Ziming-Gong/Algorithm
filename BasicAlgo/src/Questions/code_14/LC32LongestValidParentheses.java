package Questions.code_14;

public class LC32LongestValidParentheses {
    public int longestValidParentheses(String s) {
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N];
        int ans = 0;
        for (int i = 1; i < N; i++) {
            if (str[i] == ')') {
                int pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str[pre] == '(') {
                    dp[i] = i - pre + 1;
                    if (pre - 1 > 0) {
                        dp[i] += dp[pre - 1];
                    }
                }

            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
