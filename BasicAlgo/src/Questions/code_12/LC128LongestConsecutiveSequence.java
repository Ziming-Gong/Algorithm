package Questions.code_12;

import java.util.HashMap;

public class LC128LongestConsecutiveSequence {
    //https://leetcode.com/problems/longest-consecutive-sequence/
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> head = new HashMap<>();
        HashMap<Integer, Integer> tail = new HashMap<>();
        int ans = 1;
        for (int i = 0; i < nums.length; i++) {
            head.put(nums[i], 1);
            tail.put(nums[i], 1);
            if (tail.containsKey(nums[i] - 1)) {
                int start = nums[i] - tail.get(nums[i] - 1);
                head.put(start, head.get(start) + 1);
                tail.put(nums[i], head.get(start));
                tail.remove(nums[i] - 1);
                head.remove((nums[i]));
                ans = Math.max(ans, head.get(start));
            }
            if (head.containsKey(nums[i] + 1)) {
                int start = nums[i] - tail.get(nums[i]) + 1;
                head.put(start, head.get(start) + head.get(nums[i] + 1));
                int end = head.get(nums[i] + 1) + nums[i + 1] - 1;
                tail.put(end, head.get(start));
                tail.remove(nums[i]);
                head.remove(nums[i] + 1);
                ans = Math.max(ans, head.get(start));
            }
        }
        return ans;


    }

    public static void main(String[] args) {
        boolean is = false;
        System.out.println(!is);
    }
}
