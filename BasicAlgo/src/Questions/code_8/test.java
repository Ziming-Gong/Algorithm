package Questions.code_8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class test {
    public List<List<Integer>> threeSum(int[] nums) {
        int N = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for(int i = 0; i < N - 2; i ++){
            if(i != 0 && nums[i-1] == nums[i]){
                continue;
            }
            if(nums[i] > 0){
                break;
            }
            f(ans, nums, i + 1, 0 - nums[i]);

        }
        return ans;
    }

    public void f (List<List<Integer>> ans, int[] nums, int begin, int target){
        int L = begin;
        int R = nums.length - 1;
        while( L < R){
            if(nums[L] + nums[R] == target){
                List<Integer> list = new ArrayList<>();
                list.add(-target);
                list.add(nums[L++]);
                list.add(nums[R++]);
                ans.add(list);
            }else if(nums[L] + nums[R] < target){
                L ++;
            }else{
                R ++;
            }
            while(L < nums.length && L != begin && nums[L] == nums[L - 1]){
                L ++;
            }
            while(R > begin && R < nums.length - 1 && nums[R] == nums[R + 1]){
                R --;
            }
        }
    }
}
