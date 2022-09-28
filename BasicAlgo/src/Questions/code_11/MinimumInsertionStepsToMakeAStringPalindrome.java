package Questions.code_11;

public class MinimumInsertionStepsToMakeAStringPalindrome {
    public int minInsertions(String s) {
        int N = s.length();
        int[][] dp = new int[N][N];
        for(int i = 0; i < N-1; i ++){
            dp[i][i+1] = s.charAt(i) == s.charAt(i + 1) ? 0 : 1;
        }
        for(int i = N - 3; i >= 0; i --){
            for(int j = i + 2; j < N; j ++){
                dp[i][j] = Math.min(dp[i+1][j], dp[i][j - 1]) + 1;
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i + 1][j - 1];
                }
            }
        }
        return dp[0][N - 1];
    }
}
