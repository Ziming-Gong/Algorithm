package Questions.code_1;

//https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
public class LongestIncreasingPath {
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int ways = Integer.MIN_VALUE;
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                dp[i][j] = -1;
            }
        }
        for(int i = 0; i < n; i ++){
            for(int j = 0; j < m; j ++){
                ways = Math.max(ways, process(matrix, i, j, dp));
            }
        }
        return ways;
    }
    public int process(int[][] matrix, int n, int m, int[][] dp){
        if(dp[n][m] != -1){
            return dp[n][m];
        }

        int up = (n - 1) >= 0 && matrix[n-1][m] > matrix[n][m] ? process(matrix, n -1, m,dp) : 0;
        int down = (n + 1) < matrix.length && matrix[n + 1][m] > matrix[n][m] ?process( matrix, n + 1, m,dp) : 0;
        int left = (m - 1 )>= 0 && matrix[n][m-1] > matrix[n][m] ?  process(matrix, n, m - 1,dp) : 0;
        int right = (m + 1) < matrix[0].length && matrix[n][m + 1] > matrix[n][m] ? process(matrix, n, m + 1,dp) : 0;
        int ans = Math.max(Math.max(up,down), Math.max(left,right)) + 1 ;
        dp[n][m] = ans;
        return ans;
    }

}
