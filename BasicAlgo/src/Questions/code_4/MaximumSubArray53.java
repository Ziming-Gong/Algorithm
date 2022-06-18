package Questions.code_4;
//https://leetcode.com/problems/maximum-subarray/submissions/
public class MaximumSubArray53 {
    public int maxSubArray(int[] arr) {
        int n = arr.length;
        int pre = arr[0];
        int max = arr[0];
        for(int i = 1; i < n; i ++){
            pre = Math.max(arr[i], arr[i] + pre);
            max = Math.max(max, pre);
        }
        return max;
    }
}
