package WeeklyPractice.Nov2;

import sun.tools.attach.HotSpotVirtualMachine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class test {
    public static int countSubarrays(int[] nums, int k) {
        for(int i = 0; i < nums.length; i ++){
            if(nums[i] == k){
                nums[i] = 0;
            }else if(nums[i] > k){
                nums[i] = 1;
            }else{
                nums[i] = -1;
            }
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int ans = 0;
        boolean find = false;
        for(int i = 0; i < nums.length; i ++){
            sum += nums[i];
//            ans += map.getOrDefault(sum, 0);
            if(nums[i] == 0){
                find = true;
            }
            if(find){
                ans += map.getOrDefault(-sum + 1, 0);
                ans += map.getOrDefault(-sum, 0);
            }

            if(!find){
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }

        }
        return ans;

    }

    public static void main(String[] args) {
        System.out.println((long) Math.pow(2, 30));
    }
}
