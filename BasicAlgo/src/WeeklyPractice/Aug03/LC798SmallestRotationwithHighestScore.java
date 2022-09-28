package WeeklyPractice.Aug03;

public class LC798SmallestRotationwithHighestScore {
    public int bestRotation(int[] nums) {
        int n = nums.length;
        int[] move = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (nums[i] < n) {
                if (nums[i] > i) {
                    add(move, nums[i] - i, n - i - 1);
                } else {
                    add(move, 0, n - i - 1);
                    add(move, n - i + nums[i], n - 1);
                }
            }
        }
        int ans = 0;
        int max = move[0];
        int preSum = max;
        for (int i = 1; i < n; i++) {
            preSum += move[i];
            if (preSum > max) {
                ans = n - i;
                max = preSum;
            }
        }
        return ans;
    }

    public void add(int[] arr, int l, int r) {
        arr[l]++;
        arr[r + 1]--;
    }
}
