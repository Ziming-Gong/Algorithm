package Questions.code_17;


//https://leetcode.com/problems/distinct-subsequences/
public class LC115DistinctSubsequences {
    public int numDistinct(String s, String t) {
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        int n = str1.length;
        int m = str2.length;
        int[][] dp = new int[n][m];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for(int i = 1; i < n; i ++){
            dp[i][0] = dp[i - 1][0] + (str1[i] == str2[0] ? 1 : 0);
        }
        for(int i = 1; i < n ; i ++){
            for(int j = 1; j <= Math.min(i, m - 1); j ++){
                dp[i][j] = dp[i - 1][j];
                if(str1[i] == str2[j]){
                    dp[i][j] += dp[i - 1][ j - 1];
                }
            }
        }
        return dp[n - 1][m - 1];

    }
}
