package basicAlgo.DPAdvanced;

//https://leetcode.com/problems/strange-printer/
public class StrangerPrinter {
     public int strangePrinter0(String s) {
         char[] str = s.toCharArray();
         return process(str,0,str.length - 1);
     }
     public int process(char[] str, int L, int R){
         if(L == R){
             return 1;
         }
         int min = Integer.MAX_VALUE;
         for(int k = L + 1; k <= R; k ++){
             min = Math.min(min, process(str,L,k-1)+process(str,k,R)-(str[L] == str[k] ? 1 : 0));
         }
         return min;
     }

    public int strangePrinter1(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i ++){
            dp[i][i] = 1;
        }
        for(int i = n - 2; i >= 0; i --){
            for(int j = i + 1; j < n; j ++){
                int ans = Integer.MAX_VALUE;
                for(int k = i + 1; k <= j; k ++){
                    ans = Math.min(ans, dp[i][k-1] + dp[k][j] - (str[i] == str[k] ? 1 : 0));
                }
                dp[i][j] = ans;
            }
        }
        return dp[0][n-1];
    }
}
