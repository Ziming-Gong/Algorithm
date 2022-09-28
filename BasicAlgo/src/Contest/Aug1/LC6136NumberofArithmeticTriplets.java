package Contest.Aug1;

public class LC6136NumberofArithmeticTriplets {
    public int arithmeticTriplets(int[] nums, int diff) {
        if(nums.length < 3){
            return 0;
        }
        int N = nums.length;
        int ans = 0;
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                if(nums[j] - nums[i] == diff){
                    for(int k = j + 1; k < N; k ++){
                        if(nums[k] - nums[j] == diff){
                            ans ++;
                        }
                    }
                }
            }
        }
        return ans;
    }
}
