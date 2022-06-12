package Questions.code_1;

import java.util.HashMap;

//https://leetcode.com/problems/target-sum/
public class TargetSum {

    public int findTargetSumWays(int[] nums, int target) {
        return process(nums, 0, target);
    }
    public int process(int[] nums, int index, int rest){
        if(index == nums.length){
            return rest == 0 ? 1 : 0;
        }
        return process(nums, index + 1, rest-nums[index]) + process(nums, index + 1, rest + nums[index]);
    }

    public int findTargetSumWays1(int[] nums, int target) {
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();


        return process(nums, 0, target, map);
    }
    public int process(int[] nums, int index, int rest, HashMap<Integer, HashMap<Integer, Integer>> map){
        if(map.containsKey(index) && map.get(index).containsKey(rest)){
            return map.get(index).get(rest);
        }
        if(index == nums.length){
            return rest == 0 ? 1 : 0;
        }
        int ans = process(nums, index + 1, rest-nums[index],map) + process(nums, index + 1, rest + nums[index],map);
        if(!map.containsKey(index)){
            map.put(index, new HashMap<>());
        }
        map.get(index).put(rest,ans);
        return ans;
    }

    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for(int i = 0; i < nums.length; i ++){
            if(nums[i] < 0){
                nums[i] = ~nums[i] + 1;
            }
            sum += nums[i];
        }
        if(target > sum){
            return 0;
        }
        if((sum & 1) != (target & 1)){
            return 0;
        }



        int M = (target + sum) >> 1;
        if(M < 0){
            return 0;
        }
        int N = nums.length;
        int[][] dp = new int[N][M+1];
        if(nums[0] <= M){
            dp[0][nums[0]] = 1;

        }
        for(int i = 0; i < N; i ++){
            dp[i][0] = nums[i] == 0 ? 2 : 1;
        }

        for(int i = 1; i < N; i ++){
            for(int j = 0; j <= M; j ++){
                dp[i][j] = dp[i-1][j];
                dp[i][j] += j - nums[i] >= 0 ? dp[i-1][j - nums[i]] : 0;
                // dp[i][j] = dp[i - 1][j];
                // if (j - nums[i] >= 0) {
                // 	dp[i][j] += dp[i - 1][j - nums[i]];
                // }
            }
        }
        return dp[N - 1][M];
    }




}
