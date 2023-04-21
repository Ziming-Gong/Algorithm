package WeeklyPractice.Feb2023.Feb01;

public class lC995MinimumNumberofKConsecutiveBitFlips {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int l = 0;
        int r = 0;
        int[] queue = new int[n];
        int curIndex = 0;
        while (curIndex < n && nums[curIndex] == 1) {
            curIndex++;
        }
        for (int i = 0; i < Math.min(curIndex + k, n); i++) {
            if (((r - l) & 1) == 0 && nums[i] == 0) {
                queue[r++] = i;
            }
        }
        int ans = 0;
        for(; curIndex < n - k; curIndex ++){
            if(curIndex == queue[l]){
                l ++;
                ans ++;
            }
            if(nums[curIndex + k] == ((r - l) & 1)){
                queue[r ++] = curIndex + k;
            }
        }
        return l != r && queue[r - 1] + k > n ? -1 : ans;
    }
}
