package basicAlgo.QuadrangleInequality;


// 测试链接：https://leetcode.com/problems/split-array-largest-sum/

public class SplitArrayLargestSum {

    //Most smart
    public int splitArray1(int[] nums, int m) {
        long sum = 0;
        for (int i : nums) {
            sum += i;
        }
        long midSum = 0;
        long leftSum = 0;
        long rightSum = sum;
        long ans = 0;
        while (leftSum <= rightSum) {
            midSum = (leftSum + rightSum) / 2;
            int cur = getParts(nums, midSum);
            if (cur <= m) {
                rightSum = midSum - 1;
                ans = midSum;
            } else { //cur > m
                leftSum = midSum + 1;
            }
        }
        return (int) ans;
    }

    public int getParts(int[] arr, long max) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                return Integer.MAX_VALUE;
            }
        }
        int ans = 1;
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (sum + arr[i] <= max) {
                sum += arr[i];
            } else {
                ans++;
                sum = arr[i];
            }
        }
        return ans;
    }


    // QuadrangleInequality
    public int splitArray2(int[] arr, int m) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[] sum = new int[n + 1];
        int[][] dp = new int[n][m + 1];
        int[][] best = new int[n][m + 1];
        dp[0][1] = arr[0];
        best[0][1] = -1;
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        for (int i = 1; i < n; i++) {
            dp[i][1] = dp[i - 1][1] + arr[i];
            best[i][1] = -1;
        }
        // print(dp);
        // System.out.println();
        // print(best);
        for (int j = 2; j <= m; j++) {
            for (int i = n - 1; i >= 0; i--) {
                int left = best[i][j - 1] != -1 ? best[i][j - 1] : 0;
                int right = i == n - 1 ? n - 1 : best[i + 1][j];
                int ans = Integer.MAX_VALUE;
                int choose = -1;
                for (int range = left; range <= right; range++) {
                    int cur = Math.max(dp[range][j - 1], range == i ? 0 : sum(sum, range + 1, i));
                    if (cur < ans) {
                        ans = cur;
                        choose = range;
                    }
                }
                dp[i][j] = ans;
                best[i][j] = choose;
            }
        }
        return dp[n - 1][m];
    }


    // common DP
    public int splitArray3(int[] nums, int m) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int[][] dp = new int[n][m + 1];
        dp[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i][1] = nums[i] + dp[i - 1][1];
        }
        // print(dp);
        for (int j = 2; j <= m; j++) {
            for (int i = 0; i < n; i++) {
                int max = Integer.MAX_VALUE;
                ;
                for (int range = 0; range < i; range++) {
                    max = Math.min(max, Math.max(dp[range][j - 1], sum(sum, range + 1, i)));
                }
                dp[i][j] = max;
            }
        }

        return dp[n - 1][m];
    }

    public void print(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

    }

    public int sum(int[] arr, int l, int r) {
        return arr[r + 1] - arr[l];
    }


}
