package basicAlgo.DPAdvanced;

// 本题测试链接 : https://leetcode.com/problems/remove-boxes/

public class RemoveBoxes {

    public int removeBoxes(int[] boxes) {
        return func(boxes,0,boxes.length-1,0);
    }

    public int func(int[] arr, int L, int R, int k){
        if(L > R){
            return 0;
        }
        int p1 = (k+1)*(k+1) + func(arr, L+1, R, 0);
        for(int i = L + 1; i <= R; i ++){
            if(arr[L] == arr[i]){
                p1 = Math.max(func(arr, L+1,i-1,0)+func(arr, i,R,k+1),p1);
            }
        }
        return p1;
    }

    public int removeBoxes1(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return func(boxes,0,boxes.length-1,0,dp);
    }

    public int func(int[] arr, int L, int R, int k,int[][][] dp){
        if(L > R){
            return 0;
        }
        if(dp[L][R][k] > 0){
            return dp[L][R][k];
        }
        // if(L == R){
        //     return (k+1)*(k+1);
        // }
        int p1 = (k+1)*(k+1) + func(arr, L+1, R, 0,dp);

        for(int i = L + 1; i <= R; i ++){
            if(arr[L] == arr[i]){
                p1 = Math.max(func(arr, L+1,i-1,0,dp)+func(arr, i,R,k+1,dp),p1);
            }
        }
        dp[L][R][k] = p1;
        return p1;
    }

    public int removeBoxes2(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return func2(boxes,0,boxes.length-1,0,dp);
    }

    public int func2(int[] arr, int L, int R, int k,int[][][] dp){
        if(L > R){
            return 0;
        }
        if(dp[L][R][k] > 0){
            return dp[L][R][k];
        }

        int last = L;
        while(last+1 <= R && arr[L] == arr[last+1]){
            last ++;

        }

        int pre = k + last - L;
        int p1 = (pre+1)*(pre+1) + func(arr, last+1, R, 0,dp);

        for(int i = last + 2; i <= R; i ++){
            if(arr[L] == arr[i] && arr[i-1] != arr[L]){
                p1 = Math.max(func(arr, last+1,i-1,0,dp)+func(arr, i,R,pre+1,dp),p1);
            }
        }
        dp[L][R][k] = p1;
        return p1;
    }


}
