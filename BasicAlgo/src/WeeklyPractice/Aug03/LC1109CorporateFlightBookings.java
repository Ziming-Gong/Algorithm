package WeeklyPractice.Aug03;

public class LC1109CorporateFlightBookings {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] arr = new int[n + 2];
        for(int[] cur : bookings){
            arr[cur[0]] += cur[2];
            arr[cur[1] + 1] -= cur[2];
        }
        int[] ans = new int[n];
        int preSum = arr[1];
        ans[0] = preSum;
        for(int i = 1; i < n; i ++){
            preSum += arr[i + 1];
            ans[i] = preSum;
        }
        return ans;
    }
}
