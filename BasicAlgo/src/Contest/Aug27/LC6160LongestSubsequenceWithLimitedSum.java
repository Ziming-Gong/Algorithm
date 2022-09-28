package Contest.Aug27;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LC6160LongestSubsequenceWithLimitedSum {
//    public static int[] answerQueries(int[] nums, int[] queries) {
//        int n = nums.length;
//        int m = queries.length;
//        int[] ans = new int[m];
//        long[] preSum = new long[n];
//        preSum[0] = nums[0];
//        for (int i = 1; i < n; i++) {
//            preSum[i] = nums[i] + preSum[i - 1];
//        }
//        ArrayList<Long> list = new ArrayList<>();
//        TreeMap<>
//        for (int i = 0; i < n; i++) {
//            for (int j = i; j < n; j++) {
//                list.add(preSum[j] - preSum[i]);
//            }
//        }
//        list.sort((a, b) -> (a.compareTo(b)));
//        for (int q : queries) {
//
//        }
//        return ans;
//
//    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 1};
        int[] q = {3, 10, 21};
        System.out.println(answerQueries(arr, q));
    }

    public static int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int m = queries.length, sum, l;
        int[] ans = new int[m];
        Arrays.sort(nums);
        for(int i = 0; i < m; i ++){
            if(nums[0] > queries[i]){
                continue;
            }
            sum = 0;
            l = 0;
            while(l < n && sum <= queries[i]){
                sum += nums[l];
                l ++;
            }
            ans[i] = l + 1;
        }
        return ans;
    }


}
