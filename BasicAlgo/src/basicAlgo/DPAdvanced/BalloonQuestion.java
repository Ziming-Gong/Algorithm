package basicAlgo.DPAdvanced;


// 本题测试链接 : https://leetcode.com/problems/burst-balloons/
public class BalloonQuestion {

    public int maxCoins0(int[] nums) {
        int N = nums.length;
        int[] help = new int[N + 2];
        help[0] = 1;
        help[N + 1] = 1;
        for (int i = 0; i < N; i++) {
            help[i + 1] = nums[i];
        }
        // for(int i = 0; i < help.length;i++){
        //     System.out.printf(help[i]+", ");
        // }
        return process(help, 1, N);
    }

    //L...R中，哪个最后一个burst
    //rules:
    //  1. L-1 : not burst
    //  2. R+1 : not burst
    public int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L] * arr[L + 1] * arr[L - 1];
        }

        int p1 = arr[L] * arr[L - 1] * arr[R + 1] + process(arr, L + 1, R);
        p1 = Math.max(p1, arr[L - 1] * arr[R + 1] * arr[R] + process(arr, L, R - 1));
        for (int i = L + 1; i < R; i++) {
            p1 = Math.max(p1, arr[L - 1] * arr[R + 1] * arr[i] + process(arr, L, i - 1) + process(arr, i + 1, R));
        }
        return p1;
    }

    public int maxCoins1(int[] nums){
        int N = nums.length;
        int[] help = new int[N+2];
        for(int i =0; i < N; i ++){
            help[i+1] = nums[i];
        }
        help[N+1] = 1;
        help[0] = 1;
        int[][] dp = new int[N+2][N+2];
        for(int i = 1; i <= N; i ++){
            dp[i][i] = help[i]*help[i-1]*help[i+1];
        }
        dp[0][0] =1;
        dp[N+1][N+1] = 1;
        // print(dp);
        for(int i = N; i >=1; i --){
            for(int j = i+1; j <= N; j ++){
                int max = help[i]*help[i-1]*help[j+1] +dp[i+1][j];
                max = Math.max(max, help[j]*help[i-1]*help[j+1] + dp[i][j-1]);
                for(int k = i+1; k < j;k ++){
                    max = Math.max(max, help[k]*help[i-1]*help[j+1] + dp[i][k-1]+dp[k+1][j]);
                }
                dp[i][j] = max;
            }
        }
        // print(dp);
        return dp[1][N];
    }

    public void print(int[][] dp){
        for(int i = 0; i < dp.length; i ++){
            for(int j = 0; j < dp[0].length; j ++){
                System.out.printf(dp[i][j] + ", ");
            }
            System.out.println();
        }
    }
}
