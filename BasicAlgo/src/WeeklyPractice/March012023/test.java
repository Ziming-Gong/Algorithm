package WeeklyPractice.March012023;

public class test {

    int mod = (int) 1e9 + 7;
    public int subarraysWithMoreZerosThanOnes(int[] nums) {
        int max = 0;
        int min = 0;
        int n = nums.length;
        // 把所有的 0 变成 -1
        // 等于是求所有和大于0的sub array
        for(int i = 0; i < n; i ++){
            if(nums[i] == 0){
                nums[i] = -1;
            }
        }
        // 数组离散化
        // 比如 这个数组 是[0, 1, 0, 1, 0, 1]
        // 这个subarray的和的可能范围 是 -3 ～ 3
        // 把这个范围离散化
        // 真实下标 ： -3 -2 -1  0  1  2  3
        // 离散化后 ：  0  1  2  3  4  5  6
        for(int i : nums){
            if(i > 0){
                max ++;
            }else{
                min ++;
            }
        }
        // 这个 dp的
        // row是有n个
        // col是最大的和是多少
        int m = max + min + 1;

        // dp[x][y] 的含义是 必须以x这个数结尾的subArray的 和是y有多少个？
        int[][] dp = new int[n][m];

        // 离散化后 0 的真实下标 是 min
        int zero = min;
        dp[0][zero + nums[0]] = 1;
        int ans = nums[0] == 1 ? 1 : 0;
        for(int i = 1; i < n; i ++){
            // 求如果 把这个数当成一个 subArray
            // 比如 nums[i] == 1
            // zero + nums[i] 是nums[i] 的真实坐标
            dp[i][zero + nums[i]] = 1;
            if(nums[i] > 0){
                // 如果 此时为nums[i] == 1
                // dp[i][j] 依赖的是 i-1 的时候， y - 1有多少个subarray
                // 比如 此时 i = 4，要求此时比如以 i 这个数结尾 前面有多少个subarray 和为4
                // 又因为你现在nums[i] == 1
                // 所以你需要 i = 3 的时候 和为 3 有多少个 就是下面的dp[i - 1][j - 1]
                for(int j = 1; j < m; j ++){
                    dp[i][j] += dp[i - 1][j - 1] % mod;
                    if(j > zero){
                        ans += dp[i][j];
                    }
                }
            }else{
                // 同上
                for(int j = 0; j < m - 1; j ++){
                    dp[i][j] += dp[i - 1][j + 1] % mod;
                    if(j > zero){
                        ans += dp[i][j];
                    }
                }
            }
            ans %= mod;
        }
        return ans;

    }

}
