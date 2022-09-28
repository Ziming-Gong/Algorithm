package OA;


// Given an array a[] consisting of N integers and an integer K,
// the task is to find the minimum cost to reduce the given array to a single element by choosing any pair of
// consecutive array elements and replace them by (a[i] + a[i+1]) for a cost K * (a[i] + a[i+1]).
public class JP_MinimizeCosttoreducetheArraytoasingleelementbygivenoperations {

    public static int solve(int[] arr, int k) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = arr[i];
        }

        for(int i = 0; i < n - 1; i ++){
            dp[i][i + 1] = (arr[i] + arr[i + 1]) * k;
        }







        return dp[0][n - 1];
    }


}
