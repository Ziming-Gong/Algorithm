package Contest.July9;

public class LC2335MinimumAmountOfTimeToFillCups {
    public int fillCups(int[] amount) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += amount[i];
        }
        int max = Math.max(amount[0], Math.max(amount[1], amount[2]));
        int min = Math.min(amount[0], Math.min(amount[1], amount[2]));
        int mid = sum - max - min;
        int ans = 0;
        while (min > 0) {

            while (max >= mid && mid >= min && mid > 0) {
                max--;
                mid--;
                ans++;
            }
            sum = max + min + mid;
            int temp = max;
            max = Math.max(max, Math.max(mid, min));
            min = Math.min(min, Math.min(temp, mid));
            mid = sum - max - min;
        }

        return ans + max;

    }
}
