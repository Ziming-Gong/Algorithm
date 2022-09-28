package Contest.Sep3;

public class LC6169LongestNiceSubarray {
    public int longestNiceSubarray(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int ans = 0;
        int[] had = new int[31];
        int l = 0, r = 1;
        int cur = nums[0];
        for (int i = 0; i <= 31; i++) {
            if ((1 << i & cur) != 0) {
                had[i] = 1;
            }
        }
        while (r < nums.length) {
            cur = nums[r];
            while (!isValid(had, cur)) {
                remove(had, nums, l++);
            }
            if (r - l + 1 > ans) {
                ans = r - l + 1;
            }
            add(had, cur);
            r++;
        }
        return ans;

    }
    public void add(int[] had, int cur){
        for (int i = 0; i <= 31; i++) {
            if ((1 << i & cur) != 0) {
                had[i] = 1;
            }
        }
    }

    public void remove(int[] had, int[] arr, int l) {
        int cur = arr[l];
        for (int i = 0; i <= 31; i++) {
            if ((1 << i & cur) != 0) {
                had[i]--;
            }
        }
    }

    public boolean isValid(int[] had, int cur) {
        for (int i = 0; i <= 31; i++) {
            if ((1 << i & cur) != 0 && had[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
